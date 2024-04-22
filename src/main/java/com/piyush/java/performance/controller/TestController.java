package com.piyush.java.performance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/api/testcontroller/")
public class TestController {

    Map<String,String> trackerMap = new HashMap<>();
    Map<String,JSONObject> objectMap = new HashMap<>();
    Map<String,JSONObject> objectMap1 = new HashMap<>();

    Map<String,JSONObject> objectMap2 = new HashMap<>();



    Logger log = LoggerFactory.getLogger(TestController.class);
    @GetMapping
    public Map<String, String> getData(){
        String uuid = UUID.randomUUID().toString();
        trackerMap.put(uuid,uuid);
        log.info("received request -> " + uuid);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/p0j01x0/files/" + uuid));
            System.out.println("thread name -> " + Thread.currentThread().getName());
            Thread.sleep(200);
            writer.write(uuid);
            writer.close();
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader("/Users/p0j01x0/IdeaProjects/javamelody-spring-demo/src/main/resources/json"));
            JSONObject jsonObject = (JSONObject)obj;
            Object obj2 = parser.parse(new FileReader("/Users/p0j01x0/IdeaProjects/javamelody-spring-demo/src/main/resources/json"));
            JSONObject jsonObject2 = (JSONObject)obj;
            Object obj3 = parser.parse(new FileReader("/Users/p0j01x0/IdeaProjects/javamelody-spring-demo/src/main/resources/json"));
            JSONObject jsonObject3 = (JSONObject)obj;
            objectMap.put(uuid,jsonObject);
            objectMap1.put(uuid,jsonObject2);
            objectMap2.put(uuid,jsonObject3);


        }catch(InterruptedException e){
            System.out.println("exception found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Map<String,String> response = new HashMap<>();
        log.info("sending response -> " + uuid);
        return response;
    }
}
