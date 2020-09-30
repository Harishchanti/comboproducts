package com.example.licious.in.comboproducts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order_number")
    String orderNumber;

    @Column(name = "buyer_name")
    String buyerName;

    @Column(name = "buyer_ph_number")
    String buyerPhNumber;

    @Column(name = "combo_id")
    Long comboId;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "total_amount")
    Double totalAmount;

    @Column(name = "final_amount")
    Double finalAmount;

    @Column(name = "discount_amount")
    Double discountAmount;

    @Column(name = "status")
    String status;


    @Column(name = "created_at")
    Date createdOn;

    @Column(name = "update_at")
    Date updatedOn;

    @PreUpdate
    protected void updateTimestamp() {
        Date cDate = Calendar.getInstance().getTime();
        if (createdOn == null) {
            createdOn = cDate;
        }
        updatedOn = cDate;
    }

    @PrePersist
    protected void onCreate() {
        createdOn = new Date();
        updatedOn = new Date();
    }
}
