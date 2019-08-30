package com.zhonglu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    RedisClientTemplate redisClientTemplate;

    @GetMapping(value = "/testSet")
    public Object testSet(){
        redisClientTemplate.setToRedis("Frank","Frank测试redis");
        System.out.println(redisClientTemplate.getRedis("Frank"));
        return null;
    }

    @PostMapping(value = "get")
    public void get(@RequestBody List<Person> persons){
        System.out.println("----------------------");
        System.out.println("----------------------"+persons);
    }


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
       List<Person> people = new ArrayList<>();
        Person person = new Person("dsad", 12);
        Person person2 = new Person("dsadrr", 124);

        people.add(person);
        people.add(person2);
        HttpEntity<List<Person> > entity = new HttpEntity<>(people,httpHeaders);
        restTemplate.postForObject("http://172.17.180.129:8080/test/get",entity,String.class);
    }
}