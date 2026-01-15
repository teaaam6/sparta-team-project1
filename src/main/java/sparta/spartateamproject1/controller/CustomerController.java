package sparta.spartateamproject1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sparta.spartateamproject1.dto.CustomerGetDto;
import sparta.spartateamproject1.dto.CustomerSearchCondition;
import sparta.spartateamproject1.dto.LoginSessionAttribute;
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
}
