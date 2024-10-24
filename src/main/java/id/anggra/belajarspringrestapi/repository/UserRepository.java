package id.anggra.belajarspringrestapi.repository;

import id.anggra.belajarspringrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
