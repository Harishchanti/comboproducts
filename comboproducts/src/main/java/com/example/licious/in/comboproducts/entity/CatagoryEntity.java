package com.example.licious.in.comboproducts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "catagory")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CatagoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "enabled")
    Boolean enaled;

    @Column(name = "parent_Id")
    Long parentId;

    @Column(name = "level")
    Long level;

    @Column(name = "img_link")
    String image;

    @Column(name = "returnable")
    Boolean returnable;

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
