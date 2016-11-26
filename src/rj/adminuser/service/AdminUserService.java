package rj.adminuser.service;

import org.springframework.transaction.annotation.Transactional;
import rj.adminuser.dao.AdminUserDao;
import rj.adminuser.entity.AdminUser;

/**
 * Created by 隽 on 2016/11/26.
 */
@Transactional
public class AdminUserService {
    private AdminUserDao adminUserDao;

    public void setAdminUserDao(AdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    public AdminUser login(AdminUser adminUser) {
        return adminUserDao.login(adminUser);
    }
}
