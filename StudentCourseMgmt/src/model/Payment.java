package model;

import enumeration.PaymentStatus;
import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private double amount;
    private LocalDate paymentDate;
    private PaymentStatus pStatus;
}
