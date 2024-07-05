package models;

import enums.PaymentMode;

import java.util.Date;

public class Payment {
    private int id;
    private PaymentMode mode;
    private Date timeOfPayment;
    private String txnId;
    private Long amount;
}
