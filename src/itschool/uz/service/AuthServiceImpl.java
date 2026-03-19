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
        System.out.println("Admin has  been successfully added to the system");
    }
     private long userLastId = 1;
    @Override
    public User login(String email, String password) {
        User user = null;
        for(User u : Storage.USER_STORAGE){
            if(u  == null) continue;
            else{
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
        // todo emailni unikal ekanligini tekshirish
        //  agar boshqa userda shu email mavjud bo'lsa ro'yhatdan o'tkazmaslik
        user.setId(++userLastId);
        // todo index ga bogliq bolgmagan holsa saqlash
        Storage.USER_STORAGE[(int) (user.getId() - 1)] = user;
        return user;
    }
}
