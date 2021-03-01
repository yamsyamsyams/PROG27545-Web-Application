package ca.sheridancollege.hoangjam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Serializable {
    private Long id;
//    private String teamFlag;
    private String teamName;
    private String continent;
    private int played;
    private int won;
    private int drawn;
    private int lost;

}
