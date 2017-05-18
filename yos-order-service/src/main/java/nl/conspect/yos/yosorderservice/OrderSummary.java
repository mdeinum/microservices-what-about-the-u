package nl.conspect.yos.yosorderservice;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public interface OrderSummary {

    long getId();
    String getOrderNumber();
    LocalDate getOrderDate();
    LocalDate getDeliveryDate();
    @Value("#{target.getTotalNumberOfItems()}")
    int getTotalNumberOfItems();
    @Value("#{target.getTotalOrderPrice()}")
    BigDecimal getTotalOrderPrice();

}
