package sparta.spartateamproject1.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.entity.Customer;
import sparta.spartateamproject1.exception.AdminNotFoundException;
import sparta.spartateamproject1.exception.CustomerNotFoundException;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.repository.CustomerRepository;
import sparta.spartateamproject1.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    // 고객 리스트 조회
    // 모든 관리자가 접근 가능
    @Override
    public Page<CustomerGetDto.Response> findAll(LoginSessionAttribute loginSessionAttribute, CustomerSearchCondition dto, Pageable pageable) {
        adminRepository.findById(loginSessionAttribute.getId()).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        return customerRepository.findByOption(dto, pageable);
    }

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
    //고객정보수정

    //고객상태수정
    //고객삭제
    @Override
    public void delete(
            LoginSessionAttribute attribute,
            Long customerId) {
    }
}
