package com.netas.project.dao;

import com.netas.project.model.Student;
import org.primefaces.model.file.UploadedFile;

import java.util.List;

public interface StudentDAO {

    public List<Student> getStudentList();
    public void addStudent(Student s, UploadedFile file);
    public void updateStudent(Student s);
    public void deleteStudent(int id);
    public void updateFile(Student s, UploadedFile file);
}