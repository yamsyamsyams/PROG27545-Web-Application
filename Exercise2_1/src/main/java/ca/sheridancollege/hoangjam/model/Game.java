package ca.sheridancollege.hoangjam.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Game {
    private long id;
    private String title;
    private String publisher;
    private String platform;
    private String[] platforms = {"PS5", "Nintendo", "PC"};
    private BigDecimal price;
}
