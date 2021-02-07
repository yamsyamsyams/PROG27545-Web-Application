package ca.sheridancollege.hoangjam.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Game implements Serializable {
    private int rock = 1;
    private int paper = 0;
    private int scissor = 2;

    // possible outcomes
    private static int computer = 0;
    private static int player = 1;
    private static int draw = 2;

    private int userChoice = rock;
    private int comChoice;

    private String[] choiceNames = {"Paper", "Rock", "Scissor"};
    public String[] winnerNames = {"Computer", "Player", "Draw"};

    private static final int[][] winnerSelect =
            {
                    // computer selected paper
                    {
                            draw, // user selected paper
                            computer, // user selected rock
                            player // user selected scissors
                    },

                    // computer selected rock
                    {
                            player, // user selected paper
                            draw, // user selected rock
                            computer // user selected scissors
                    },

                    // computer selected scissors
                    {
                            computer, // user selected paper
                            player, // user selected rock
                            draw // user selected scissors
                    }
            };

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

    public String getUserChoiceName(){
        return choiceNames[userChoice];
    }

    public String getComputerChoiceName(){
        return choiceNames[comChoice];
    }

    // get the winner for hardcoded values
    public int getWinner(){
        return winnerSelect[comChoice][userChoice];
    }

    // provide winner value to get the winner name
    public String getWinnerName(){
        return winnerNames[getWinner()];
    }

    public void setComputerName(String computerName){
        winnerNames[computer] = computerName;
    }

    public String getComputerName(){
        return winnerNames[computer];
    }

    public void setUserName(String userName){
        winnerNames[player] = userName;
    }

    public String getUserName(){
        return winnerNames[player];
    }

}
