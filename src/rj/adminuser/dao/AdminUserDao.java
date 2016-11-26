package rj.adminuser.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import rj.adminuser.entity.AdminUser;

import java.util.List;

/**
 * Created by 隽 on 2016/11/26.
 */
public class AdminUserDao extends HibernateDaoSupport{
    /**
     * 管理员登陆实现
     * @param adminUser
     */
    public AdminUser login(AdminUser adminUser) {
        String hql="from AdminUser where username=? and password=?";
        List<AdminUser> list= getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        else return null;
    }
}
