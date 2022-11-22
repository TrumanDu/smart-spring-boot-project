package top.trumandu.module.system.log;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.Response;
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
    private final SysLogDao sysLogDao;

    public SysLogService(SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }


    public void add(SysLogEntity sysLogEntity) {
        sysLogDao.insert(sysLogEntity);
    }

    public Response query(SysLogQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<SysLogVO> dbResult = sysLogDao.selectSysLogList(queryDTO);
        Page<SysLogVO> pageInfo = (Page<SysLogVO>) dbResult;
        return Response.ok().data(PageUtil.convert2PageResult(pageInfo));
    }
}
