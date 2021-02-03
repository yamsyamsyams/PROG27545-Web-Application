package ca.sheridancollege.hoangjam.model;

import lombok.Data;

import java.util.Random;

@Data
public class Die {
    private int face = 1;
    Random random = new Random();

    public void rollDie() {
        this.face = random.nextInt(6) + 1;
    }
}
