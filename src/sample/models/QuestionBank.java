package sample.models;

import java.util.ArrayList;
import java.util.Random;

public class QuestionBank {
    private static QuestionBank instance; //instance of the question bank, should be Singleton
    private ArrayList<MathQuestion> mathQuestions = new ArrayList<>(); //list of all math questions
    private ArrayList<ImageQuestion> imageQuestions = new ArrayList<>(); //list of all image questions
    private ArrayList<SpellingQuestion> spellingQuestions = new ArrayList<>(); //list of all spelling questions

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
        generateMathQuestions();
        generateImageQuestions();
        generateSpellingQuestions();
    }

    /**
     * Create all math questions
     */
    private void generateMathQuestions() {
        mathQuestions.add(new MathQuestion("The number of 3-digit number divisible by 6, is ...", QuestionDifficulty.TOUGHER,
                "149", "166", "150", "151", "150"));
        mathQuestions.add(new MathQuestion("What is 1004 divided by 2?" , QuestionDifficulty.TOUGH,
                "52", "502", "520", "5002", "502"));
        mathQuestions.add(new MathQuestion("125 gallons of mixture contains 20% water. How much water should be added such" +
                "that water content is raised to 25%?", QuestionDifficulty.TOUGHEST, "8.5 gallons", "9.5 gallons",
                "7.5 gallons", "8 and 1/3 gallons", "8 and 1/3 gallons"));
        mathQuestions.add(new MathQuestion("Which of the following numbers gives 240 when added to its own square",
                QuestionDifficulty.TOUGHER, "15", "16", "17", "18", "15"));
        mathQuestions.add(new MathQuestion("A clock strikes once at 1 o'clock, twice at 2 o'clock, thrice at 3 o'clock. How many " +
                "times will it strike in 24 hours", QuestionDifficulty.TOUGHEST, "78", "136", "156", "196", "156"));
        mathQuestions.add(new MathQuestion("The average of first 50 natural numbers is ...?", QuestionDifficulty.TOUGH,
                "21", "22", "23", "25", "25"));
        mathQuestions.add(new MathQuestion("What is 121 times 11?", QuestionDifficulty.TOUGH, "1331", "1313", "1133", "3131", "1331"));
        mathQuestions.add(new MathQuestion("In the following series a wrong number is given. What is that number?  " +
                "4 5 10 18 34 59 95", QuestionDifficulty.TOUGHEST, "5", "10", "18", "34", "10"));
        mathQuestions.add(new MathQuestion("A clock reads 4:30. If the minute hand points East. in what direction will the hour hand point?",
                QuestionDifficulty.TOUGHER, "North", "North-West", "North-East", "South-East", "North-East"));
        mathQuestions.add(new MathQuestion("The sum of squares of two numbers is 80 and the square of difference between the two numbers is 36. " +
                "Find the product of two numbers", QuestionDifficulty.TOUGHEST, "11", "22", "33", "44", "22"));
    }

    /**
     * Create image questions
     */
    private void generateImageQuestions() {
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGH, "Point to A note", "http://www.musicawareness.com/images/CstaffTr.gif",
                204,216, 50, 60));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGHER, "Point to President Obama", "https://i.ytimg.com/vi/qiiBjayukos/hqdefault.jpg",
                14, 34, 112, 140));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGHEST, "Point to Hulk", "https://images-na.ssl-images-amazon.com/images/I/61JbK%2BGDqgL._SX425_.jpg",
                125, 164, 99, 113));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGHER, "Point to Australia", "https://geology.com/world/world-physical-map.jpg",
                237, 267, 151, 163));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGH, "Point to the motorbike", "https://motorbikewriter.com/content/uploads/2014/07/image26.jpg",
                118, 159, 98, 179));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGHEST, "Point to Golden Gate Bridge", "https://assets.digicorus.corusdigitaldev.com/wp-content/uploads/sites/19/2018/06/04142757/CartoonNetwork_WeBareBears_462x3861.jpg",
                180, 214, 133, 144));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGH, "Point to the corn of the horse", "https://freedesignfile.com/upload/2017/10/Cartoon-unicorns-cute-vectors-07.jpg",
                49,62,26, 50));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGHER, "Point to Manchester United", "https://blogepl.files.wordpress.com/2012/10/english-premier-league-clubs.jpg?w=500&h=400",
                153, 190, 79, 114));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGHEST, "Point to the name of highest ranking player", "https://2.bp.blogspot.com/-NUlHFOoIPFk/WIiilc6RVPI/AAAAAAAAII0/jsuZvE-zlJgKZgqEH02xFfNDP7WcbS9MQCLcB/s1600/LIVE%2BATP.JPG",
                33, 82, 0, 5));
        imageQuestions.add(new ImageQuestion(QuestionDifficulty.TOUGHEST, "Point to The Great Wall", "https://upload.wikimedia.org/wikipedia/commons/f/fb/New7Wonders.jpg",
                0, 72, 53, 113));
    }

    private void generateSpellingQuestions() {
        String path = "./src/sample/resources/";
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGH, path + "gallon.mp3", "gallon"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGH, path + "generate.mp3", "generate"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGH, path + "generous.mp3", "generous"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGHER, path + "jasmine.mp3", "jasmine"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGHER, path + "jerusalem.mp3", "jerusalem"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGHER, path + "juvenile.mp3", "juvenile"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGHEST, path + "zeus.mp3", "zeus"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGHEST, path + "zodiac.mp3", "zodiac"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGHEST, path + "zuchini.mp3", "zuchini"));
        spellingQuestions.add(new SpellingQuestion(QuestionDifficulty.TOUGHEST, path + "zurich.mp3", "zurich"));
    }

    /**
     * Generate a random question from the bank
     * @param difficulty the difficulty of the question wanting to retrieve
     * @return a question from the question bank
     */
    public MathQuestion generateRandomQuestion(QuestionDifficulty difficulty) {
        MathQuestion result = mathQuestions.get(new Random().nextInt(mathQuestions.size()));
        if (result.getDifficulty() == difficulty)
            return result;
        return generateRandomQuestion(difficulty);
    }

    /**
     * Generate a random image question from the bank
     * @param difficulty the difficulty of the question wanting to retrieve
     * @return a question from the question bank
     */
    public ImageQuestion generateRandomImageQuestion(QuestionDifficulty difficulty) {
        ImageQuestion result = imageQuestions.get(new Random().nextInt(mathQuestions.size()));
        if (result.getDifficulty() == difficulty)
            return result;
        return generateRandomImageQuestion(difficulty);
    }

    /**
     * Generate a random  question from the bank
     * @param difficulty the difficulty of the question wanting to retrieve
     * @return a question from the question bank
     */
    public SpellingQuestion generateRandomSpellingQuestion(QuestionDifficulty difficulty) {
        SpellingQuestion result = spellingQuestions.get(new Random().nextInt(spellingQuestions.size()));
        if (result.getDifficulty() == difficulty)
            return result;
        return generateRandomSpellingQuestion(difficulty);
    }
}
