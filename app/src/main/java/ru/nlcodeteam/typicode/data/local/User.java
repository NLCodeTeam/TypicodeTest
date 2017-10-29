package ru.nlcodeteam.typicode.data.local;

import java.io.Serializable;

/**
 * Created by eldar on 28.10.2017.
 */

public class User implements Serializable {
    private int id;
    private String name;
    private String address;
    private String email;

    public User(int id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public User(int id,String[] items) {
        this(id, items[0],items[1],items[2]);
    }

    public String[] getItems() {
        return new String[] {
                this.name,
                this.address,
                this.email
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
