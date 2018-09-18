package sample.models;

import java.util.ArrayList;
import java.util.Arrays;

public class GrammarChecker {
    private static ArrayList<String> conjunctions = new ArrayList<>(Arrays.asList("and", "nor", "but", "or", "yet", "so"));
    private static ArrayList<String> relativePronouns = new ArrayList<>(Arrays.asList("that", "which", "who", "whom"));

    private GrammarChecker() {
    }

    /**
     * Check if the sentence is simple or not
     * @param sentence the string to be checked
     * @return true if the sentence has space and a full stop
     */
    public static boolean isSimpleSentence(String sentence) {
        return sentence.contains(" ") && sentence.contains(".");
    }

    /**
     * check if a sentence is compound
     * @param sentence the sentence to be checked
     * @return true if the sentence has one of the conjuctions and satisfies conditions to be a simple sentence
     */
    public static boolean isCompoundSentence(String sentence) {
        if (hasConjunction(sentence) && isSimpleSentence(sentence))
            return true;
        return false;
    }

    /**
     * check if a sentence is complex
     * @param sentence sentence to be checked
     * @return true if sentence has one of the relative pronouns and satisfies conditions to be a complex sentence
     */
    public static boolean isComplexSentence(String sentence) {
        if (hasRelativePronouns(sentence) && isCompoundSentence(sentence))
            return true;
        return false;
    }

    /**
     * Check if a sentence has conjuction
     * @param sentence sentence to be checked
     * @return true if sentence has conjuction
     */
    private static boolean hasConjunction(String sentence) {
        for (String conjunction: conjunctions) {
            if (sentence.contains(conjunction))
                return true;
        }
        return false;
    }

    /**
     * check if a sentence has relative pronoun
     * @param sentence the sentence to be checked
     * @return true if the sentence has one of the pronouns
     */
    private static boolean hasRelativePronouns(String sentence) {
        for (String relativePronoun: relativePronouns) {
            if (sentence.contains(relativePronoun))
                return true;
        }
        return false;
    }
}
