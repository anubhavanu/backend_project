package com.example.fileupload.batchtask.nachupload;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reader implements ItemReader<JsonNode>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(Reader.class);

    private MappingIterator<JsonNode> mappingIterator;
    private  JobParameters parameters;
    private ObjectMapper mapper;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        parameters = stepExecution.getJobExecution().getJobParameters();

        String csvFileName= String.valueOf(parameters.getParameter("csvFileName").getValue());
        File csvFile = null;
        try {
            csvFile = ResourceUtils.getFile("classpath:"+csvFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        String schemaJson= String.valueOf(parameters.getParameter("stringSchemaJson").getValue());
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);


        JsonSchema jsonSchema = factory.getSchema(schemaJson);

        JsonNode jsonSchemaNode;
        try {
            jsonSchemaNode = mapper.readTree(schemaJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//        Map<String, CsvSchema.ColumnType> map = getSchemaMapFromJson(jsonSchemaNode);
//
//        CsvSchema.Builder schemaBuilder = new CsvSchema.Builder();
//        map.forEach(schemaBuilder::addColumn);
//        CsvSchema schema = schemaBuilder.build().withQuoteChar('"').withColumnSeparator(',').withoutHeader();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
//        CsvSchema schema = schemaBuilder.build().withQuoteChar('"').withColumnSeparator(',');

        CsvMapper csvMapper = new CsvMapper();
        try {
            mappingIterator = csvMapper.readerFor(JsonNode.class).with(schema).readValues(csvFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.debug("Line Reader initialized.");
    }

    @Override
    public JsonNode read() throws Exception {
        if (mappingIterator.hasNext()){
            return mappingIterator.next();
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Reader ended.");
        return ExitStatus.COMPLETED;
    }
    private  Map<String, CsvSchema.ColumnType> getSchemaMapFromJson(JsonNode jsonSchemaNode) {

        ArrayList<String> headerNames= new ArrayList<>();
//        jsonSchema.getSchemaNode().get("properties").fields().forEachRemaining(entry -> {
//                String propertyName = entry.getKey();
//                JsonNode propertySchema = entry.getValue();
//                String type = propertySchema.get("type").asText();
//                headerNames.add(propertyName);
//
//        });

        HashMap<String, CsvSchema.ColumnType> hm= new HashMap<>() ;
        hm.put("firstName",CsvSchema.ColumnType.STRING);
        hm.put("age",CsvSchema.ColumnType.NUMBER);
        hm.put("salary",CsvSchema.ColumnType.NUMBER);
        hm.put("isValid",CsvSchema.ColumnType.STRING);
        hm.put("joiningDate",CsvSchema.ColumnType.STRING);
        return  hm;
    }
}