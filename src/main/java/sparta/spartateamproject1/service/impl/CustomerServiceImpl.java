package sparta.spartateamproject1.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.spartateamproject1.dto.AdminUpdateDto;
import sparta.spartateamproject1.dto.CustomerGetDto;
import sparta.spartateamproject1.dto.LoginSessionAttribute;
import sparta.spartateamproject1.entity.Customer;
import sparta.spartateamproject1.exception.CustomerNotFoundException;
import sparta.spartateamproject1.repository.CustomerRepository;
import sparta.spartateamproject1.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    //세부 조회
    @Override
    public CustomerGetDto.Response findOne(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("존재하지 않는 고객입니다."));

        return CustomerGetDto.Response.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .status(customer.getStatus())
                .createdAt(customer.getCreatedAt())
                .build();



    }

    @Override
    public List<CustomerGetDto.Response> findAll(LoginSessionAttribute loginSessionAttribute) {
        // TODO : update 만들기
        throw new IllegalArgumentException("UNIMPLEMENTED");
    }

    @Override
    public CustomerGetDto.Response update(Long var1, Long var2, AdminUpdateDto.Request var3) {
        // TODO : update 만들기
        throw new IllegalArgumentException("UNIMPLEMENTED");
    }

    @Override
    public void delete(Long var1, Long var2) {
        // TODO : delete 만들기
        throw new IllegalArgumentException("UNIMPLEMENTED");
    }
}
