package org.example.models;

import org.example.models.ModelLayer;
import org.example.models.Student;

public class DBLayer implements ModelLayer {
    @Override
    public Student getStudent() {
        return new Student("Andrii Korotchuk", 20, "IPZ-31");
    }
}
