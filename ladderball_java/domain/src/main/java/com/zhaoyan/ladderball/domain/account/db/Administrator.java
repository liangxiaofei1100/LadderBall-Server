package com.zhaoyan.ladderball.domain.account.db;

import javax.persistence.*;

@Entity(name = "ladderball_admin")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "loginname")
    public String userName;

    @Column(name = "password")
    public String password;

    @Column(name = "name")
    public String name;

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
