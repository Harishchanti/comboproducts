package com.example.licious.in.comboproducts.controller;

import com.example.licious.in.comboproducts.model.ApiResponse;
import com.example.licious.in.comboproducts.model.ApiResult;
import com.example.licious.in.comboproducts.model.ComboProductCreateRequest;
import com.example.licious.in.comboproducts.model.ComboProductsDetails;
import com.example.licious.in.comboproducts.service.ComboProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ComboProductsController {

    @Autowired
    ComboProductsService comboProductsService;

    @RequestMapping(value = "/combo/products", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> createComboProducts(@RequestBody ComboProductCreateRequest request) {
        if (!comboProductsService.validateRequest(request)) {
            return ResponseEntity.ok(new ApiResponse<>(null, ApiResult.FAILURE, "Invalid request"));
        }
        return ResponseEntity.ok(comboProductsService.createComboProducts(request));
    }

    @RequestMapping(value = "/combo/products", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<ComboProductsDetails>>> getComboProducts(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                                    @RequestParam(value = "pageSize", defaultValue = "30", required = false) Integer pageSize,
                                                                                    @RequestParam(value = "sortby", defaultValue = "desc", required = false) String sortBy) {
        return ResponseEntity.ok(comboProductsService.getComboProductDetails(page, pageSize, sortBy));
    }


}
