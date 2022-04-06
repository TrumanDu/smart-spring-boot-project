package top.trumandu.module.system.org;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.trumandu.module.system.org.domain.SysOrgEntity;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
@Mapper
public interface SysOrgDao extends BaseMapper {
    /**
     * 查询所有的组织信息列表
     * @return
     */
    List<SysOrgEntity> selectSysOrgList();
}
