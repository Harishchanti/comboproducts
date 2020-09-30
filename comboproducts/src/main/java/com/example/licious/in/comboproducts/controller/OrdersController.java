package com.example.licious.in.comboproducts.controller;

import com.example.licious.in.comboproducts.model.ApiResponse;
import com.example.licious.in.comboproducts.model.CartRequest;
import com.example.licious.in.comboproducts.model.CartResposne;
import com.example.licious.in.comboproducts.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrdersController {


    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<CartResposne>> cart(@RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(orderService.createOrder(cartRequest));
    }
}
