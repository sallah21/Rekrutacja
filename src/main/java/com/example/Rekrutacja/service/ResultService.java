package com.example.Rekrutacja.service;

import com.example.Rekrutacja.entity.result;
import com.example.Rekrutacja.repository.resultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ResultService {


    @Async
    public CompletableFuture<List<result>> findAllResults(resultRepository rep){
        List<result> results = rep.findAll();
        return CompletableFuture.completedFuture(results);
    }
}
