package service;

import entity.User;

public class Uservice {

    public String list() {
        User user = new User();
        user.setName("王八");
        return user.getName();
    }


}
