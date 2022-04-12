package top.trumandu.module.system.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.PageResultDTO;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.SelectDTO;
import top.trumandu.module.system.user.domain.UserBaseDTO;
import top.trumandu.module.system.user.domain.UserQueryDTO;
import top.trumandu.module.system.user.domain.UserUpdateDTO;
import top.trumandu.module.system.user.domain.UserVO;
import top.trumandu.util.SmartCurrentUserUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/currentUser")
    public ResponseDTO getCurrentUserInfo(){
        Long currentUserId = SmartCurrentUserUtil.getCurrentUserId();
        return ResponseDTO.success(userService.getCurrentUser(currentUserId));
    }

    @SysLog(operation = "增加用户", params = true)
    @PostMapping("/user/add")
    public ResponseDTO addUser(@Valid @RequestBody UserBaseDTO userDTO) {
        Long currentUserId = SmartCurrentUserUtil.getCurrentUserId();
        return userService.addUser(userDTO, currentUserId);
    }

    @SysLog(operation = "修改用户")
    @PutMapping("/user/update")
    public ResponseDTO updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        Long currentUserId = SmartCurrentUserUtil.getCurrentUserId();
        return userService.updateUser(userUpdateDTO, currentUserId);
    }

    @GetMapping("/user/get/{id}")
    public ResponseDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user/list")
    public ResponseDTO getAllUser() {
        return userService.listAllUser();
    }

    @PostMapping("/user/query")
    public ResponseDTO<PageResultDTO<UserVO>> query(@Valid @RequestBody UserQueryDTO userQueryDTO) {
        return userService.query(userQueryDTO);
    }

    @SysLog(operation = "删除用户")
    @DeleteMapping("/user/delete/{id}")
    public ResponseDTO deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseDTO.success();
    }

    /**
     * 查询待分配角色的用户
     * @return
     */
    @GetMapping("/user/list/select")
    public ResponseDTO<List<SelectDTO>> getSelectData() {
        return ResponseDTO.success(userService.selectUserSelectList());
    }
}
