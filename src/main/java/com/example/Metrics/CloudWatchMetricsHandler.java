package com.example.Metrics;

import com.example.Enums.OrderStatusEnum;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import software.amazon.awssdk.services.cloudwatch.model.MetricDatum;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;

public class CloudWatchMetricsHandler {

    private final CloudWatchClient cloudWatchClient;
    public CloudWatchMetricsHandler() {
        this.cloudWatchClient = CloudWatchClient.builder().build();
    }

    public void putMetric(OrderStatusEnum orderStatusEnum) {
        Dimension dimension = Dimension.builder()
                                       .name("OrderStatus")
                                       .value(orderStatusEnum.name())
                                       .build();

        // 2. Build the metric datum item
        MetricDatum datum = MetricDatum.builder()
                                       .metricName("ProcessingEvents")
                                       .dimensions(dimension)
                                       .value(orderStatusEnum.getValue())
                                       .unit(StandardUnit.COUNT)
                                       .build();

        // 3. Construct and dispatch the request
        PutMetricDataRequest request = PutMetricDataRequest.builder()
                               .namespace(System.getenv("APPLICATION_NAME"))
                               .metricData(datum)
                               .build();

        cloudWatchClient.putMetricData(request);
    }


}
