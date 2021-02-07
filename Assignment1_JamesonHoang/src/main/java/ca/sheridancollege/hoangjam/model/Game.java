package ca.sheridancollege.hoangjam.model;

import lombok.Data;

@Data
public class Game {
    private int userChoice;
    private int comChoice;

    private int rock = 0;
    private int paper = 1;
    private int scissor = 2;

    private String[] choiceNames = {"Rock", "Paper", "Scissor"};
    public String[] winnerNames = {"Computer", "Player", "Draw"};

    public Game() {
        comChoice = (int) (3 * Math.random());
    }

    public void setUserChoice(int userChoice){
        if(userChoice >= 0 && userChoice <= 2){
            this.userChoice = userChoice;
        }
        else{
            throw new IllegalArgumentException("Invalid user choice");
        }
    }

    public void setComChoice(int comChoice){
        if(comChoice >= 0 && comChoice <= 2){
            this.comChoice = comChoice;
        }
        else{
            throw new IllegalArgumentException("Invalid computer choice");
        }
    }

}
