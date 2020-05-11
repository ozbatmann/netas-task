package com.netas.project.service;

import com.netas.project.model.Student;
import org.primefaces.model.file.UploadedFile;

import java.util.List;

public interface StudentService {

    public List<Student> getStudentList();
    public void addStudent(String name, String surname, String phoneNumber, String city, String district, String description,UploadedFile file);
    public void updateStudent(Student s);
    public void deleteStudent(int id);
    public void updateFile(Student s, UploadedFile file);
     
}