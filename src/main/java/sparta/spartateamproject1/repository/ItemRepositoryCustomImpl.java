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

import sparta.spartateamproject1.dto.ItemGetPageDto;
import sparta.spartateamproject1.dto.ItemSearchCondition;

import java.util.List;

import static sparta.spartateamproject1.entity.QItem.item;
import static sparta.spartateamproject1.entity.QAdmin.admin;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemGetPageDto.Response> findByOption(ItemSearchCondition dto, Pageable pageable) {
        List<ItemGetPageDto.Response> content = queryFactory
                .select(Projections.constructor(ItemGetPageDto.Response.class,
                        item.id,
                        item.name,
                        item.category,
                        item.price,
                        item.stock,
                        item.status,
                        item.createdAt,
                        item.admin.id,
                        admin.name
                ))
                .from(item)
                .where(itemNameContains(dto), itemStatusEquals(dto), itemCategoryEquals(dto))
                .leftJoin(admin)
                .on(item.admin.eq(admin))
                .orderBy(getSortOrder(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> totalCount = queryFactory
                .select(item.count())
                .from(item)
                .where(itemNameContains(dto), itemStatusEquals(dto), itemCategoryEquals(dto));

        return PageableExecutionUtils.getPage(content, pageable, () -> totalCount.fetchOne());
    }

    private BooleanExpression itemNameContains(ItemSearchCondition dto) {
        if (dto != null && dto.getName() != null) {
            // return customer.name.eq(dto.getName());
            return item.name.containsIgnoreCase(dto.getName());
        }
        return null;
    }

    private BooleanExpression itemStatusEquals(ItemSearchCondition dto) {
        if (dto != null && dto.getStatus() != null) {
            return item.status.eq(dto.getStatus());
        }
        return null;
    }

    private BooleanExpression itemCategoryEquals(ItemSearchCondition dto) {
        if (dto != null && dto.getCategory() != null) {
            return item.category.eq(dto.getCategory());
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
                return new OrderSpecifier<>(direction, item.name);
            case "price":
                return new OrderSpecifier<>(direction, item.price);
            case "stock":
                return new OrderSpecifier<>(direction, item.stock);
            default:
                return new OrderSpecifier<>(direction, item.createdAt);
        }
    }
}
