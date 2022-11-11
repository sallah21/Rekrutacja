package com.example.Rekrutacja.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "RESULTS")
@AllArgsConstructor
@NoArgsConstructor
public class result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String wart;
    private UUID generation;

    public UUID getGeneration() {
        return this.generation;
    }

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.wart;
    }

    public void setGeneration(UUID generation) {
        this.generation = generation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.wart = value;
    }
}
