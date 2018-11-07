package rj.user.dao;

import rj.user.entity.User;

import java.util.List;

/**
 * Created by 隽 on 2016/11/20.
 */
public interface IUserDao {
    User findByUsername(String username);
    void save(User user);
    User findByCode(String code);
    void update(User existu);
    User login(User user);
    int findCount();
    List<User> findByPage(int begin, int limit);
    User findByUid(Integer uid);
    void delete(User existUser);
}
