package com.example.Notification;

import com.example.Entity.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Slf4j
public class SnsHandler {

    private final SnsClient snsClient;

    public SnsHandler() {
        this.snsClient = SnsClient.builder().build();
    }

    public String sendNotification(OrderRequest orderRequest, String topicArn) {
        try {
            return this.snsClient.publish(
                    PublishRequest.builder()
                                  .message(orderRequest.toString())
                                  .subject("Order Notification - " + orderRequest.getOrderId())
                                  .topicArn(topicArn)
                                  .build()
            ).messageId();
        } catch (Exception e) {
            log.warn("Failed to format SNS HTML message: {}", e.getMessage());
            return "Failed to format SNS HTML message: " + e.getMessage();
        }
    }

}
