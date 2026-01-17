package sparta.spartateamproject1.dev;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sparta.spartateamproject1.entity.Customer;
import sparta.spartateamproject1.repository.CustomerRepository;
import sparta.spartateamproject1.type.CustomerStatus;

@Component
@ConditionalOnProperty(
    name = "app.add-test-customers",
    havingValue = "true",
    matchIfMissing = false
)
@RequiredArgsConstructor
public class TestCustomerAdder implements CommandLineRunner {
    
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        // 고객 데이터를 db에 저장해둠
        for (int i = 1; i <= 30; i++) {
            CustomerStatus cs;
            if (i % 3 == 1) {
                cs = CustomerStatus.ACTIVE;
            } else if (i % 3 == 2) {
                cs = CustomerStatus.INACTIVE;
            } else {
                cs = CustomerStatus.STOP;
            }
            Customer customer = new Customer("customer" + i, "customer" + i + "@gmail.com", "010-1234-1234", cs, LocalDateTime.now());
            customerRepository.save(customer);
        }
    }
}
