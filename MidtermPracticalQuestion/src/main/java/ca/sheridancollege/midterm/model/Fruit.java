package ca.sheridancollege.midterm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fruit implements Serializable {

    private String fType;
    private int weight;
    private double subTotal;

}
