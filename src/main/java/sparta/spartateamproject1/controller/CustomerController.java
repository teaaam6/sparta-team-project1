package sparta.spartateamproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public Page<CustomerGetDto.Response> getCustomers(@SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute, @ModelAttribute CustomerSearchCondition conditionDto) {
        int pageNumber = conditionDto.getPageNumber() - 1;
        int pageSize = conditionDto.getPageSize();
        boolean asc = conditionDto.isAsc();
        String sortBy = conditionDto.getSortBy();

        // JPA는 0부터 시작
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return customerService.findAll(loginSessionAttribute, conditionDto, pageRequest);
    }

    @PatchMapping("/customers/{customerId}")
    public ResponseEntity<CustomerUpdateDto.Response> modifyCustomer(@SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute, @Valid @RequestBody CustomerUpdateDto.Request dto, @PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(loginSessionAttribute, customerId, dto));
    }

//    @PostMapping("/customers/{customerId}")
//    public ResponseEntity
}
