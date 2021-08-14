package top.trumandu.module.system.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.trumandu.module.system.role.domain.RoleEntity;

/**
 * @author Truman.P.Du
 * @date 2021/08/14
 * @description
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
}
