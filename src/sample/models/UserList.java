package sample.models;

import java.util.ArrayList;
import java.util.Date;

public class UserList {
    private ArrayList<User> users = new ArrayList<>();
    private static UserList instance = new UserList();

    private UserList() {

    }

    public static UserList getInstance() {
        if (instance == null)
            instance = new UserList();
        return instance;
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

    public boolean checkCredential(String id, String pin) {
        for (User user: users) {
            if (user.getCredential().getId().equals(id) && user.getCredential().getPin().equals(pin))
                if (!checkPINExpired(user)) {
                    TestSession.getInstance().setUser(user);
                    return true;
                }
        }
        return false;
    }

    public boolean checkIDAlreadyExisted(String id) {
        for (User user: users) {
            if (user.getCredential().getId().equals(id))
                return true;
        }
        return false;
    }

    private boolean checkPINExpired(User user) {
        Date now = new Date();
        long timeGap = now.getTime() - user.getCredential().getTimeRegister().getTime();
        if (timeGap > 60*1000*20) {
            users.remove(user);
            return true;
        }
        return false;
    }

}
