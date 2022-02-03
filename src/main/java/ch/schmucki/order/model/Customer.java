package ch.schmucki.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
}
