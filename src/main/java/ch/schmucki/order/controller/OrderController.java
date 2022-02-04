package ch.schmucki.order.controller;

import ch.schmucki.order.model.Order;
import ch.schmucki.order.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> find(@PathVariable long orderId) {
        if(orderRepository.existsById(orderId)) {
            return new ResponseEntity<>(orderRepository.findById(orderId).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(Order order) {
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable long orderId, Order order) {
        if(orderRepository.existsById(orderId)) {
            Order o = orderRepository.findById(orderId).get();
            o.setCustomer(order.getCustomer());
            o.setEmployee(order.getEmployee());
            o.setOrderDate(order.getOrderDate());
            o.setShippingDate(order.getShippingDate());
            o.setShippingAddress(order.getShippingAddress());
            orderRepository.save(o);
            return new ResponseEntity<>(o, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable long orderId) {
        if(orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
