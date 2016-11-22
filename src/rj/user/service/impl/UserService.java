package rj.user.service.impl;

import org.springframework.transaction.annotation.Transactional;
import rj.user.dao.IUserDao;
import rj.user.entity.User;
import rj.user.service.IUserService;
import rj.utils.MailUitls;
import rj.utils.UUIDUtils;

/**
 * 业务逻辑层要进行事物的管理
 * @author 隽
 *
 */
@Transactional
public class UserService implements IUserService{

    //注入dao
    private IUserDao userDao;
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    public IUserDao getUserDao() {
        return userDao;
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    //业务层完成注册的方法
    public void save(User user) {
        user.setState(0);//设置用户的状态：0为未激活，1为激活
        String code=UUIDUtils.getUUID();
        user.setCode(code);//得到激活码
        userDao.save(user);
        //发送激活邮件
        MailUitls.sendMail(user.getEmail(), code);
    }
    //业务层根据激活码查找的功能
    public User findByCode(String code) {
        return userDao.findByCode(code);
    }

    //业务层更新操作
    public void update(User user) {
        userDao.update(user);
    }
    //业务层登陆的操作
    public User login(User user){
        return userDao.login(user);
    }

}