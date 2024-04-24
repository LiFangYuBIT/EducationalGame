package au.edu.jcu.educationalgame;

import java.util.ArrayList;
import java.util.List;

public class QuestionEasyBank {

    private static List<QuestionList> englishQuestions() {

        final List<QuestionList> questionLists = new ArrayList<>();

        final QuestionList question1 = new QuestionList("She lectures at __ Oxford University.",
                " ", "the", "an", "a", "the", "");
        final QuestionList question2 = new QuestionList("The headmaster hurried to the concert hall only __ the speaker __.",
                "to find; left", "to find; gone", "finding; left", "finding; gone", "to find; gone", "");
        final QuestionList question3 = new QuestionList("It was not until liberation that __ to his hometown.",
                "did he return", "was he returned", "he did return", "he returned", "he returned", "");
        final QuestionList question4 = new QuestionList("－When __ we meet again? －__ it any time you like.",
                "will; Do", "will; Make", "shall; Do", "shall; Make", "shall; Make", "");
        final QuestionList question5 = new QuestionList("Look! There are lots of __ birds flying over the trees.",
                "funny red little", "funny little red", "little funny red", "little red funny", "little red funny", "");

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
