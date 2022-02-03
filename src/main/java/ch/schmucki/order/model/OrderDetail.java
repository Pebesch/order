package ch.schmucki.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Order order;
    private int unitPrice;
    private int quantity;
    private int discount;
}
