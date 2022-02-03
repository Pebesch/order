package ch.schmucki.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SHIPPING_ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private long Id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Employee employee;
    private Date orderDate;
    private Date shippingDate;
    private String shippingAddress;
}
