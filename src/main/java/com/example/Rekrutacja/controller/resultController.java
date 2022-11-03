package com.example.Rekrutacja.controller;

import com.example.Rekrutacja.entity.result;
import com.example.Rekrutacja.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;


@RestController
public class resultController {
    @Autowired
    private CalculateService calcserv;

    @GetMapping("/jobs")
    public String jobs(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format("Hello %s!", name);
    }
    @GetMapping("/results")
    public CompletableFuture<List<result>> results(){

        return calcserv.findAllResults();
    }
    @GetMapping("/")
    public String start(){
        return String.format("To start write in url /calculate word, maxlength, minlength amount of strings");
    }
    @PostMapping("/")
    public String start2(){
        return String.format("To start write in url /calculate word, maxlength, minlength amount of strings");
    }
    @PostMapping("/calculate")
    public Set<String> calculate(@RequestParam(name = "word", defaultValue = "") String word, @Nullable @RequestParam(name = "maxLength" ,defaultValue ="2147483647",required=false)  int maxLen, @Nullable @RequestParam(name = "minLength", defaultValue = "1",required=false) int minLen, @RequestParam(name = "amount") int amount) throws Exception {
        if (maxLen < minLen){
            throw new Exception("Max length is smaller than min length");
        }
        Set<String> result =  calcserv.task(word,maxLen,minLen,amount).get();
        System.out.print("res " + result);
        return result;
    }

    @GetMapping("/calculate")
    public Set<String> calculate2(@RequestParam(name = "word", defaultValue = "") String word, @Nullable @RequestParam(name = "maxLength" ,defaultValue ="2147483647",required=false)  int maxLen,  @Nullable @RequestParam(name = "minLength", defaultValue = "1",required=false) int minLen, @RequestParam(name = "amount") int amount) throws Exception {
        if (maxLen < minLen){
            throw new Exception("Max length is smaller than min length");
        }

        Set<String> result = calcserv.task(word,maxLen,minLen,amount).get();
        System.out.print("res " + result);
        return result;
    }
}
