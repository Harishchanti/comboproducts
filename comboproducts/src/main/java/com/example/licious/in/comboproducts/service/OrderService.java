package com.example.licious.in.comboproducts.service;

import com.example.licious.in.comboproducts.entity.ComboProductsEntity;
import com.example.licious.in.comboproducts.entity.OrdersEntity;
import com.example.licious.in.comboproducts.model.*;
import com.example.licious.in.comboproducts.repository.ComboProductsRepository;
import com.example.licious.in.comboproducts.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    ComboProductsRepository comboProductsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    public final String NUMERIC_STRING = "0123456789";


    public ApiResponse<CartResposne> createOrder(CartRequest cartRequest) {
        boolean valid = validateCartRequest(cartRequest);
        if (!valid) return new ApiResponse<>(null, ApiResult.FAILURE, "invalid cart request");
        OrdersEntity orderEntry = new OrdersEntity();
        orderEntry.setBuyerName(cartRequest.getName());
        orderEntry.setBuyerPhNumber(cartRequest.getMobilenumber());
        orderEntry.setOrderNumber(generateUniqueOrderNumber());
        Optional<ComboProductsEntity> opComboProductsEntity = comboProductsRepository.findById(cartRequest.getComboId());
        orderEntry.setComboId(cartRequest.getComboId());
        orderEntry.setStatus(Constants.OrderStaus.ORDER_PLACED.name());
        orderEntry.setQuantity(cartRequest.getQty());
        // final amount = total amount - discount ammount
        // discount amount is considers if any coupons or reward points are applied
        Double totalAmount = opComboProductsEntity.get().getSellingPrice() * cartRequest.getQty();
        orderEntry.setFinalAmount(totalAmount);
        orderEntry.setTotalAmount(totalAmount);
        orderEntry = ordersRepository.save(orderEntry);
        return new ApiResponse<>(populateCartResponse(orderEntry), ApiResult.SUCCESS, null);
    }

    private CartResposne populateCartResponse(OrdersEntity orderEntry) {
        return new CartResposne(orderEntry.getOrderNumber());
    }

    private String generateUniqueOrderNumber() {
        boolean flag = true;
        String orderId = generateOrderId(6);
        while (flag) {
            OrdersEntity orderEntity = ordersRepository.findByOrderNumber(orderId);
            if (orderEntity == null) {
                break;
            }
            orderId = generateOrderId(6);
        }
        return orderId;
    }

    private String generateOrderId(int n) {

        StringBuilder sb = new StringBuilder();
        //updateOrderPrefix(sb);
        for (int i = 0; i < n; i++) {
            int index = (int) (NUMERIC_STRING.length() * Math.random());
            sb.append(NUMERIC_STRING.charAt(index));
        }
        return sb.toString();
    }


    private boolean validateCartRequest(CartRequest cartRequest) {
        // validate
        // if buyer details
        // combo details
        return true;
    }
}
