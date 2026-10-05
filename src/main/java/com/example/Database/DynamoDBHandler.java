package com.example.Database;

import com.example.Entity.OrderRequest;
import java.util.Map;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

public class DynamoDBHandler {

    private final DynamoDbClient client;

    public DynamoDBHandler() {
        this.client = DynamoDbClient.builder().build(); // Automatically takes the current region!
    }

    public String addOrderToDb(OrderRequest  orderRequest, String tableName) {
        return this.client.putItem(
                PutItemRequest.builder().item(
                        Map.of(
                                "OrderId", AttributeValue.builder().s(orderRequest.getOrderId()).build(),
                                "CustomerId", AttributeValue.builder().s(orderRequest.getCustomerId()).build(),
                                "Amount", AttributeValue.builder().n(orderRequest.getAmount().toString()).build(),
                                "Notify", AttributeValue.builder().bool(orderRequest.isNotify()).build()
                        )
                ).conditionExpression("attribute_not_exists(OrderId)").tableName(tableName).build()
                ).responseMetadata().requestId();
    }

}


//sam validate --lint
//sam build
//sam deploy --config-env dev