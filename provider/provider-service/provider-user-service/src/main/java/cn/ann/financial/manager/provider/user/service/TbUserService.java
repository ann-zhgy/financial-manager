package cn.ann.financial.manager.provider.user.service;

import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.config.UserProperties;
import cn.ann.financial.manager.provider.user.domain.TbPermission;
import cn.ann.financial.manager.provider.user.domain.TbRole;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import cn.ann.financial.manager.provider.user.mapper.TbUserMapper;
import cn.ann.financial.manager.provider.user.param.UpdateEnableParam;
import cn.ann.financial.manager.provider.user.param.UpdateFamilyParam;
import cn.ann.financial.manager.provider.user.param.UpdateOpenParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-20:19:16
 */
@Service(version = "1.0.0")
public class TbUserService implements TbUserServiceApi {
    @Resource
    private UserProperties properties;

    @Resource(name = "tbUserMapper")
    private TbUserMapper tbUserMapper;

    @Resource(name = "passwordEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 通过用户名获取用户
     *
     * @param name 用户名
     * @return 用户 {@link TbUser}
     */
    @Override
    public TbUser get(String name) {
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("username", name);
        return tbUserMapper.selectOneByExample(example);
    }

    /**
     * 通过 id 获取用户
     *
     * @param id id
     * @return {@link TbUser}
     */
    @Override
    public TbUser get(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过邮箱获取用户
     *
     * @param email 邮箱
     * @return {@link TbUser}
     */
    @Override
    public TbUser getByEmail(String email) {
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email", email);
        return tbUserMapper.selectOneByExample(example);
    }

    /**
     * 通过用户名获取 id
     *
     * @param name 用户名
     * @return id
     */
    @Override
    public Long getIdByName(String name) {
        return tbUserMapper.getIdByName(name);
    }

    /**
     * 通过 family 获取用户
     *
     * @param familyId 家庭 id
     * @return {@link List<TbUser>}
     */
    @Override
    public List<TbUser> getListByFamilyId(Long familyId) {
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("familyId", familyId);
        return tbUserMapper.selectByExample(example);
    }

    @Override
    public List<Long> getIdsByFamilyId(Long familyId) {
        return tbUserMapper.getIdsByFamilyId(familyId);
    }

    /**
     * 获取用户的权限
     *
     * @param username username
     * @return 权限list {@link TbPermission}
     */
    @Override
    public List<String> getUrls(String username) {
        return tbUserMapper.getUrls(username);
    }

    /**
     * 获取可以展示在父结点上的用户权限，可用来做菜单
     *
     * @param userId 用户id
     * @return 权限list {@link TbPermission}
     */
    @Override
    public List<TbPermission> getMenu(Long userId) {
        return tbUserMapper.getMenu(userId);
    }

    /**
     * 获取用户的角色
     *
     * @param userId 用户 id
     * @return {@link TbRole}
     */
    @Override
    public List<TbRole> getRoles(Long userId) {
        return tbUserMapper.getRoles(userId);
    }

    /**
     * 向数据库插入用户
     *
     * @param user {@link TbUser}
     * @return 数据库插入记录条数
     */
    @Override
    public InsertResult<TbUser> insert(TbUser user) {
        initTbUser(user);
        int result = tbUserMapper.insertSelective(user);
        InsertResult<TbUser> insertResult = new InsertResult<>();
        insertResult.setT(user);
        insertResult.setModifyCount(result);
        return insertResult;
    }

    /**
     * 更新数据库中的用户信息
     *
     * @param user {@link TbUser}
     * @return 数据库更新记录条数
     */
    @Override
    public int update(TbUser user) {
        TbUser oldUser = get(user.getId());

        if (StringUtils.isNotBlank(user.getNickName())) {
            oldUser.setNickName(user.getNickName());
        }
        if (user.getBirth() != null) {
            oldUser.setBirth(user.getBirth());
        }
        if (user.getGender() != null && user.getGender() != 0) {
            oldUser.setGender(user.getGender());
        }
        if (user.getOpen() != null &&
                (user.getOpen() == ProviderConstant.OPEN
                        || user.getOpen() == ProviderConstant.NO_OPEN)) {
            oldUser.setOpen(user.getOpen());
        }
        oldUser.setUpdated(new Date());
        return tbUserMapper.updateByPrimaryKey(oldUser);
    }

    /**
     * 更新用户关联家庭关系
     *
     * @param param {@link UpdateFamilyParam}
     */
    @Override
    public int updateFamily(UpdateFamilyParam param) {
        TbUser oldUser = get(param.getId());
        oldUser.setId(param.getId());
        oldUser.setFamilyId(param.getFamilyId());
        return tbUserMapper.updateByPrimaryKey(oldUser);
    }

    /**
     * 更新 open 字段。（设置公开）
     *
     * @param param {@link TbUser}
     * @return 数据库记录更新条数
     */
    @Override
    public int updateOpen(UpdateOpenParam param) {
        TbUser oldUser = get(param.getId());
        oldUser.setOpen(param.getOpen());
        oldUser.setUpdated(new Date());
        return tbUserMapper.updateByPrimaryKey(oldUser);
    }

    @Override
    public int updateEnable(UpdateEnableParam param) {
        TbUser oldUser = get(param.getId());
        oldUser.setEnable(param.getEnable());
        oldUser.setUpdated(new Date());
        return tbUserMapper.updateByPrimaryKey(oldUser);
    }


    /**
     * 删除用户
     *
     * @param username 用户名
     * @return 数据库删除记录条数
     */
    @Override
    public int delete(String username) {
        TbUser user = get(username);
        return tbUserMapper.delete(user);
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 数据库更新记录条数
     */
    @Override
    public int modifyPassword(String username, String password) {
        TbUser oldUser = get(username);
        oldUser.setPassword(passwordEncoder.encode(password));
        oldUser.setUpdated(new Date());
        return tbUserMapper.updateByPrimaryKey(oldUser);
    }

    /**
     * 修改头像
     *
     * @param username 用户名
     * @param path     图片路径
     * @return 数据库更新记录条数
     */
    @Override
    public int modifyIcon(String username, String path) {
        TbUser oldUser = get(username);
        oldUser.setIcon(path);
        oldUser.setUpdated(new Date());
        return tbUserMapper.updateByPrimaryKey(oldUser);
    }

    /**
     * 初始化 用户 对象
     *
     * @param user {@link TbUser}
     */
    private void initTbUser(TbUser user) {
        user.setId(null);
        if (user.getNickName() == null) {
            user.setNickName(user.getUsername());
        }
        user.setCode(new Random().nextInt(999999) + "");
        user.setEnable(ProviderConstant.USER_DISABLE);
        user.setOpen(ProviderConstant.NO_OPEN);
        user.setIcon(properties.getImages().get((int) (Math.random() * properties.getImages().size())));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
    }

}
