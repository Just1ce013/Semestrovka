import java.util.List;
import java.util.Scanner;

public class QuizProcessor {
    private String GREETINGS;
    private String START;

    private QuizPreparer quizPreparer;
    private Quiz quiz;
    private Scanner scanner;

    public QuizProcessor() {
        GREETINGS = "Quiz \"Quizz\"!" +
                "\nВыберите тему викторины:" +
                "\n";
        START = "Тема викторины: %s" +
                "\nКоличество вопросов: %s" +
                "\nНеобходимое количество баллов для победы: %s" +
                "\nВращайте барабан!" +
                "\n";

        quizPreparer = new QuizPreparer();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println(GREETINGS);
        List<String> themes = quizPreparer.getThemesNames();
        for (String theme : themes) System.out.println(theme);
        String theme = scanner.nextLine();
        if (!quizPreparer.getThemesNames().contains(theme)) {
            System.out.println("Викторины с такой темой нет!");
            return;
        }
        quiz = quizPreparer.getPreparedQuiz(theme);
        System.out.println(String.format(START, quiz.getName(), quiz.getQuestionsCount(), quiz.getRequiredPoints()));

        int questionNumber = 0;
        for (Question question : quiz.getQuestions()) {
            questionNumber++;
            System.out.println("Вопрос №" + questionNumber);

            if (question.getType().equals(Question.Type.SINGLE)) {
                System.out.println("Единственный ответ.");
                System.out.println(question.getText());
                for (String option : question.getOptionsTexts()) {
                    System.out.println(option);
                }
                System.out.println("Введите ответ.");
                String response = scanner.nextLine();
                for (String option : question.getOptionsTexts()) {
                    if (response.equalsIgnoreCase(option)) quiz.updateScore(question.getPoints(option));
                }
            }
            if (question.getType().equals(Question.Type.MULTIPLE)) {
                System.out.println("Возможно несколько вариантов ответа.");
                System.out.println(question.getText());
                for (String option : question.getOptionsTexts()) {
                    System.out.println(option);
                }
                System.out.println("Введите овтет. Если в ответе несколько вариантов, разделите их пробелом.");
                String[] responses = scanner.nextLine().split(" ");
                for (String response : responses) {
                    if (question.getOptionsTexts().contains(response)) quiz.updateScore(question.getPoints(response));
                }
            }

            if (question.getType().equals(Question.Type.TEXT)) {
                System.out.println("Пользовательский вариант ответа.");
                System.out.println(question.getText());
                System.out.println("Введите ответ.");
                String response = scanner.nextLine();
                for (String option : question.getOptionsTexts()) {
                    if (response.equalsIgnoreCase(option)) quiz.updateScore(question.getPoints(option));
                }
            }
            System.out.println("Ваш счет: " + quiz.getScore() + "\nПродолжаем!\n");
        }

        System.out.println("Викторина окончена! Ваш счет: " + quiz.getScore());
        System.out.println(quiz.getQuizResult());
    }
}
