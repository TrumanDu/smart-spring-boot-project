package top.trumandu.module.system.log;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.log.domain.SysLogEntity;
import top.trumandu.module.system.log.domain.SysLogQueryDTO;
import top.trumandu.module.system.log.domain.SysLogVO;
import top.trumandu.util.PageUtil;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
@Service
public class SysLogService {
    @Autowired
    SysLogDao sysLogDao;


    public void add(SysLogEntity sysLogEntity){
        sysLogDao.insert(sysLogEntity);
    }

    public ResponseDTO<List<SysLogVO>> query(SysLogQueryDTO queryDTO){
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<SysLogVO> dbResult = sysLogDao.selectSysLogList(queryDTO);
        Page<SysLogVO> pageInfo = (Page<SysLogVO>) dbResult;
        return ResponseDTO.success(PageUtil.convert2PageResult(pageInfo));
    }
}
