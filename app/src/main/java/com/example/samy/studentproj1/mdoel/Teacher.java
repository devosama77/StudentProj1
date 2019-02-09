package com.example.samy.studentproj1.mdoel;

public class Teacher {
    String name ;
    String subject;
    String email;

    public Teacher() {
    }

    public Teacher(String name, String subject,String email) {
        this.name = name;
        this.subject = subject;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
