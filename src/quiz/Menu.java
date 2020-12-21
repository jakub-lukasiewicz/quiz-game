package quiz;

import java.util.*;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private String chosenCategoryCode;
    private String chosenCategory;
    private Map<Integer, String> menuMap = new HashMap<>();
    private List<String> yesOrNo = new ArrayList<>();

    public void chooseCategory() {

        int tempCategoryCode;

        System.out.println("Wybierz kategorie pytan, wpisujac przynalezacy do niej numer i zatwierdz enterem:");
        MapHandler.getInstance().createMap(QuestionHandler.getInstance().getCategories(), menuMap);
        MapHandler.getInstance().printMap(menuMap);

        chosenCategoryCode = scanner.nextLine();
        tempCategoryCode = Integer.parseInt(chosenCategoryCode);
        if (tempCategoryCode <= QuestionHandler.getInstance().getCategories().size() && tempCategoryCode > 0) {
            setChosenCategory(tempCategoryCode);
        } else {
            System.out.println("Niepoprawny kod kategorii");
            chooseCategory();
        }

    }

    public String getChosenCategory() {
        return chosenCategory;
    }

    private void setChosenCategory(int categoryCode) {
        chosenCategory = menuMap.get(categoryCode);
    }

    public void playAgain() {

        addOptionsToList();
        MapHandler.getInstance().createMap(yesOrNo, menuMap);

        System.out.println("Chcesz zagrać jeszcze raz?");
        MapHandler.getInstance().printMap(menuMap);
        String wantToPlayAgain = scanner.nextLine();
        int playAgainAnswerParsedToInteger = Integer.parseInt(wantToPlayAgain);

        if (playAgainAnswerParsedToInteger == 1 || playAgainAnswerParsedToInteger == 2) {
            if (menuMap.get(playAgainAnswerParsedToInteger).equals("tak")) {
                Game.getInstance().start();
            } else {
                return;
            }
        } else {
            System.out.println("Niepoprawny kod odpowiedzi");
            playAgain();
        }

    }

    private void addOptionsToList() {

        if (yesOrNo.isEmpty()) {
            yesOrNo.add("tak");
            yesOrNo.add("nie");
        }

    }


}

