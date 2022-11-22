package top.trumandu.module.system.log;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.trumandu.common.domain.Response;
import top.trumandu.module.system.log.domain.SysLogQueryDTO;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
@RestController
public class SysLogController {
    private final SysLogService sysLogService;

    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @PostMapping("/sys_log/query")
    public Response query(@RequestBody SysLogQueryDTO queryDTO) {
        return sysLogService.query(queryDTO);
    }
}
