package com.example.Entity;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequest {
    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private boolean notify = false;
}

// {"orderId":"ORD-3","customerId":"CUST-3","amount":3000}
// {"orderId":"ORD-3","customerId":"CUST-3","amount":3000, "notify": true}