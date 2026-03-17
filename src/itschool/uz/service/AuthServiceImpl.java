package itschool.uz.service;

import itschool.uz.model.User;
import itschool.uz.storage.Storage;

public class AuthServiceImpl implements AuthService {
     private long userLastId = 0;
    @Override
    public User login(String email, String password) {
        return null;
    }
  // todo register metodni togri impl qilish
    @Override
    public User register(User user) {
        if(user.getEmail() == null || user.getPassword() == null || user.getFullName() == null) {
            System.out.println("To'dirilmagan bo'sh maydonlarni to'diring");
            return  null;
        }
        user.setId(++userLastId);
        Storage.USER_STORAGE[(int) (user.getId() - 1)] = user;
        return user;
    }
}
