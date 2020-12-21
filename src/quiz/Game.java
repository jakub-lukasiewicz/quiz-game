package quiz;

import java.util.*;

public class Game {
    private List<Integer> usedQuestions = new ArrayList<>();
    private Random rng = new Random();
    private Scanner scanner = new Scanner(System.in);
    private Map<Integer, String> currentQuestionAnswerMap = new HashMap<>();
    private int usersPoints;
    private Menu menu = new Menu();

    private static final Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    public void start(){
        menu.chooseCategory();
        play(getChosenQuestionList(menu.getChosenCategory()));
    }

    public List getChosenQuestionList(String category) {

        switch(category) {
            case "sport":
                return QuestionHandler.getInstance().getSportQuestions();
            case "geografia":
                return QuestionHandler.getInstance().getGeographicQuestions();
            case "muzyka":
                return QuestionHandler.getInstance().getMusicQuestions();
            case "technika":
                return QuestionHandler.getInstance().getTechnicalQuestions();
            case "filmy":
                return QuestionHandler.getInstance().getMovieQuestions();
            case "wszystkie":
            default:
                return QuestionHandler.getInstance().getAllQuestions();
        }

    }

    public void play(List<Question> questionsList) {

        int questionIndex;
        int numberOfQuestionsPerGame = 10;
        usersPoints = 0;

        String questionTitle;
        String correctAnswer;
        String usersAnswer;
        List<String> answers = new ArrayList<>();

        usedQuestions.clear();

        for (int i = 0; i < numberOfQuestionsPerGame; i++) {
            questionIndex = drawAQuestion(questionsList);
            questionTitle = questionsList.get(questionIndex).getQuestionTitle();
            correctAnswer = questionsList.get(questionIndex).getCorrectAnswer();
            answers.clear();
            answers.addAll(questionsList.get(questionIndex).getAnswers());
            shuffleAnswers(answers);
            MapHandler.getInstance().createMap(answers, currentQuestionAnswerMap);

            System.out.println(questionTitle);

            MapHandler.getInstance().printMap(currentQuestionAnswerMap);
            usersAnswer = scanner.nextLine();
            checkAnswer(usersAnswer, correctAnswer);

        }

        System.out.println("Twoj wynik: " + usersPoints + "/" + numberOfQuestionsPerGame);
        menu.playAgain();

    }

    private Integer drawAQuestion(List<Question> questionsList) {

        int questionIndex = rng.nextInt(questionsList.size());
        if (usedQuestions.contains(questionIndex)) {
            return drawAQuestion(questionsList);
        } else {
            usedQuestions.add(questionIndex);
            return questionIndex;
        }

    }

    private void shuffleAnswers(List<String> answers) {

        int numberOfShuffles = rng.nextInt(5) + 1;
        for (int i = 0; i < numberOfShuffles; i++) {
            Collections.shuffle(answers);
        }

    }

    private void checkAnswer(String usersAnswer, String correctAnswer) {

        int usersAnswerCode = Integer.parseInt(usersAnswer);
        if (currentQuestionAnswerMap.containsKey(usersAnswerCode)){
            String answer = currentQuestionAnswerMap.get(usersAnswerCode);
            if (answer.equals(correctAnswer)) {
                System.out.println("Dobrze!");
                usersPoints++;
            } else {
                System.out.println("Zla odpowiedz");
            }
        } else {
            System.out.println("Zla odpowiedz");
        }

    }
}
