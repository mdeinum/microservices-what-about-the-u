package nl.conspect.yos.yosorderservice;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * An order detail is the link table between {@link Order} and {@link Book} We also store how many books are ordered in
 * the {@link #quantity} field
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Entity
public class OrderDetail extends AbstractPersistable<Long> implements Serializable {

    private String description;

    @Column(nullable= false, precision=7, scale=2)
    @Digits(integer=9, fraction=2)
    private BigDecimal price;

    @Min(1)
    private int quantity = 1;

    private OrderDetail() {
        super();
    }

    public OrderDetail(String description, int quantity, BigDecimal price) {
        this.description=description;
        this.quantity=quantity;
        this.price=price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getTotalPrice() {
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }

    public static OrderDetail of(String description, int quantity, BigDecimal price) {
        return new OrderDetail(description, quantity, price);
    }

}