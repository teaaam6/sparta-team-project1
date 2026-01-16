package sparta.spartateamproject1.dev;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sparta.spartateamproject1.config.PasswordEncoder;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.entity.ApprovalResult;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.repository.ItemRepository;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.ItemCategory;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.Role;

@Component
@ConditionalOnProperty(
    name = "app.add-test-items",
    havingValue = "true",
    matchIfMissing = false
)
@RequiredArgsConstructor
public class TestItemAdder implements CommandLineRunner {
    
    private final ItemRepository itemRepository;
    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 상품 주인이 될 admin들을 추가
        List<String> adminNames = List.of(
                "momo", "kiki", "baba"
        );
        List<String> adminPhoneNumbers = List.of(
                "010-1111-1111", 
                "010-2222-2222", 
                "010-3333-3333"
        );
        List<String> adminEmails = List.of(
                "momo@gmail.com",
                "kiki@naver.com",
                "baba@daum.com"
        );

        List<Admin> admins = new ArrayList<Admin>();

        ApprovalResult result = new ApprovalResult("", null, LocalDateTime.now(), true);

        for (int i=0; i<adminNames.size(); i++) {
            Admin admin = Admin.builder()
                    .name(adminNames.get(i))
                    .email(adminEmails.get(i))
                    .password(passwordEncoder.encode("12345678"))
                    .phoneNumber(adminPhoneNumbers.get(i))
                    .role(Role.ADMIN) // 일단은 전부 슈퍼 유저로
                    .status(AdminStatus.ACTIVE)
                    .approvalResult(result)
                    .build();

            adminRepository.save(admin);

            admins.add(admin);
        }

        // 상품의 등록관리자가 삭제되어 null 일수도 있습니다.
        admins.add(null);

        // 테스트 상품 추가
        ItemCategory[] categories = ItemCategory.values();
        ItemStatus[] statuses = ItemStatus.values();

        final int itemsToAdd = 30;
        final int maxStock = itemsToAdd * 10;

        // 고객 데이터를 db에 저장해둠
        for (int i = 1; i <= itemsToAdd; i++) {
            ItemCategory category = categories[i % categories.length];
            ItemStatus status = statuses[i % statuses.length];

            Item item = Item.builder()
                .name("item " + i)
                .category(category)
                .price((long)(i*100))
                .stock((long)(maxStock - i*10))
                .status(status)
                .admin(admins.get((i-1) % admins.size()))
                .build();

            itemRepository.save(item);
        }
    }
}
