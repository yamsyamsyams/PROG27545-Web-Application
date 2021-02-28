package ca.sheridancollege.hoangjam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Serializable {
    private String teamFlag;
    private String teamName;
    private String continent;
    private int playedGames;
    private int wonGames;
    private int drawnGames;
    private int lostGames;
}
