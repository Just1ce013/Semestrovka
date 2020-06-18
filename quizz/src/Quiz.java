import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String name;
    private int questionsCount;
    private int requiredPoints;
    private List<Question> questions;
    private int score;

    public Quiz() {
        score = 0;
        questions = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getQuestionsCount() {
        return this.questionsCount;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public int getRequiredPoints() {
        return this.requiredPoints;
    }

    public void addQuestion(Question question) {
        if (question != null) questions.add(question);
    }

    public List<Question> getQuestions(){
        return questions;
    }

    public void updateScore(int quantity) {
        score += quantity;
    }

    public int getScore() {
        return score;
    }

    public String getQuizResult() {
        if (score == requiredPoints) return "Победа!";
        else return "Поражение!";
    }

    public boolean processResponse(String response, Question question) {
        for (String option : question.getOptionsTexts()) {
            if (option.equalsIgnoreCase(response)) updateScore(question.getPoints(option));
            return true;
        }
        return false;
    }

    public void printQuiz(){
        System.out.println(name);
        System.out.println(questionsCount);
        System.out.println(requiredPoints);
        for (Question question : questions){
            question.printQuestion();
        }
    }
}
