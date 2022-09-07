package rikkei.academy.service;

import rikkei.academy.model.User;

import java.util.List;

public interface IUserService {
    List<User> selectAllUsers();
    void addUserTransaction(User user, int[] permission);
}
