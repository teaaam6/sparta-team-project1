package sparta.spartateamproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sparta.spartateamproject1.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long>, ItemRepositoryCustom {
    @Modifying
    @Query("UPDATE Item i SET i.admin = null WHERE i.admin.id = :adminId")
    int setAdminToNullByAdminId(@Param("adminId") Long adminId);
}
