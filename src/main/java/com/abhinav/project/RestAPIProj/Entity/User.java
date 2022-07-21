package com.abhinav.project.RestAPIProj.Entity;

import com.abhinav.project.RestAPIProj.Config.AesEncryptor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 2, message = "Name should have at least 2 characters")
    @Column(name = "Name")
    private String name;

    @NotNull
    @Pattern(regexp = "^(?=.*\\d)+(?=.*[a-z])(?=.*[A-Z])+(?=.*[@#$%^&+=]).{8,20}$",
            message = "Should have 1 lower, 1 upper, 1 number, 1 spl char (b/w 8-20 char)")
    @Convert(converter = AesEncryptor.class)
    @Column(name = "Password ")
    private String password;

    public User() {
    }

    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
