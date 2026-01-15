package sparta.spartateamproject1.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import sparta.spartateamproject1.dto.CustomerGetDto;
import sparta.spartateamproject1.dto.CustomerSearchCondition;

import java.util.List;

import static sparta.spartateamproject1.entity.QCustomer.customer;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CustomerRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<CustomerGetDto.Response> findByOption(CustomerSearchCondition dto, Pageable pageable) {
        List<CustomerGetDto.Response> content = queryFactory
                .select(Projections.constructor(CustomerGetDto.Response.class,
                        customer.id,
                        customer.name,
                        customer.phoneNumber,
                        customer.email,
                        customer.status,
                        customer.createdAt
                ))
                .from(customer)
                .where(customerNameEquals(dto), customerEmailEquals(dto), customerStatusEquals(dto))
                .orderBy(getSortOrder(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> totalCount = queryFactory
                .select(customer.count())
                .from(customer)
                .where(customerNameEquals(dto), customerEmailEquals(dto), customerStatusEquals(dto));

        return PageableExecutionUtils.getPage(content, pageable, () -> totalCount.fetchOne());
    }

    private BooleanExpression customerNameEquals(CustomerSearchCondition dto) {
        if (dto != null && dto.getName() != null) {
            return customer.name.eq(dto.getName());
        }
        return null;
    }

    private BooleanExpression customerEmailEquals(CustomerSearchCondition dto) {
        if (dto != null && dto.getEmail() != null) {
            return customer.email.eq(dto.getEmail());
        }
        return null;
    }

    private BooleanExpression customerStatusEquals(CustomerSearchCondition dto) {
        if (dto != null&&dto.getStatus() != null) {
            return customer.status.eq(dto.getStatus());
        }
        return null;
    }

    // Pageable의 Sort 정보를 Querydsl의 orderSpecifier로 변환
    private OrderSpecifier<?> getSortOrder(Pageable pageable) {
        Sort.Order order = pageable.getSort().iterator().next();
        Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

        // 사용자가 요청한 sortBy(Property)에 따른 분기 처리
        switch (order.getProperty()) {
            case "name":
                return new OrderSpecifier<>(direction, customer.name);
            case "email":
                return new OrderSpecifier<>(direction, customer.email);
            default:
                return new OrderSpecifier<>(direction, customer.createdAt);
        }
    }
}
