package com.example.licious.in.comboproducts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "catagory_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    CatagoryEntity catagoryEntity;

    @Column(name = "sku")
    String sku;

    @Column(name = "imgs_link")
    String images;

    @Column(name = "enabled")
    Boolean enabled;

    @Column(name = "mrp")
    Double mrp;

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
