package sparta.spartateamproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.spartateamproject1.entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);

    Boolean existsByEmail(String email);
}
