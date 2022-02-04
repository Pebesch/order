package ch.schmucki.order.controller;

import ch.schmucki.order.model.Employee;
import ch.schmucki.order.persistence.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> find(@PathVariable long employeeId) {
        if(employeeRepository.existsById(employeeId)) {
            return new ResponseEntity<>(employeeRepository.findById(employeeId).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long employeeId, @RequestBody Employee employee) {
        if(employeeRepository.existsById(employeeId)) {
            Employee e = employeeRepository.findById(employeeId).get();
            e.setLastName(employee.getLastName());
            e.setFirstName(employee.getFirstName());
            e.setTitle(employee.getTitle());
            e.setTitleOfCourtesy(employee.getTitleOfCourtesy());
            e.setBirthDate(employee.getBirthDate());
            e.setHireDate(employee.getHireDate());
            e.setAddress(employee.getAddress());
            employeeRepository.save(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId) {
        if(employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
