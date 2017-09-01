package swan.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import swan.demo.model.Customer;

import java.util.List;

/**
 * Created by guanzhenxing on 2017/8/10.
 */
@Mapper
@Repository
public interface CustomerMapper {

    // 注解 @TargetDataSource 不可以在这里使用
    @Select("SELECT * from customer WHERE name = #{name}")
    List<Customer> getByName(@Param("name") String name);

}
