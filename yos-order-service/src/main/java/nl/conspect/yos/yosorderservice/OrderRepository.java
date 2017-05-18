package nl.conspect.yos.yosorderservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<OrderSummary> findAllSummarizedBy();
    List<OrderSummary> findAllSummarizedByCustomerId(long customerId);


}
