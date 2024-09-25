package oop;

import java.util.Random;

enum VARIANTS {
    ROCK, 
    PAPER, 
    SCISSORS;
}

class Player {
    private String name;
    private VARIANTS choice;

    // бот
    public Player() {
        this.name = "Bot";
        this.choice = getRandomChoice();
    }

    // человек
    public Player(String name, VARIANTS choice) {
        this.name = name;
        this.choice = choice;
    }

    // рандом
    private VARIANTS getRandomChoice() {
        VARIANTS[] values = VARIANTS.values();
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }

    // получить имя
    public String getName() {
        return name;
    }

    // получить выбор
    public VARIANTS getChoice() {
        return choice;
    }

    // Определение победителя
    public String whoWins(Player player1, Player player2) {
        if (player1.getChoice() == player2.getChoice()) {
            return "Ничья";
        }
        switch (player1.getChoice()) {
            case ROCK:
                return "Победил игрок с именем: " + ((player2.getChoice() == VARIANTS.SCISSORS) ? player1.getName() : player2.getName());
            case PAPER:
                return "Победил игрок с именем: " + ((player2.getChoice() == VARIANTS.ROCK) ? player1.getName() : player2.getName());
            case SCISSORS:
                return "Победил игрок с именем: " + ((player2.getChoice() == VARIANTS.PAPER) ? player1.getName() : player2.getName());
        }
        return "Ничья";
    }
}

public class mod1 {
    public static void main(String[] args) {
        // создаем объекты
        Player bot = new Player();
        Player danil = new Player("Данил", VARIANTS.SCISSORS);

        // получаем результат
        System.out.println(bot.whoWins(bot, danil));
    }
}