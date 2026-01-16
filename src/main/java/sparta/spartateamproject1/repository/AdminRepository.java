package sparta.spartateamproject1.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sparta.spartateamproject1.dto.AdminGetDto;
import sparta.spartateamproject1.dto.AdminSearchCondition;
import sparta.spartateamproject1.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {
    Optional<Admin> findByEmail(String email);
}
