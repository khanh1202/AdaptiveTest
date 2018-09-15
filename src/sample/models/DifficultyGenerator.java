package sample.models;

public class DifficultyGenerator {
    private DifficultyGenerator() {

    }

    /**
     * Define the difficulty of next question
     * @param currentQuestion the question user has just answered
     * @param isAnswerRight whether user gets it right at the question
     * @return the difficulty of the next generated question
     */
    public static QuestionDifficulty nextQuestionDifficulty(Question currentQuestion, boolean isAnswerRight) {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                if (isAnswerRight)
                    return QuestionDifficulty.TOUGHER;
                return QuestionDifficulty.TOUGH;
            case TOUGHER:
                if (isAnswerRight)
                    return QuestionDifficulty.TOUGHEST;
                return QuestionDifficulty.TOUGH;
            default:
                if (isAnswerRight)
                    return QuestionDifficulty.TOUGHEST;
                return QuestionDifficulty.TOUGHER;
        }
    }
}
