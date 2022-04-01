package com.guestpro.iot.emoney.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ReqTopup implements Serializable {
    private String userId;
    private String transactionRef;
    private String description;
    private BigDecimal amount;
}
