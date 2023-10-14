package com.example.fileupload.batchtask.nachupload;

import com.example.fileupload.model.secondary.BulkUploadRow;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

public class Processor implements
        ItemProcessor<JsonNode, BulkUploadRow>, StepExecutionListener {

    private Logger logger = LoggerFactory.getLogger(Processor.class);
    private  JobParameters parameters;
    private JsonSchema jsonSchema;
    ObjectMapper mapper;


    @Override
    public void beforeStep(StepExecution stepExecution) {
        mapper = new ObjectMapper();
        parameters = stepExecution.getJobExecution().getJobParameters();
        String schemaJson= String.valueOf(parameters.getParameter("stringSchemaJson").getValue());
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
         jsonSchema = factory.getSchema(schemaJson);
        logger.debug("Line Processor initialized.");
    }

    @Override
    public BulkUploadRow process(JsonNode row) throws Exception {

        JsonNode parsedRow = row.deepCopy();

        BulkUploadRow dbRow= new BulkUploadRow();
        dbRow.setOriginalRow(row);
        dbRow.setIsValidationError(Boolean.FALSE);
        dbRow.setIsProcessingError(Boolean.FALSE);
        Set<ValidationMessage> validationMessages = correctlyParseTextNode(parsedRow, jsonSchema);

        if (!validationMessages.isEmpty()){
            dbRow.setIsValidationError(Boolean.FALSE);
            JsonNode errors_node = mapper.convertValue(validationMessages, JsonNode.class);
            dbRow.setValidationErrorMsg(errors_node);
        }
        else {
            dbRow.setParsedRow(parsedRow);
        }
        return dbRow;

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Processor ended.");
        return ExitStatus.COMPLETED;
    }

    private Set<ValidationMessage> correctlyParseTextNode(JsonNode item, JsonSchema jsonSchema) {
        var ref = new Object() {
            final Set<ValidationMessage> typeValidationMessages = new HashSet<ValidationMessage>();
        };
        jsonSchema.getSchemaNode().get("properties").fields().forEachRemaining(entry -> {
            try {
                String propertyName = entry.getKey();
                JsonNode propertySchema = entry.getValue();
                if (item.has(propertyName)) {
                    String value = item.get(propertyName).asText();
                    String type = propertySchema.get("type").asText();
                    convertToSchemaType(item, value, type, propertyName);
                }

            }
            catch (Exception e) {
                ValidationMessage v = (new ValidationMessage.Builder()).format(new MessageFormat("")).customMessage(e.getClass().getSimpleName() + ":" +e.getLocalizedMessage()).build();
                ref.typeValidationMessages.add(v);
            }
        });
        Set<ValidationMessage> valueValidationMessages = jsonSchema.validate(item);
        Set<ValidationMessage> mergedValidationMessages=new HashSet<ValidationMessage>();
        mergedValidationMessages.addAll(ref.typeValidationMessages);
        mergedValidationMessages.addAll(valueValidationMessages);
        return mergedValidationMessages;
    }
    private void convertToSchemaType(JsonNode item, String value, String type,String propertyName) {
        switch (type) {
            case "integer":
                ((ObjectNode) item).put(propertyName, Integer.parseInt(value));
                break;
            case "boolean":
                ((ObjectNode) item).put(propertyName, Boolean.valueOf(value.toLowerCase()));
                break;
            case "string":
                ((ObjectNode) item).put(propertyName, value);
                break;
            case "number":
                ((ObjectNode) item).put(propertyName, Double.parseDouble(value));
                break;
        }
    }
}