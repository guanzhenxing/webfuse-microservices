package network.swan.service.uaa.account.user.service;

import network.swan.core.utils.IdUtil;
import network.swan.frame.exception.WafBizException;
import network.swan.service.uaa.account.user.domain.User;
import network.swan.service.uaa.account.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int delete(String id) {
        int count = userRepository.delete(id);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "删除用户失败");
        }
        //TODO 删除用户-角色关联
        return count;
    }

    public User insert(User record) {
        long id = new IdUtil(0, 0).nextId();
        record.setId(String.valueOf(id));
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int count = userRepository.insert(record);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "插入用户失败");
        }
        return record;
    }

    public User update(User record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int count = userRepository.update(record);
        if (count < 1) {
            throw new WafBizException("BIZ_ERROR", "修改用户失败");
        }
        return record;
    }

    public User selectById(String id) {
        User account = userRepository.selectById(id);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("Id[%s] not found", id));
        }
        return account;
    }

    public User findByUsername(String username) {
        User account = userRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
        return account;
    }

    List<User> search(User queryParam, int pageNum, int pageSize) {
        return userRepository.search(queryParam, pageNum, pageSize);
    }

    public long countBySearch(User queryParam) {
        return userRepository.countBySearch(queryParam);
    }

}
