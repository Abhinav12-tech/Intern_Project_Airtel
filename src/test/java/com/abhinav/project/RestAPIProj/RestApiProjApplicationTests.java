package com.abhinav.project.RestAPIProj;

import com.abhinav.project.RestAPIProj.Controller.UserController;
import com.abhinav.project.RestAPIProj.Entity.User;
import com.abhinav.project.RestAPIProj.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class RestApiProjApplicationTests {

    @Autowired
    private UserController controller;

    @MockBean
    private UserRepository repository;

    @Test
    public void getAllUsersTest(){
        when(repository.findAll()).thenReturn(Stream.
                of(new User("Abhinav", "Geeks@portal120"),
                        new User("Anirudh", "AniChau#2021")).collect(Collectors.toList()));
        assertEquals(2, controller.getAllUsers().size());
    }

    @Test
    public void createUserTest(){
        User user = new User("Prateek", "ApsDK#2022");
        when(repository.save(user)).thenReturn(user);
        assertEquals(user,controller.createUser(user));
    }

    @Test
    public void insertData(){
        User user = new User();
        user.setName("Meghna");
        user.setPassword("MountSM%2018");

        repository.save(user);
    }

}
