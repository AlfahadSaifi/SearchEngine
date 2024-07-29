package com.nucleus.model.engine;

import javax.persistence.*;

@Entity
@Table(name = "custom_sequence_testsr")
public class CustomSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    int id;

    @Column(name = "VALUE")
    int value;

    public CustomSequence() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
