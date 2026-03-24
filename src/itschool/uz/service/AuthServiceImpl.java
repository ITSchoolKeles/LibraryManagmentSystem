package itschool.uz.service;

import itschool.uz.enums.UserRole;
import itschool.uz.model.User;
import itschool.uz.storage.Storage;

public class AuthServiceImpl implements AuthService {
    static {
        User admin =   new User(
                100,
                "Admin",
                "admin@gmail.com",
                "admin123",
                UserRole.ADMIN);
          Storage.USER_STORAGE[0] = admin;
          User user = new User(
                  1,
                  "Elyor Azimov",
                  "elyor@gmail.com",
                  "ea1202",
                    UserRole.USER
          );
          Storage.USER_STORAGE[1] = user;
        System.out.println("Admin has  been successfully added to the system");
    }
     private long userLastId = 1;
    @Override
    public User login(String email, String password) {
        User user = null;
        for(User u : Storage.USER_STORAGE){
            if(u != null){
                if(u.getEmail().equals(email)){
                    user = u; break;
                }
            }
        }
        if(user == null){
            System.out.println("Email does not exist");
            return null;
        }
        if(!user.getPassword().equals(password)){
            System.out.println("Password is incorrect");
            return null;
        }

        return user;

    }
    @Override
    public User register(User user) {
        if(user.getEmail() == null || user.getPassword() == null || user.getFullName() == null
           || user.getFullName().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            System.out.println("To'dirilmagan bo'sh maydonlarni to'diring");
            return  null;
        }

        for(User u : Storage.USER_STORAGE){
            if(u != null && u.getEmail().equals(user.getEmail())){
                System.out.println("This email is already registered");
                return null;
            }
        }
        user.setId(++userLastId);

     for(int i = 0; i < Storage.USER_STORAGE.length; i++){
         if(Storage.USER_STORAGE[i] == null){
             Storage.USER_STORAGE[i] = user;
             break;
         }
     }
        return user;
    }
}
