package top.trumandu.module.system.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.PageResultDTO;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.SelectDTO;
import top.trumandu.constant.CommonConst;
import top.trumandu.module.system.menu.SysMenuService;
import top.trumandu.module.system.menu.domain.SysMenuVO;
import top.trumandu.module.system.role.RoleDao;
import top.trumandu.module.system.role.domain.RoleVO;
import top.trumandu.module.system.user.domain.*;
import top.trumandu.util.BeanUtil;
import top.trumandu.util.PageUtil;
import top.trumandu.util.SmartDigestUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@Service
public class UserService {
    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    private UserDao userDao;
    @Autowired
    public RoleDao roleDao;

    public CurrentUserDTO getCurrentUser(Long currentUserId) {
        UserEntity userEntity = userDao.selectById(currentUserId);
        RoleVO role = roleDao.selectRoleByUserId(currentUserId);
        CurrentUserDTO currentUser = new CurrentUserDTO(userEntity.getId(), userEntity.getName(), userEntity.getUsername(), userEntity.getEmail());
        if (role != null) {
            currentUser.setRole(role);
            List<String> hasRoutes = new ArrayList<>();
            List<SysMenuVO> menuVOList = sysMenuService.listMenuListByRole(role.getId());
            List<MenuData> menuDataList = new ArrayList<>();
            convert(menuDataList, hasRoutes, menuVOList);
            currentUser.setMenuDataList(menuDataList);
            currentUser.setHasRoutes(hasRoutes);
        }
        return currentUser;
    }

    private void convert(List<MenuData> menuDataList, List<String> hasRoutes, List<SysMenuVO> menuVOList) {
        menuVOList.forEach(sysMenuVO -> {
            MenuData menuData = new MenuData();
            menuData.setIcon(sysMenuVO.getMenuIcon());
            menuData.setName(sysMenuVO.getMenuName());
            menuData.setPath(sysMenuVO.getMenuUrl());
            hasRoutes.add(sysMenuVO.getMenuUrl());
            if (sysMenuVO.getChildren() != null) {
                List<MenuData> routes = new ArrayList<>();
                convert(routes, hasRoutes, sysMenuVO.getChildren());
                menuData.setRoutes(routes);
            }
            menuDataList.add(menuData);
        });
    }

    /**
     * 增加用戶
     *
     * @param userDTO
     * @return
     */
    public ResponseDTO addUser(UserBaseDTO userDTO, Long currentUserId) {
        int count = userDao.countByUsername(userDTO.getUsername());
        if (count > 0) {
            return ResponseDTO.failure("Username[" + userDTO.getUsername() + "] already exists.");
        }
        UserEntity userEntity = BeanUtil.copy(userDTO, UserEntity.class);
        String encryptPassword = SmartDigestUtils.encryptPassword(CommonConst.Password.SALT_FORMAT, userDTO.getPassword());
        userEntity.setPassword(encryptPassword);
        userEntity.setCreateUserId(currentUserId);

        userDao.insert(userEntity);
        return ResponseDTO.success();
    }

    /**
     * 根据ID更新用户信息
     *
     * @param userUpdateDTO
     * @return
     */
    public ResponseDTO updateUser(UserUpdateDTO userUpdateDTO, Long currentUserId) {
        UserEntity userEntity = BeanUtil.copy(userUpdateDTO, UserEntity.class);
        String encryptPassword = SmartDigestUtils.encryptPassword(CommonConst.Password.SALT_FORMAT, userUpdateDTO.getPassword());
        userEntity.setPassword(encryptPassword);
        userEntity.setLastEditUserId(currentUserId);
        UserEntity dbUser = userDao.selectById(userUpdateDTO.getId());
        userEntity.setCreateUserId(dbUser.getCreateUserId());
        userEntity.setCreateTime(dbUser.getCreateTime());
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
     *
     * @param queryDTO
     * @return
     */
    public ResponseDTO<PageResultDTO<UserVO>> query(UserQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<UserVO> dbResult = userDao.selectUserList(queryDTO);
        Page<UserVO> pageInfo = (Page<UserVO>) dbResult;
        return ResponseDTO.success(PageUtil.convert2PageResult(pageInfo));
    }

    /**
     * 根据id删除指定用户信息
     *
     * @param id
     * @return
     */
    public void deleteUser(Long id) {
        userDao.disableUserById(id);
    }

    /**
     * 查询待分配角色用户
     *
     * @return
     */
    public List<SelectDTO> selectUserSelectList() {
        List<UserVO> userVOList = userDao.listNoRoleUser();
        List<SelectDTO> list = new ArrayList<>(userVOList.size());
        userVOList.forEach(userVO -> {
            list.add(new SelectDTO(userVO.getName(), userVO.getId().toString()));
        });
        return list;
    }
}
