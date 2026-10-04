package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSBatchResponse;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.example.Config.ParameterStoreConfig;
import com.example.Config.SecretsManagerConfig;
import com.example.Entity.OrderRequest;
import java.util.ArrayList;
import java.util.List;
import tools.jackson.databind.ObjectMapper;

public class OrderLambdaHandler implements RequestHandler<SQSEvent, SQSBatchResponse> {

    private final ObjectMapper objectMapper= new ObjectMapper();
    private final ParameterStoreConfig parameterStoreConfig =
            new ParameterStoreConfig();
    private final SecretsManagerConfig secretsManagerConfig = new  SecretsManagerConfig();


    @Override
    public SQSBatchResponse handleRequest(SQSEvent event, Context context) {

        String secretArn =
                System.getenv("ORDER_SERVICE_SECRET_ARN");

        String secret =
                secretsManagerConfig.getSecret(secretArn);

        context.getLogger().log(
                "Secret successfully retrieved"
        );

        String parameterName = System.getenv("DOWNSTREAM_URL_PARAMETER");

        String downstreamUrl =
                parameterStoreConfig.getParameter(parameterName);

        context.getLogger().log(
                "Downstream URL: " + downstreamUrl
        );

        String environment = System.getenv("ENVIRONMENT");
        String applicationName = System.getenv("APPLICATION_NAME");
        String logLevel = System.getenv("LOG_LEVEL");
        String timeout = System.getenv("DOWNSTREAM_TIMEOUT");

        context.getLogger().log(
                "Environment: " + environment
        );

        context.getLogger().log(
                "Application: " + applicationName
        );

        context.getLogger().log(
                "Log Level: " + logLevel
        );

        context.getLogger().log(
                "Downstream timeout: " + timeout
        );

        context.getLogger().log(
                "Received " + event.getRecords().size() + " SQS messages"
        );

        List<SQSBatchResponse.BatchItemFailure> failures = new ArrayList<>();

        for (SQSEvent.SQSMessage message : event.getRecords()) {
            try {
                context.getLogger().log(
                        "Message ID: " + message.getMessageId()
                );

                OrderRequest request = objectMapper.readValue(message.getBody(), OrderRequest.class);

                context.getLogger().log("Message Body: " + request);
                context.getLogger().log("Order ID: " + request.getOrderId()
                        + " Customer ID: " + request.getCustomerId()
                        + " Amt: " + request.getAmount());
            } catch (Exception e) {
                context.getLogger()
                       .log("Failed to process message Id: "
                               + message.getMessageId()
                               + " "
                               + e.getMessage()
                       );
                failures.add(new SQSBatchResponse.BatchItemFailure(message.getMessageId()));
            }
        }

        return new SQSBatchResponse(failures);
    }
}