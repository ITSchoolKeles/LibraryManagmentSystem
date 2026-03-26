package itschool.uz.service;

import itschool.uz.enums.UserRole;
import itschool.uz.model.User;
import itschool.uz.storage.Storage;

public class UserServiceImpl implements UserService {
    @Override
    public void viewAllUsers() {
        boolean isUserListEmpty = true;

        for(User u : Storage.USER_STORAGE){
            if(u != null && u.getRole() == UserRole.USER){
                System.out.println(u);
                isUserListEmpty = false;
            }
        }
        if(isUserListEmpty){
            System.out.println("There is no user in the system yet");
        }
    }
}
