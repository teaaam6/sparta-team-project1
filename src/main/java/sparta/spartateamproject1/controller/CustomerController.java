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
import sparta.spartateamproject1.jwt.JWTUtil;
import sparta.spartateamproject1.service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class CustomerController {
    private final CustomerService customerService;
    private final JWTUtil jwtUtil;

    @GetMapping("/customers")
    public Page<CustomerGetDto.Response> getCustomers(@RequestHeader("Authorization") String token, @ModelAttribute CustomerSearchCondition conditionDto) {
        int pageNumber = conditionDto.getPageNumber() - 1;
        int pageSize = conditionDto.getPageSize();
        boolean asc = conditionDto.isAsc();
        String sortBy = conditionDto.getSortBy();

        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        // JPA는 0부터 시작
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return customerService.findAll(id, conditionDto, pageRequest);
    }

    @PatchMapping("/customers/{customerId}")
    public ResponseEntity<CustomerUpdateDto.Response> modifyCustomer(@RequestHeader("Authorization") String token, @Valid @RequestBody CustomerUpdateDto.Request dto, @PathVariable Long customerId) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(id, customerId, dto));
    }

    @PatchMapping("/customers/{customerId}/status")
    public ResponseEntity<CustomerUpdateDto.StatusResponse> modifyCustomerStatus(@RequestHeader("Authorization") String token, @Valid @RequestBody CustomerUpdateDto.StatusRequest dto, @PathVariable Long customerId) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateStatus(id, customerId, dto));
    }

    //세부조회
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerGetDto.Response> getOne(
            @PathVariable Long customerId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findOne(customerId));
    }

    //고객삭제
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> delete(
        // @RequestHeader("Authorization") String token,
        @PathVariable Long customerId
    ) {
        customerService.delete(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
