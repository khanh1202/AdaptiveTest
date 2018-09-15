package sample.models;

import java.util.ArrayList;
import java.util.Random;

public class QuestionBank {
    private static QuestionBank instance; //instance of the question bank, should be Singleton
    private ArrayList<Question> mathQuestions = new ArrayList<>(); //list of all math questions

    private QuestionBank() {
        initializeQuestions();
    }

    public static QuestionBank getInstance() {
        if (instance == null)
            instance = new QuestionBank();
        return instance;
    }

    /**
     * Initialize some dummy questions for the app
     */
    private void initializeQuestions() {
        mathQuestions.add(new Question("The number of 3-digit number divisible by 6, is ...", QuestionDifficulty.TOUGHER,
                "149", "166", "150", "151", "150"));
        mathQuestions.add(new Question("What is 1004 divided by 2?" , QuestionDifficulty.TOUGH,
                "52", "502", "520", "5002", "502"));
        mathQuestions.add(new Question("125 gallons of mixture contains 20% water. How much water should be added such" +
                "that water content is raised to 25%?", QuestionDifficulty.TOUGHEST, "8.5 gallons", "9.5 gallons",
                "7.5 gallons", "8 and 1/3 gallons", "8 and 1/3 gallons"));
        mathQuestions.add(new Question("Which of the following numbers gives 240 when added to its own square",
                QuestionDifficulty.TOUGHER, "15", "16", "17", "18", "15"));
        mathQuestions.add(new Question("A clock strikes once at 1 o'clock, twice at 2 o'clock, thrice at 3 o'clock. How many " +
                "times will it strike in 24 hours", QuestionDifficulty.TOUGHEST, "78", "136", "156", "196", "156"));
        mathQuestions.add(new Question("The average of first 50 natural numbers is ...?", QuestionDifficulty.TOUGH,
                "21", "22", "23", "25", "25"));
        mathQuestions.add(new Question("What is 121 times 11?", QuestionDifficulty.TOUGH, "1331", "1313", "1133", "3131", "1331"));
        mathQuestions.add(new Question("In the following series a wrong number is given. What is that number?  " +
                "4 5 10 18 34 59 95", QuestionDifficulty.TOUGHEST, "5", "10", "18", "34", "10"));
        mathQuestions.add(new Question("A clock reads 4:30. If the minute hand points East. in what direction will the hour hand point?",
                QuestionDifficulty.TOUGHER, "North", "North-West", "North-East", "South-East", "North-East"));
        mathQuestions.add(new Question("The sum of squares of two numbers is 80 and the square of difference between the two numbers is 36. " +
                "Find the product of two numbers", QuestionDifficulty.TOUGHEST, "11", "22", "33", "44", "22"));
    }

    /**
     * Generate a random question from the bank
     * @param difficulty the difficulty of the question wanting to retrieve
     * @return a question from the quesyion bank
     */
    public Question generateRandomQuestion(QuestionDifficulty difficulty) {
        Question result = mathQuestions.get(new Random().nextInt(mathQuestions.size()));
        if (result.getDifficulty() == difficulty)
            return result;
        return generateRandomQuestion(difficulty);
    }
}
