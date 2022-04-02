package top.trumandu.module.system.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.base.BaseController;
import top.trumandu.common.domain.PageResultDTO;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.user.domain.UserBaseDTO;
import top.trumandu.module.system.user.domain.UserQueryDTO;
import top.trumandu.module.system.user.domain.UserUpdateDTO;
import top.trumandu.module.system.user.domain.UserVO;

import javax.validation.Valid;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @PostMapping("/user/add")
    public ResponseDTO addUser(@Valid @RequestBody UserBaseDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/user/update")
    public ResponseDTO updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userUpdateDTO);
    }

    @GetMapping("/user/get/{id}")
    public ResponseDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user/get/all")
    public ResponseDTO getAllUser() {
        return userService.listAllUser();
    }

    @PostMapping("/user/query")
    public ResponseDTO<PageResultDTO<UserVO>> query(@Valid @RequestBody UserQueryDTO userQueryDTO) {
        return userService.query(userQueryDTO);
    }


    @DeleteMapping("/user/delete/{id}")
    public ResponseDTO deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
