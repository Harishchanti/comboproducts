package com.example.licious.in.comboproducts.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "combo_products")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComboProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "discription")
    String discription;

    /*@Column(name = "combo_id")
    String comboId;*/

    @Column(name = "selling_price")
    Double sellingPrice;

    @Column(name = "enabled")
    Boolean enaled;

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
