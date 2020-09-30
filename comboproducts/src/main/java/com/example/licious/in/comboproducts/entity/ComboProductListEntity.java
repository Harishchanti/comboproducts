package com.example.licious.in.comboproducts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "combo_product_list")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComboProductListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /*@Column(name = "combo_id")
    Long comboProductId;
*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "combo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    ComboProductsEntity comboProductsEntity;


    @Column(name = "mrp")
    Double mrp;

    @Column(name = "product_sku")
    String productSku;

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
