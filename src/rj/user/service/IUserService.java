package rj.user.service;

import rj.user.entity.User;
import rj.utils.PageBean;

/**
 * Created by 隽 on 2016/11/20.
 */
public interface IUserService {
    User findByUsername(String username);
    void save(User user);
    User findByCode(String code);
    void update(User user);
    User login(User user);
    PageBean<User> findByPage(Integer page);
    User findByUid(Integer uid);
    void delete(User existUser);
}
