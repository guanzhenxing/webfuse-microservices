package network.swan.service.uaa.account.permission.repository;

import network.swan.service.uaa.account.permission.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository {

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 插入数据
     *
     * @param record
     * @return
     */
    int insert(Permission record);

    /**
     * 修改数据
     *
     * @param record
     * @return
     */
    int update(Permission record);

    /**
     * 根据id查询单个权限信息
     *
     * @param id
     * @return
     */
    Permission selectById(String id);

    /**
     * 按照搜索条件查询
     *
     * @param queryParam
     * @return
     */
    List<Permission> search(Permission queryParam, int pageNum, int pageSize);

    /**
     * 按照过滤条件统计
     *
     * @param queryParam
     * @return
     */
    long countBySearch(Permission queryParam);


}
