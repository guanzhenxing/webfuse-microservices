package network.swan.uaa.repository;

import network.swan.uaa.models.TokenBlackList;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by guanzhenxing on 2017/9/3.
 */
public interface TokenBlackListRepo  extends Repository<TokenBlackList, Long> {
    Optional<TokenBlackList> findByJti(String jti);
    List<TokenBlackList> queryAllByUserIdAndIsBlackListedTrue(Long userId);
    void save(TokenBlackList tokenBlackList);
    List<TokenBlackList> deleteAllByUserIdAndExpiresBefore(Long userId, Long date);
}

