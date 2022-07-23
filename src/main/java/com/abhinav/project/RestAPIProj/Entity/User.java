package com.abhinav.project.RestAPIProj.Entity;

import com.abhinav.project.RestAPIProj.Config.AesEncryptor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//User class to define user data and apply the Getters & Setters
@Entity
@Table(name = "users")
public class User {

    //Primary key of User Table (Auto-Generated value)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Name column of database (Data input by user)
    @NotNull
    @Size(min = 2, message = "Name should have at least 2 characters")
    @Column(name = "Name")
    private String name;

    //Password column of database (Data input by user)
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)+(?=.*[a-z])(?=.*[A-Z])+(?=.*[@#$%^&+=]).{8,20}$",
            message = "Should have 1 lower, 1 upper, 1 number, 1 spl char (b/w 8-20 char)")
    @Convert(converter = AesEncryptor.class)
    @Column(name = "Password ")
    private String password;

    //Constructor for the defined variables
    public User() {
    }

    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    //Getter for Primary Key Id
    public long getId(){
        return id;
    }

    //Setter for Primary Key Id
    public void setId(long id){
        this.id = id;
    }

    //Getter for Column Name
    public String getName(){
        return name;
    }

    //Setter for Column Name
    public void setName(String name){
        this.name = name;
    }

    //Getter for Column Password
    public String getPassword(){
        return password;
    }

    //Setter for Column Password
    public void setPassword(String password) {
        this.password = password;
    }
}
