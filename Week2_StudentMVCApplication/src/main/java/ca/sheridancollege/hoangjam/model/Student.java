package ca.sheridancollege.hoangjam.model;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;

    // added in week 3
    private String program;
}
