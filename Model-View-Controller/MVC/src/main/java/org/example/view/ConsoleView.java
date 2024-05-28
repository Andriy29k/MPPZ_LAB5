package org.example.view;

import org.example.models.Student;

public class ConsoleView implements View {
    @Override
    public void showStudent(Student student) {
        System.out.println("Student name: " + student.getName() + "\nage: " + student.getAge() + "\ngroup: " + student.getGroupName());
    }
}
