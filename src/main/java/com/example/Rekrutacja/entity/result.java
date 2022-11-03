package com.example.Rekrutacja.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "results")
@AllArgsConstructor
@NoArgsConstructor
public class result {
    @Id
    @GeneratedValue
    private int id;
    private String value;
    private UUID generation;

    public UUID getGeneration() {
        return this.generation;
    }

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public void setGeneration(UUID generation) {
        this.generation = generation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
