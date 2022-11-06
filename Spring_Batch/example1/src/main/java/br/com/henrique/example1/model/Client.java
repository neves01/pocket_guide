package br.com.henrique.example1.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String lastname;
    private Integer age;
    private String email;
    private List<Transaction> transactions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {

        return "Client{" +
                "name='" + name + '\'' +
                ", lastName='" + lastname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                (transactions.isEmpty() ? "" : "transactions="   + transactions) +
                '}';
    }
}
