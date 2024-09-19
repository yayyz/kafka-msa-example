package com.spartacoding.msa.order.presentaion;

import com.spartacoding.msa.order.application.OrderApplicationService;
import com.spartacoding.msa.order.dto.OrderCreateDto;
import com.spartacoding.msa.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderApplicationService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        return new ResponseEntity<>(orderService.createOrder(orderCreateDto.getProductId(), orderCreateDto.getQuantity(),
                orderCreateDto.getCustomerEmail(), orderCreateDto.getTotalPrice()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

}
