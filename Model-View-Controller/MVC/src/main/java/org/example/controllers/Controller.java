package org.example.controllers;

import org.example.models.DBLayer;
import org.example.models.ModelLayer;
import org.example.models.Student;
import org.example.view.ConsoleView;
import org.example.view.View;

public class Controller {
    ModelLayer modelLayer = new DBLayer();
    View view = new ConsoleView();
    public void execute(){
        Student student = modelLayer.getStudent();
        view.showStudent(student);
    }
}
