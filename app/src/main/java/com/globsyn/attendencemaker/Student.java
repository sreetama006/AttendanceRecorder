package com.globsyn.attendencemaker;

public class Student {
    private String id;
    private String name;
    private String date;


    public Student() {

    }


    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

   public String getDate() {
        return date;
    }

   public void setDate(String date) {
        this.date = date;
    }



}



