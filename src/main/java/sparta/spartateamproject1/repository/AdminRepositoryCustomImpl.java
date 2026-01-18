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
import sparta.spartateamproject1.dto.AdminGetAllDto;
import sparta.spartateamproject1.dto.AdminGetDto;
import sparta.spartateamproject1.dto.AdminSearchCondition;

import java.time.LocalDateTime;
import java.util.List;

import static sparta.spartateamproject1.entity.QAdmin.admin;

public class AdminRepositoryCustomImpl implements AdminRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public AdminRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<AdminGetAllDto.Response> findByOption(AdminSearchCondition dto, Pageable pageable) {
        List<AdminGetAllDto.Response> content = queryFactory
                .select(Projections.constructor(AdminGetAllDto.Response.class,
                        admin.id,
                        admin.name,
                        admin.email,
                        admin.phoneNumber,
                        admin.role,
                        admin.status,
                        admin.createdAt,
                        admin.approvalResult.approvedAt
                ))
                .from(admin)
                .where(adminNameEquals(dto), adminEmailEquals(dto), adminStatusEquals(dto))
                .orderBy(getSortOrder(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> totalCount = queryFactory
                .select(admin.count())
                .from(admin)
                .where(adminNameEquals(dto), adminEmailEquals(dto), adminStatusEquals(dto));

        return PageableExecutionUtils.getPage(content, pageable, () -> totalCount.fetchOne());
    }

    private BooleanExpression adminNameEquals(AdminSearchCondition dto) {
        if (dto != null && dto.getName() != null) {
            return admin.name.eq(dto.getName());
        }
        return null;
    }

    private BooleanExpression adminEmailEquals(AdminSearchCondition dto) {
        if (dto != null && dto.getEmail() != null) {
            return admin.email.eq(dto.getEmail());
        }
        return null;
    }

    private BooleanExpression adminStatusEquals(AdminSearchCondition dto) {
        if (dto != null&&dto.getStatus() != null) {
            return admin.status.eq(dto.getStatus());
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
                return new OrderSpecifier<>(direction, admin.name);
            case "email":
                return new OrderSpecifier<>(direction, admin.email);
            default:
                return new OrderSpecifier<>(direction, admin.createdAt);
        }
    }
}
