package top.trumandu.module.system.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.log.domain.SysLogQueryDTO;
import top.trumandu.module.system.log.domain.SysLogVO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
@RestController
public class SysLogController {
    @Autowired
    SysLogService sysLogService;

    @PostMapping("/sys_log/query")
    public ResponseDTO<List<SysLogVO>> query(@RequestBody SysLogQueryDTO queryDTO) {
        return sysLogService.query(queryDTO);
    }
}
