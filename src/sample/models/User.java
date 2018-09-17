package sample.models;

import java.util.Date;

public class User {
    private String name; //name of student
    private String school; //school of student
    private Credential credential; //login credential of the student
    private int scoreMathTest; //math test score of the user
    private int scoreImageTest; //recognize critical info test score
    private int scoreSpellingTest; //spelling test score
    private int scoreListeningTest;
    private int scoreWritingTest;
    private boolean hasFinishedMath; //indicate if user has finished Math test or not
    private boolean hasFinishImage;
    private boolean hasFinishSpelling;
    private boolean hasFinishListening;
    private boolean hasFinishWriting;

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
        scoreImageTest = 0;
        scoreSpellingTest = 0;
        scoreListeningTest = 0;
        scoreWritingTest = 0;
        hasFinishedMath = false;
        hasFinishImage = false;
        hasFinishListening = false;
        hasFinishSpelling = false;
        hasFinishWriting = false;
    }

    public int getScoreMathTest() {
        return scoreMathTest;
    }

    public int getScoreImageTest() {
        return scoreImageTest;
    }

    public int getScoreSpellingTest() {
        return scoreSpellingTest;
    }

    public int getScoreListeningTest() {
        return scoreListeningTest;
    }

    public int getScoreWritingTest() {
        return scoreWritingTest;
    }

    /**
     * Add score to user's math test
     * @param scoreAdded score added
     */
    public void addScoreMathTest(int scoreAdded) {
        this.scoreMathTest += scoreAdded;
    }

    /**
     * Add score to user's image test
     * @param scoreAdded score added
     */
    public void addScoreImageTest(int scoreAdded) {
        this.scoreImageTest += scoreAdded;
    }

    /**
     * Add score to user's spelling test
     * @param scoreAdded score added
     */
    public void addScoreSpellingTest(int scoreAdded) {
        this.scoreSpellingTest += scoreAdded;
    }

    /**
     * Add score to user's spelling test
     * @param scoreAdded score added
     */
    public void addScoreListeningTest(int scoreAdded) {
        this.scoreListeningTest += scoreAdded;
    }

    public void setScoreListeningTest(int scoreListeningTest) {
        this.scoreListeningTest = scoreListeningTest;
    }

    public boolean isHasFinishedMath() {
        return hasFinishedMath;
    }

    public boolean isHasFinishImage() {
        return hasFinishImage;
    }

    public boolean isHasFinishSpelling() {
        return hasFinishSpelling;
    }

    public boolean isHasFinishListening() {
        return hasFinishListening;
    }

    public boolean isHasFinishWriting() {
        return hasFinishWriting;
    }

    public void setHasFinishedMath(boolean hasFinishedMath) {
        this.hasFinishedMath = hasFinishedMath;
    }

    public void setHasFinishImage(boolean hasFinishImage) {
        this.hasFinishImage = hasFinishImage;
    }

    public void setHasFinishSpelling(boolean hasFinishSpelling) {
        this.hasFinishSpelling = hasFinishSpelling;
    }

    public void setHasFinishListening(boolean hasFinishListening) {
        this.hasFinishListening = hasFinishListening;
    }

    public void setHasFinishWriting(boolean hasFinishWriting) {
        this.hasFinishWriting = hasFinishWriting;
    }
}
