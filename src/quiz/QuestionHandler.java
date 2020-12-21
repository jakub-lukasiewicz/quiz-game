package quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionHandler {
    private static final QuestionHandler instance = new QuestionHandler();

    public static QuestionHandler getInstance() {
        return instance;
    }

    private List<String> textFromFile = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private List<Question> allQuestions = new ArrayList<>();
    private List<Question> movieQuestions = new ArrayList<>();
    private List<Question> sportQuestions = new ArrayList<>();
    private List<Question> technicalQuestions = new ArrayList<>();
    private List<Question> geographicQuestions = new ArrayList<>();
    private List<Question> musicQuestions = new ArrayList<>();
    private boolean isImportDone = false;
    private Scanner fileScanner;

    {
        try {
            fileScanner = new Scanner(new File("bin/questionsFile/pytania.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void importQuestions() {

        if (!isImportDone) {
            importTextFromFile();
            convertFromTextToQuestionObject();
            isImportDone = true;
        }

    }

    private void importTextFromFile() {

        while (fileScanner.hasNextLine()) {
            textFromFile.add(fileScanner.nextLine());
        }

    }

    private void convertFromTextToQuestionObject() {

        int currentLine = 0;

        while (currentLine < textFromFile.size()) {

            List<String> tempListOfAnswers = new ArrayList<>();

            Question question = new Question();

            question.setQuestionTitle(textFromFile.get(currentLine));
            currentLine++;

            question.setCategory(textFromFile.get(currentLine));
            currentLine++;

            question.setNumberOfAnswers(Integer.parseInt(textFromFile.get(currentLine)));
            currentLine++;

            question.setCorrectAnswer(textFromFile.get(currentLine));

            for (int i = currentLine; i < currentLine + question.getNumberOfAnswers(); i++) {
                tempListOfAnswers.add(textFromFile.get(i));
            }

            question.setAnswers(tempListOfAnswers);
            currentLine += question.getNumberOfAnswers();

            sortQuestionByCategory(question);

        }

    }

    private void sortQuestionByCategory(Question question) {

        allQuestions.add(question);
        if (!categories.contains("wszystkie")){
            categories.add("wszystkie");
        }

        switch (question.getCategory()) {
            case "sport":
                sportQuestions.add(question);
                if (!categories.contains("sport")){
                    categories.add("sport");
                }
                break;
            case "technika":
                technicalQuestions.add(question);
                if (!categories.contains("technika")){
                    categories.add("technika");
                }
                break;
            case "muzyka":
                musicQuestions.add(question);
                if (!categories.contains("muzyka")){
                    categories.add("muzyka");
                }
                break;
            case "filmy":
                movieQuestions.add(question);
                if (!categories.contains("filmy")){
                    categories.add("filmy");
                }
                break;
            case "geografia":
                geographicQuestions.add(question);
                if (!categories.contains("geografia")){
                    categories.add("geografia");
                }
                break;
            default:
                break;

        }
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public List<Question> getMovieQuestions() {
        return movieQuestions;
    }

    public List<Question> getSportQuestions() {
        return sportQuestions;
    }

    public List<Question> getTechnicalQuestions() {
        return technicalQuestions;
    }

    public List<Question> getGeographicQuestions() {
        return geographicQuestions;
    }

    public List<Question> getMusicQuestions() {
        return musicQuestions;
    }

    public List<String> getCategories() {
        return categories;
    }

}
