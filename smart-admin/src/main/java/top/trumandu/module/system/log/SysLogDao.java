package top.trumandu.module.system.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.trumandu.module.system.log.domain.SysLogEntity;
import top.trumandu.module.system.log.domain.SysLogQueryDTO;
import top.trumandu.module.system.log.domain.SysLogVO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
    /**
     * 分页查询符合条件的日志
     *@param queryDTO
     * @return
     */
    List<SysLogVO> selectSysLogList(@Param("queryDTO") SysLogQueryDTO queryDTO);
}
