package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.dto.request.productOrder.ProductOrderSaveRequestDto;
import com.dcankayrak.productservice.entity.Order;
import com.dcankayrak.productservice.entity.Product;
import com.dcankayrak.productservice.entity.ProductOrder;
import com.dcankayrak.productservice.exception.ProductNotFoundException;
import com.dcankayrak.productservice.feign.UserServiceClient;
import com.dcankayrak.productservice.repository.OrderRepository;
import com.dcankayrak.productservice.repository.ProductOrderRepository;
import com.dcankayrak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    public void saveProductOrder(ProductOrderSaveRequestDto request) {
        Product tempProduct = productRepository
                .findById(request.getProductId()).orElseThrow(() -> {throw new ProductNotFoundException("Aradığınız ürün bulunamadı!");});
        Order tempOrder = orderRepository.findById(request.getOrderId()).orElseThrow();

        if(userServiceClient.isUserExists(request.getCustomerId())){
            ProductOrder newProductOrder = new ProductOrder();
            newProductOrder.setProduct(tempProduct);
            newProductOrder.setOrder(tempOrder);
            newProductOrder.setUserId(request.getCustomerId());
            productOrderRepository.save(newProductOrder);
        }else{
            throw new RuntimeException("Kullanıcı Bulunamadı!");
        }
    }
}