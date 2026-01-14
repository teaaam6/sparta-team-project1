package sparta.spartateamproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.spartateamproject1.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
