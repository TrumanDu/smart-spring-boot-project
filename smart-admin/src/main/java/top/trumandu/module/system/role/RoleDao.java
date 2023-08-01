package top.trumandu.module.system.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.trumandu.module.system.role.domain.RoleEntity;
import top.trumandu.module.system.role.domain.RoleVO;
import top.trumandu.module.system.role.domain.UserRoleVO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2021/08/14
 * @description
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {



    /**
     * 根据角色查询用户列表
     *
     * @param roleId
     * @return
     */
    List<UserRoleVO> selectUserListByRole(@Param("roleId") Long roleId);

    /**
     * 删除角色用户
     * @param id
     * @return
     */
    Integer deleteRoleUser(@Param("id") Long id);

    /**
     * 给用户赋予角色
     * @param roleId
     * @param userId
     */
    void insertUserRole(@Param("roleId") Long roleId,@Param("userId") Long userId);

    /**
     * 获取用户所属的角色
     * 可能为null
     * @param userId
     * @return
     */
    RoleVO selectRoleByUserId(@Param("userId") Long userId);
}
