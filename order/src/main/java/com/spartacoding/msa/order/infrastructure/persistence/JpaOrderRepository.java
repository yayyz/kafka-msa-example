package com.spartacoding.msa.order.infrastructure.persistence;

import com.spartacoding.msa.order.domain.Order;
import com.spartacoding.msa.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, Long> {
}
