package ca.sheridancollege.hoangjam.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class Game {

    @NonNull
    private int numOfDice;
    private Die[] dice;

    public void setDice() {
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
