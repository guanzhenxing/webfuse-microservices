package network.swan.service.uaa.account.user.repository;

import network.swan.service.uaa.account.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
@Repository
public interface UserRepository {

    /**
     * 根据ID删除
     *
     * @param id
     * @return 删除的条数
     */
    int delete(String id);

    /**
     * 插入数据
     *
     * @param record
     * @return 插入的条数
     */
    int insert(User record);

    /**
     * 修改数据
     *
     * @param record
     * @return
     */
    int update(User record);


    /**
     * 根据ID获得用户信息
     *
     * @param id
     * @return
     */
    User selectById(String id);

    /**
     * 根据用户名获得用户信息
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 按照搜索条件查询
     *
     * @param queryParam
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<User> search(User queryParam, int pageNum, int pageSize);


    /**
     * 按照过滤条件统计
     *
     * @param queryParam
     * @return
     */
    long countBySearch(User queryParam);
}
