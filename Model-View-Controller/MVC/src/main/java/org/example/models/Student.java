package org.example.models;

public class Student {
    String name;
    int age;
    String groupName;

    public Student() {
    }

    public Student(String name, int age, String groupName) {
        this.name = name;
        this.age = age;
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
