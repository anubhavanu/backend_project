package com.example.fileupload.Component;

import com.example.fileupload.model.CustomerTxns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;
import java.util.logging.Logger;
@Component
public class MessageReciever {
    private static Logger logger = Logger
            .getLogger(MessageReciever.class.getName());


    @Autowired
    SqsClient sqsClient;
    public String recieveMessage()
    {
        final String queueURL
                = "https://sqs.ap-south-1.amazonaws.com/387683656737/process_txns";
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueURL)
                .build();
        List<Message> receivedMessages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        String messages = "";
        for (Message receivedMessage : receivedMessages) {
            messages += receivedMessage.body() + "\n";
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()    //line no 31 -35 are for deleting the message after reading it from SQS queue
                    .queueUrl(queueURL)
                    .receiptHandle(receivedMessage.receiptHandle())
                    .build();
            sqsClient.deleteMessage(deleteMessageRequest);
            logger.info("message id: "+ receivedMessage.messageId());
        }
        return messages;

    }
    public String recieveMessage(CustomerTxns customerTxns)
    {
        final String queueURL
                = "https://sqs.ap-south-1.amazonaws.com/387683656737/process_txns";
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueURL)
                .build();
        List<Message> receivedMessages = sqsClient.receiveMessage(receiveMessageRequest).messages();

        for (Message receivedMessage : receivedMessages) {
            String messages = receivedMessage.body() ;
            if (messages.contains(customerTxns.getAccountInfo().getUser_account_no())){
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()    //line no 31 -35 are for deleting the message after reading it from SQS queue
                    .queueUrl(queueURL)
                    .receiptHandle(receivedMessage.receiptHandle())
                    .build();
            sqsClient.deleteMessage(deleteMessageRequest);

        }


    }
        return ("transaction removed from waiting queue");
    }

}
