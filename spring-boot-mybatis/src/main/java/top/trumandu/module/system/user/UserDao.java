package top.trumandu.module.system.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.trumandu.module.system.user.domain.UserEntity;
import top.trumandu.module.system.user.domain.UserVO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    List<UserVO> listAllUser();
}
