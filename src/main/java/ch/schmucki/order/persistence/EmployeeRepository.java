package ch.schmucki.order.persistence;

import ch.schmucki.order.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
