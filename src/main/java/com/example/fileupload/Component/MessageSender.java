package com.example.fileupload.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.logging.Logger;

@Component
public class MessageSender {

    private static Logger logger = Logger
            .getLogger(MessageSender.class.getName());

    @Autowired
    SqsClient sqsClient;
    public  void sendMessage( ) {
//        SqsClient sqsClient = getSQSClient();

        final String queueURL
                = "https://sqs.ap-south-1.amazonaws.com/387683656737/process_txns";

        SendMessageRequest sendMessageRequest = SendMessageRequest
                .builder()
                .queueUrl(queueURL)
                .messageBody("Test message")
                .build();

        // Send message and get the messageId in return
        SendMessageResponse sendMessageResponse =
                sqsClient
                        .sendMessage(sendMessageRequest);

        logger.info("message id: "+ sendMessageResponse.messageId());

//        sqsClient.close();
    }


}
