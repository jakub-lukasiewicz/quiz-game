package quiz;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        QuestionHandler.getInstance().importQuestions();

        Game.getInstance().start();

    }
}
