package com.example.licious.in.comboproducts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetails {

    Long id;
    String name;
    String description;
    String sku;
    List<String> images;
    Boolean enabled;
    Double mrp;
}
