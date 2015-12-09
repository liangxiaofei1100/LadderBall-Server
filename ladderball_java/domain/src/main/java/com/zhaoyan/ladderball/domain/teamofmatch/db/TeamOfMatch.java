package com.zhaoyan.ladderball.domain.teamofmatch.db;

import javax.persistence.*;

@Entity(name = "teamofmatch")
public class TeamOfMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;
    public int score;
    public String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TeamOfMatch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", color='" + color + '\'' +
                '}';
    }
}
