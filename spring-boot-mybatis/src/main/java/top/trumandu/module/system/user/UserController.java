package top.trumandu.module.system.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.Response;
import top.trumandu.module.system.user.domain.UserBaseDTO;
import top.trumandu.module.system.user.domain.UserQueryDTO;
import top.trumandu.module.system.user.domain.UserUpdateDTO;
import top.trumandu.util.SmartCurrentUserUtil;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@SuppressWarnings("unused")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/currentUser")
    public Response getCurrentUserInfo() {
        Long currentUserId = SmartCurrentUserUtil.getCurrentUserId();
        return Response.ok().data(userService.getCurrentUser(currentUserId));
    }

    @SysLog(operation = "增加用户", params = true)
    @PostMapping("/user/add")
    public Response addUser(@Valid @RequestBody UserBaseDTO userDTO) {
        Long currentUserId = SmartCurrentUserUtil.getCurrentUserId();
        return userService.addUser(userDTO, currentUserId);
    }

    @SysLog(operation = "修改用户")
    @PutMapping("/user/update")
    public Response updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        Long currentUserId = SmartCurrentUserUtil.getCurrentUserId();
        return userService.updateUser(userUpdateDTO, currentUserId);
    }

    @GetMapping("/user/get/{id}")
    public Response getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user/list")
    public Response getAllUser() {
        return userService.listAllUser();
    }

    @PostMapping("/user/query")
    public Response query(@Valid @RequestBody UserQueryDTO userQueryDTO) {
        return userService.query(userQueryDTO);
    }

    @SysLog(operation = "删除用户")
    @DeleteMapping("/user/delete/{id}")
    public Response deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return Response.ok();
    }

    /**
     * 查询待分配角色的用户
     *
     */
    @GetMapping("/user/list/select")
    public Response getSelectData() {
        return Response.ok().data(userService.selectUserSelectList());
    }
}
