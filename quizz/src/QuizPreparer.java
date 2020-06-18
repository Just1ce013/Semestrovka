import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizPreparer {
    private final String PROGRAM_DIRECTORY;
    private final String FILES_STORE_FOLDER;
    private final String FILES_DIRECTORY_PATH;

    public QuizPreparer() {
        PROGRAM_DIRECTORY = new File(".").getAbsolutePath();
        FILES_STORE_FOLDER = "files_store";
        FILES_DIRECTORY_PATH = PROGRAM_DIRECTORY
                + File.separator
                + FILES_STORE_FOLDER;

        File filesDirectoryPath = new File(FILES_DIRECTORY_PATH);
        if (!filesDirectoryPath.exists()) filesDirectoryPath.mkdirs();
    }

    public List<String> getThemesNames() {
        ArrayList<String> filesNames = new ArrayList<String>();
        File[] files = new File(FILES_DIRECTORY_PATH).listFiles();
        for (File file : files) {
            filesNames.add(file.getName().replace(".txt", ""));
        }
        return filesNames;
    }

    public Quiz getPreparedQuiz(String theme) {
        String filePath = FILES_DIRECTORY_PATH
                + File.separator
                + theme
                + ".txt";

        Quiz quiz = new Quiz();
        Question question = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] subs = line.split(":");

                if (subs[0].equalsIgnoreCase("name")) quiz.setName(subs[1]);
                if (subs[0].equalsIgnoreCase("questionsCount")) quiz.setQuestionsCount(Integer.parseInt(subs[1]));
                if (subs[0].equalsIgnoreCase("requiredPoints")) quiz.setRequiredPoints(Integer.parseInt(subs[1]));
                if (subs[0].equalsIgnoreCase("question")) {
                    quiz.addQuestion(question);
                    question = new Question();
                }
                if (subs[0].equalsIgnoreCase("type")) question.setType(subs[1]);
                if (subs[0].equalsIgnoreCase("text")) question.setText(subs[1]);
                if (subs[0].equalsIgnoreCase("option")) {
                    question.addOption(subs[1], Integer.parseInt(subs[2]));
                }
            }
            quiz.addQuestion(question);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return quiz;
    }

    public void getPreparedQuiz(Quiz quiz) {

    }
}
