package com.example.Config;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

public class ParameterStoreConfig {

    private final SsmClient ssmClient;

    public ParameterStoreConfig() {
        this.ssmClient = SsmClient.builder()
                                  .region(Region.AP_SOUTH_1)
                                  .build();
    }

    public String getParameter(String parameterName) {

        GetParameterRequest request = GetParameterRequest.builder()
                                                         .name(parameterName)
                                                         .withDecryption(true)
                                                         .build();

        return ssmClient.getParameter(request)
                        .parameter()
                        .value();
    }
}