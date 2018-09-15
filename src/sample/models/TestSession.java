package sample.models;

public class TestSession {
    private static TestSession instance;
    private User currentUser;

    private TestSession() {
    }

    public static TestSession getInstance() {
        if (instance == null)
            instance = new TestSession();
        return instance;
    }

    public void setUser(User user) {
        currentUser = user;
    }

    public void endSession() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
