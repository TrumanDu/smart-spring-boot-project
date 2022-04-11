package top.trumandu.module.system.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.trumandu.module.system.role.domain.RoleMenuEntity;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/11
 * @description
 */
@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenuEntity> {
    /**
     * 查询角色所拥有的所有菜单Id
     * @param roleId
     * @return
     */
    List<Long> selectMenuIdsByRole(@Param("roleId") Long roleId);

    /**
     * 删除指定角色下所有菜单
     * @param roleId
     */
    void deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 批量插入角色菜单信息
     * @param roleMenuEntities
     */
    void batchInsert(@Param("roleMenuEntities") List<RoleMenuEntity> roleMenuEntities);
}
