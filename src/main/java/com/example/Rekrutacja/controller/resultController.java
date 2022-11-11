package com.example.Rekrutacja.controller;

import com.example.Rekrutacja.entity.result;
import com.example.Rekrutacja.repository.resultRepository;
import com.example.Rekrutacja.service.CalculateService;
import com.example.Rekrutacja.service.ResultService;
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
    public resultRepository rep ;
    @GetMapping("/jobs")
    public String jobs(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format("Hello %s!", name);
    }
    @GetMapping("/results")
    public List<result> results(){
        //System.out.println(" rep find all "+rep.findAll());
         List<result> w = rep.findAll();
        return  w;

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
        CalculateService calcserv = new CalculateService();
        Set<String> result =  calcserv.task(word,maxLen,minLen,amount).get();

        return result;
    }

    @GetMapping("/calculate")
    public Set<String> calculate2(@RequestParam(name = "word", defaultValue = "") String word, @Nullable @RequestParam(name = "maxLength" ,defaultValue ="2147483647",required=false)  int maxLen,  @Nullable @RequestParam(name = "minLength", defaultValue = "1",required=false) int minLen, @RequestParam(name = "amount") int amount) throws Exception {

        CalculateService calcserv = new CalculateService();
        if (maxLen < minLen){
            throw new Exception("Max length is smaller than min length");
        }
        System.out.println("Active thread cound before task " + Thread.activeCount());
        Set<String> result = calcserv.task(word,maxLen,minLen,amount).get();
        rep.saveAll(calcserv.saveResult(result));
        System.out.println("Active thread cound after task " + Thread.activeCount());
        //System.out.print("res " + result);
        return result;
    }
}
