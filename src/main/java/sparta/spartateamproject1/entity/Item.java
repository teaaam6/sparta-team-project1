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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public void updateInfo(String name, ItemCategory category, Long price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void updateStock(Long stock) {
        this.stock = stock;

        // item상태가 단종이 아닐 경우 자동으로 업데이트 합니다.
        if (this.status != ItemStatus.DISCONTINUED) {
            if (this.stock <= 0) {
                this.status = ItemStatus.SOLD_OUT;
            }
        }
    }

    public void updateStatus(ItemStatus status) {
        this.status = status;
    }
}
