package top.trumandu.module.system.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.PageResultDTO;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.user.domain.*;
import top.trumandu.util.BeanUtil;
import top.trumandu.util.PageUtil;

import java.util.List;


/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 增加用戶
     *
     * @param userDTO
     * @return
     */
    public ResponseDTO addUser(UserBaseDTO userDTO) {
        UserEntity userEntity = BeanUtil.copy(userDTO, UserEntity.class);
        userDao.insert(userEntity);
        return ResponseDTO.success();
    }

    /**
     * 根据ID更新用户信息
     *
     * @param userUpdateDTO
     * @return
     */
    public ResponseDTO updateUser(UserUpdateDTO userUpdateDTO) {
        UserEntity userEntity = BeanUtil.copy(userUpdateDTO, UserEntity.class);
        userDao.updateById(userEntity);
        return ResponseDTO.success();
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public ResponseDTO<UserBaseDTO> getUser(Long id) {
        UserEntity userEntity = userDao.selectById(id);
        UserVO userVO = BeanUtil.copy(userEntity, UserVO.class);
        return ResponseDTO.success(userVO);
    }

    /**
     * 查询所有未被删除的用户
     *
     * @return
     */
    public ResponseDTO<List<UserVO>> listAllUser() {
        List<UserVO> dbResult = userDao.listAllUser();
        return ResponseDTO.success(dbResult);
    }

    /**
     * 根据条件分页查询用户列表
     * @param queryDTO
     * @return
     */
    public ResponseDTO<PageResultDTO<UserVO>> query(UserQueryDTO queryDTO){
        PageHelper.startPage(queryDTO.getPageNum(),queryDTO.getPageSize());
        List<UserVO> dbResult = userDao.selectUserList(queryDTO);
        Page<UserVO> pageInfo= (Page<UserVO>) dbResult;
        return  ResponseDTO.success(PageUtil.convert2PageResult(pageInfo));
    }

    /**
     * 根据id删除指定用户信息
     *
     * @param id
     * @return
     */
    public ResponseDTO deleteUser(Long id) {
        userDao.disableUserById(id);
        return ResponseDTO.success();
    }
}
