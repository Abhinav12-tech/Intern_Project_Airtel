package com.abhinav.project.RestAPIProj;

import com.abhinav.project.RestAPIProj.Controller.UserController;
import com.abhinav.project.RestAPIProj.Entity.User;
import com.abhinav.project.RestAPIProj.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

//Test Class to validate Unit & E2E Test Cases
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestApiProjApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserController controller;

    @MockBean
    private UserRepository repository;

    //Unit Test Case to validate presence of users in Database
    @Test
    public void getAllUsersTest(){
        when(repository.findAll()).thenReturn(Stream.
                of(new User("Abhinav", "Geeks@portal120"),
                        new User("Anirudh", "AniChau#2021")).collect(Collectors.toList()));
        assertEquals(2, controller.getAllUsers().size());
    }

    //Unit Test Case to validate successful creation of a user in Database
    @Test
    public void createUserTest(){
        User user = new User("Prateek", "ApsDK#2022");
        when(repository.save(user)).thenReturn(user);
        assertEquals(user,controller.createUser(user));
    }

    //Unit Test Case to validate encryption of password & addition of user in Database
    @Test
    public void insertData(){
        User user = new User();
        user.setName("Meghna");
        user.setPassword("MountSM%2018");

        repository.save(user);
    }

    //E2E Test Function to validate Rest Calls
    public ResponseEntity<String> doRestCall(String url, MultiValueMap<String,String> queryParam,
                                             Map<String,String> pathParam,String body,
                                             MultiValueMap<String,String> header,HttpMethod method){

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        HttpEntity<String> entity = new HttpEntity<>(body,header);
        ResponseEntity<String> response = testRestTemplate.
                exchange(builder.buildAndExpand(pathParam).toUri(),method,entity,String.class);
        return response;
    }

    //E2E Test Case to validate Rest Call to getUser API
    @Test
    public void getUserTest(){
        String url = "http://localhost:"+port+"/api/users/{id}";
        Map<String,String> pathVariable = new HashMap<>();
        pathVariable.put("id","1");

        HttpEntity<String> entity = new HttpEntity<>(null,null);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ResponseEntity<String> response = testRestTemplate.
                exchange(builder.buildAndExpand(pathVariable).toUri(), HttpMethod.GET,entity,String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    //E2E Test Case to validate successful addition of user to Database
    @Test
    public void addUserTest(){
        String url = "http://localhost:"+port+"/api/users";
        String body = "{\n" +
                "    \"name\":\"Poonam\",\n" +
                "    \"password\":\"PooNamC&234\"\n" +
                "}";
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        List<String> al = new ArrayList<>();
        al.add("application/json");
        headers.put("Content-Type",al);
        Map<String,String> pathParam = new HashMap<>();

        ResponseEntity<String> response= doRestCall(url,null,pathParam,body,headers,HttpMethod.POST);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
