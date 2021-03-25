package ca.sheridancollege.hoangjam.data;


import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "contact")
public class Contact implements Serializable {

    private Integer id;
    private String fullName = "";
}
