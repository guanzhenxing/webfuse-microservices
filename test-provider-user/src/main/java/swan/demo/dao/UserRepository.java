package swan.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swan.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}