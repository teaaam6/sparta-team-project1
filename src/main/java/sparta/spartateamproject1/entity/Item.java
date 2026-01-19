package sparta.spartateamproject1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sparta.spartateamproject1.type.ItemCategory;
import sparta.spartateamproject1.type.ItemStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ItemCategory category;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    // 상품을 등록한 관리가 어떤 이유에서이든지
    // 삭제 될 수 있기 때문에 nullable 입니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "admin_id")
    private Admin admin;

    public void updateInfo(String name, ItemCategory category, Long price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void updateStock(Long stock) {
        Long previousStock = this.stock;

        this.stock = stock;

        // item상태가 단종이 아닐 경우 자동으로 업데이트 합니다.
        if (this.status != ItemStatus.DISCONTINUED) {
            // 실제로 재고가 의미있게 변경 되었는지 확인
            if (
                (previousStock <= 0 && this.stock > 0) ||
                (previousStock > 0 && this.stock <= 0)
            ) {
                if (this.stock <= 0) {
                    this.status = ItemStatus.SOLD_OUT;
                }else {
                    this.status = ItemStatus.ON_SALE;
                }
            }
        }
    }

    public void updateStatus(ItemStatus status) {
        this.status = status;
    }
}
