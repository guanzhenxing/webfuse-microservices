package network.swan.service.uaa.account.role.repository;

import network.swan.service.uaa.account.role.domain.Role;

import java.util.List;

public interface RoleRepository {

    int delete(String id);

    int insert(Role role);

    int update(Role role);

    Role selectById(String id);

    List<Role> search(Role queryParam, int pageNum, int pageSize);

    long countBySearch(Role queryParam);
}
