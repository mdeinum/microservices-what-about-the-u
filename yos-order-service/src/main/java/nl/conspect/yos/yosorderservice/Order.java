package nl.conspect.yos.yosorderservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Entity
// order is a reserved SQL keyword, hence the explicit table definition
@Table(name = "orders")
public class Order extends AbstractPersistable<Long> {

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "shipping_street")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "shipping_houseNumber")),
            @AttributeOverride(name = "boxNumber", column = @Column(name = "shipping_boxNumber")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postalCode")),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country")) })
    private Address shippingAddress;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "billing_houseNumber")),
            @AttributeOverride(name = "boxNumber", column = @Column(name = "billing_boxNumber")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "billing_postalCode")),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country")) })
    private Address billingAddress;

    private String orderNumber;

    @Column(nullable = false)
    @NotNull
    private Long customerId;

    private boolean billingSameAsShipping = true;

    private LocalDate orderDate;
    private LocalDate deliveryDate;

    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderDetail> orderDetails = new ArrayList<>();

    private Order() {}

    private Order(long customerId, String orderNumber, LocalDate orderDate,  Address shippingAddress) {
        this.customerId=customerId;
        this.orderNumber=orderNumber;
        this.orderDate = orderDate;
        this.shippingAddress=shippingAddress;
    }

    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public Address getBillingAddress() {
        if (this.billingSameAsShipping) {
            return this.shippingAddress;
        }
        return this.billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public boolean isBillingSameAsShipping() {
        return this.billingSameAsShipping;
    }

    public void setBillingSameAsShipping(boolean billingSameAsShipping) {
        this.billingSameAsShipping = billingSameAsShipping;
    }

    public List<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableList(this.orderDetails);
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public BigDecimal getTotalOrderPrice() {
        return this.orderDetails.stream().map(OrderDetail::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalNumberOfItems() {
       return this.orderDetails.stream().mapToInt(OrderDetail::getQuantity).sum();
    }


    public void addOrderDetail(OrderDetail detail) {
        this.orderDetails.add(detail);
    }

    public static Order of(Iterable<OrderDetail> orderDetails, long customerId, Address shippingAddress) {
        String orderNumber = UUID.randomUUID().toString();
        Order order = new Order(customerId, orderNumber, LocalDate.now(), shippingAddress);
        orderDetails.forEach(order::addOrderDetail);
        return order;
    }
}
