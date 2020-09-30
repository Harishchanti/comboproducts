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
public class ComboProductsDetails {

    Long id;
    String description;
    Double sellingPrice;
    List<ProductDetails> productDetailsList;
}
