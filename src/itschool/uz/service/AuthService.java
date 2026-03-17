package itschool.uz.service;

import itschool.uz.model.User;

public interface AuthService {
    User login(String email, String password);
    User register(User user);
}
