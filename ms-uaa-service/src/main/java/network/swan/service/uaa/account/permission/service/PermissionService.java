package network.swan.service.uaa.account.permission.service;

import network.swan.core.utils.IdUtil;
import network.swan.frame.exception.BadArgumentException;
import network.swan.frame.exception.NoResourceException;
import network.swan.frame.exception.WafBizException;
import network.swan.service.uaa.account.permission.domain.Permission;
import network.swan.service.uaa.account.permission.repository.PermissionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by guanzhenxing on 2017/11/20.
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public int delete(String id) {
        int count = permissionRepository.delete(id);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "删除权限不正确");
        }
        //TODO  删除权限-角色表
        return count;
    }

    public Permission insert(Permission record) {
        long id = new IdUtil(0, 0).nextId();
        record.setId(String.valueOf(id));
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int count = permissionRepository.insert(record);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "插入权限不正确");
        }
        return record;
    }

    public Permission update(Permission record) {
        if (StringUtils.isEmpty(record.getId())) {
            throw new BadArgumentException("BAD_ARGUMENT", "ID为空");
        }
        record.setUpdateTime(new Date());
        int count = permissionRepository.update(record);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "修改权限不正确");
        }
        return record;
    }

    public Permission selectById(String id) {

        Permission permission = permissionRepository.selectById(id);
        if (permission == null) {
            throw new NoResourceException("NOT_FOUND", "没有发现对应的资源");
        }
        return permission;
    }

    public List<Permission> search(Permission queryParam, int pageNum, int pageSize) {
        List<Permission> permissions = permissionRepository.search(queryParam, pageNum, pageSize);
        return permissions;
    }

    public long countBySearch(Permission queryParam) {
        return permissionRepository.countBySearch(queryParam);
    }
}
