package ca.sheridancollege.hoangjam.data;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName = "";
    private String contactType = "";
    private String phoneNumber = "";
    private String address = "";
    private String email = "";

    public Contact(){
    };

    public Integer getId(){
        return id;
    }
    public void setId(){
        this.id = id;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(){
        this.fullName = fullName;
    }
    public String getContactType(){
        return contactType;
    }
    public void setContactType(){
        this.contactType = contactType;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(){
        this.phoneNumber = phoneNumber;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(){
        this.address = address;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(){
        this.email = email;
    }

}
