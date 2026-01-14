package sparta.spartateamproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.spartateamproject1.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
