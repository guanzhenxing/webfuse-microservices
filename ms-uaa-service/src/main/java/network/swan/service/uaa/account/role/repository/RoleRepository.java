package network.swan.service.uaa.account.role.repository;

import network.swan.service.uaa.account.role.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {

    int delete(String id);

    int insert(Role role);

    int update(Role role);

    Role selectById(String id);

    List<Role> search(Role queryParam, int pageNum, int pageSize);

    long countBySearch(Role queryParam);
}
