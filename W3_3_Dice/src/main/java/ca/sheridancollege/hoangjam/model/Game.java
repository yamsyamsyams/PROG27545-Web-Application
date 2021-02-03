package ca.sheridancollege.hoangjam.model;

import lombok.Data;

@Data
public class Game {
    private int numOfDice;
    private Die[] dice;

    public Game(int numOfDice) {
        setDice(numOfDice);
    }

    public void setDice(int numOfDice) {
        this.numOfDice = numOfDice;
        dice = new Die[numOfDice];

        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
            dice[i].rollDie();
        }
    }

    public int total() {
        int total = 0;
        for (Die d : dice) {
            total += d.getFace();
        }
        return total;
    }
}
