package ch.schmucki.order.controller;

import ch.schmucki.order.model.OrderDetail;
import ch.schmucki.order.persistence.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class OrderDetailController {
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailController(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @GetMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> find(@PathVariable long orderDetailId) {
        if(orderDetailRepository.existsById(orderDetailId)) {
            return new ResponseEntity<>(orderDetailRepository.findById(orderDetailId).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDetail>> findAll() {
        return new ResponseEntity<>(orderDetailRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailRepository.save(orderDetail), HttpStatus.CREATED);
    }

    @GetMapping("/{orderDetailId}")
    @PutMapping
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable long orderDetailId, @RequestBody OrderDetail orderDetail) {
        if(orderDetailRepository.existsById(orderDetailId)) {
            OrderDetail od = orderDetailRepository.findById(orderDetailId).get();
            od.setOrder(orderDetail.getOrder());
            od.setUnitPrice(orderDetail.getUnitPrice());
            od.setQuantity(orderDetail.getQuantity());
            od.setDiscount(orderDetail.getDiscount());
            orderDetailRepository.save(od);
            return new ResponseEntity<>(od, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> deleteOrderDetal(@PathVariable long orderDetailId) {
        if(orderDetailRepository.existsById(orderDetailId)) {
            orderDetailRepository.deleteById(orderDetailId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
