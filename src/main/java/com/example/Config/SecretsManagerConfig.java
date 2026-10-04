package com.example.Config;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

public class SecretsManagerConfig {

    private final SecretsManagerClient secretsManagerClient;

    public SecretsManagerConfig() {
        this.secretsManagerClient = SecretsManagerClient.builder()
                                                        .region(Region.AP_SOUTH_1)
                                                        .build();
    }

    public String getSecret(String secretArn) {

        GetSecretValueRequest request =
                GetSecretValueRequest.builder()
                                     .secretId(secretArn)
                                     .build();

        return secretsManagerClient
                .getSecretValue(request)
                .secretString();
    }
}