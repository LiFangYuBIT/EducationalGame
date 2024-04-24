package au.edu.jcu.educationalgame;

import java.util.ArrayList;
import java.util.List;

public class QuestionMediumBank {

    private static List<QuestionList> englishQuestions() {

        final List<QuestionList> questionLists = new ArrayList<>();

        final QuestionList question1 = new QuestionList("Medium",
                "Medium1", "Medium", "Medium", "Medium", "Medium1", "");
        final QuestionList question2 = new QuestionList("Medium",
                "Medium", "Medium2", "finding; left", "finding; gone", "Medium2", "");
        final QuestionList question3 = new QuestionList("Medium",
                "Medium", "Medium3", "Medium", "Medium", "Medium3", "");
        final QuestionList question4 = new QuestionList("Medium",
                "Medium", "Medium4", "shall; Do", "shall; Make", "Medium4", "");
        final QuestionList question5 = new QuestionList("Medium",
                "Medium", "Medium", "Medium5", "Medium", "Medium5", "");

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);

        return questionLists;
    }

    private static List<QuestionList> mathQuestions() {

        final List<QuestionList> questionLists = new ArrayList<>();

        final QuestionList question1 = new QuestionList("19 + __ = 42",
                "23", "61", "0", "42", "23", "");
        final QuestionList question2 = new QuestionList("What is the symbol of pi?",
                "€", "π", "Ω", "∞", "π", "");
        final QuestionList question3 = new QuestionList("What is the greatest two digit number?",
                "10", "90", "11", "99", "99", "");
        final QuestionList question4 = new QuestionList("How much is 90 – 19",
                "71", "109", "89", "None of these", "71", "");
        final QuestionList question5 = new QuestionList("Find the value of x; if x = (2 × 3) + 11.",
                "55", "192", "17", "66", "17", "");

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);

        return questionLists;
    }

    public static List<QuestionList> getQuestions(String selectedTopicName) {
        if ("English Quiz".equals(selectedTopicName)) {
            return englishQuestions();
        } else {
            return mathQuestions();
        }
    }
}
