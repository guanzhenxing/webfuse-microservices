package network.swan.service.uaa.account.role.service;

import network.swan.core.utils.IdUtil;
import network.swan.frame.exception.NoResourceException;
import network.swan.frame.exception.WafBizException;
import network.swan.service.uaa.account.role.domain.Role;
import network.swan.service.uaa.account.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by guanzhenxing on 2017/11/20.
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public int delete(String id) {
        int count = roleRepository.delete(id);
        //TODO  删除权限-角色表
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "删除角色失败");
        }
        return count;
    }

    public Role insert(Role record) {
        long id = new IdUtil(0, 0).nextId();
        record.setId(String.valueOf(id));
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int count = roleRepository.insert(record);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "插入角色失败");
        }
        return record;
    }

    public Role update(Role record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int count = roleRepository.update(record);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "修改角色失败");
        }
        return record;
    }

    public Role selectById(String id) {
        Role role = roleRepository.selectById(id);
        if (role == null) {
            throw new NoResourceException("NOT_FOUND", "没有发现对应的资源");
        }
        return role;
    }

    List<Role> search(Role queryParam, int pageNum, int pageSize) {
        return roleRepository.search(queryParam, pageNum, pageSize);
    }

    public long countBySearch(Role queryParam) {
        return roleRepository.countBySearch(queryParam);
    }


}
