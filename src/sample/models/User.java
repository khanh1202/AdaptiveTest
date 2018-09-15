package sample.models;

import java.util.Date;

public class User {
    private String name; //name of student
    private String school; //school of student
    private Credential credential; //login credential of the student
    private int scoreMathTest; //math test score of the user

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public Credential getCredential() {
        return credential;
    }

    public User(String name, String school, String id, String pin, Date timeRegister) {
        this.name = name;
        this.school = school;
        this.credential = new Credential(id, pin, timeRegister);
        scoreMathTest = 0;
    }

    public int getScoreMathTest() {
        return scoreMathTest;
    }

    /**
     * Add score to user's math test
     * @param scoreAdded score added
     */
    public void addScoreMathTest(int scoreAdded) {
        this.scoreMathTest += scoreAdded;
    }
}
