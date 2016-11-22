package rj.user.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import rj.user.dao.IUserDao;
import rj.user.entity.User;

import java.util.List;
/**
 * 继承HibernateDaoSupport模板化操作，简便开发
 * @author 隽
 *
 */
public class UserDao extends HibernateDaoSupport implements IUserDao {



    public User findByUsername(String username) {
        String hql = "from User where username = ?";
        List<User> list = this.getHibernateTemplate().find(hql, username);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;

    }

    // 注册用户存入数据库代码实现
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    /**
     * 通过激活码查找用户代码实现
     */
    public User findByCode(String code) {

        String hql="from User where code=?";
        List<User> list = this.getHibernateTemplate().find(hql, code);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public void update(User user) {
        this.getHibernateTemplate().update(user);

    }

    //登陆
    public User login(User user) {
        String hql="from User where username = ? and password = ? and state = ?";
        List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;

    }



}
