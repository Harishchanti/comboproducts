package com.example.licious.in.comboproducts.service;

import com.example.licious.in.comboproducts.entity.ComboProductListEntity;
import com.example.licious.in.comboproducts.entity.ComboProductsEntity;
import com.example.licious.in.comboproducts.entity.ProductsEntity;
import com.example.licious.in.comboproducts.model.*;
import com.example.licious.in.comboproducts.repository.ComboProductsListRepository;
import com.example.licious.in.comboproducts.repository.ComboProductsRepository;
import com.example.licious.in.comboproducts.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComboProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ComboProductsRepository comboProductsRepository;

    @Autowired
    ComboProductsListRepository comboProductsListRepository;

    public boolean validateRequest(ComboProductCreateRequest request) {
        // check if all the product SKU's are valid
        return true;
    }

    public ApiResponse<String> createComboProducts(ComboProductCreateRequest request) {

        ComboProductsEntity comboProductsEntity = new ComboProductsEntity();
        comboProductsEntity.setDiscription(request.getDescription());
        comboProductsEntity.setEnaled(true);
        comboProductsEntity.setSellingPrice(request.getSellingPrice());
        comboProductsEntity = comboProductsRepository.save(comboProductsEntity);
        List<ComboProductListEntity> comboProductListEntityList = new ArrayList<>();
        for (String sku : request.getSkuList()) {
            ComboProductListEntity comboProductListEntity = new ComboProductListEntity();
            comboProductListEntity.setComboProductsEntity(comboProductsEntity);
            comboProductListEntity.setProductSku(sku);
            comboProductListEntityList.add(comboProductListEntity);
        }
        comboProductsListRepository.saveAll(comboProductListEntityList);
        return new ApiResponse<>("Successfully Created combo product", ApiResult.SUCCESS, null);
    }

    public ApiResponse<List<ComboProductsDetails>> getComboProductDetails(Integer page, Integer pageSize, String sortBy) {
        Sort.Direction sortDirection = Sort.Direction.DESC;
        if (sortBy.equalsIgnoreCase("asc")) {
            sortDirection = Sort.Direction.ASC;
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortDirection, "sellingPrice"));

        Page<ComboProductsEntity> comboProductsEntities= comboProductsRepository.findAll(pageable);

        //List<ComboProductsEntity> comboProductsEntities = comboProductsRepository.findAll(pageable).get().collect(Collectors.toList());
        List<ComboProductsDetails> comboProductsDetailsList = new ArrayList<>();
        for (ComboProductsEntity comboProductEntity : comboProductsEntities) {
            ComboProductsDetails comboProductsDetails = new ComboProductsDetails();
            comboProductsDetails.setDescription(comboProductEntity.getDiscription());
            comboProductsDetails.setId(comboProductEntity.getId());
            comboProductsDetails.setSellingPrice(comboProductEntity.getSellingPrice());

            comboProductsDetails.setProductDetailsList(populateProductDetails(comboProductEntity));
            comboProductsDetailsList.add(comboProductsDetails);
        }
        return new ApiResponse<>(comboProductsDetailsList, ApiResult.SUCCESS, null);
    }

    private List<ProductDetails> populateProductDetails(ComboProductsEntity comboProductEntity) {
        List<String> skus = comboProductsListRepository.findAllByComboId(comboProductEntity.getId()).stream().map(ComboProductListEntity::getProductSku)
                .collect(Collectors.toList());
        List<ProductDetails> productDetailsList = new ArrayList<>();
        List<ProductsEntity> productsEntities = productsRepository.findByProductSku(skus);
        for (ProductsEntity p : productsEntities) {

            ProductDetails productDetails = new ProductDetails();
            productDetails.setDescription(p.getDescription());
            productDetails.setName(p.getName());
            productDetails.setEnabled(p.getEnabled());
            productDetails.setId(p.getId());
            productDetails.setImages(p.getImages() != null ? Arrays.asList(p.getImages().split(",")) : null);
            productDetails.setMrp(p.getMrp());
            productDetails.setSku(p.getSku());
            productDetailsList.add(productDetails);
        }
        return productDetailsList;
    }
}
