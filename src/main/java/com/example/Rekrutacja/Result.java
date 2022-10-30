package com.example.Rekrutacja;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String result;
    private Integer generation;
    public Integer getId(){
        return this.id;
    }
    public void setID(Integer id){
        this.id = id;
    }
    public String getResult(){
        return this.result;
    }
    public void setResult(String result){
        this.result = result;
    }
    public Integer getGeneration(){
        return this.generation;
    }
    public void setGeneration(Integer generation) {
        this.generation = generation;
    }
}
