package com.example.fileupload.batchtask.nachupload;


import com.example.fileupload.model.secondary.BulkUploadRow;
import com.example.fileupload.repository.secondary.BulkUploadRowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Writer implements
        ItemWriter<BulkUploadRow>, StepExecutionListener {

    private final Logger logger = LoggerFactory
            .getLogger(Writer.class);

    @Autowired
    BulkUploadRowRepository bur;
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Writer initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Writer ended.");
        return ExitStatus.COMPLETED;
    }

    @Override
    public void write(Chunk<? extends BulkUploadRow> chunks) throws Exception {

        List<BulkUploadRow> rows= (List<BulkUploadRow>) chunks.getItems();
        bur.saveAll(rows);

        logger.debug("Line Writer row written.");

    }

}