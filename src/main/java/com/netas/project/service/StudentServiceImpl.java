package com.netas.project.service;

import com.netas.project.dao.StudentDAO;
import com.netas.project.model.Student;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
	private StudentDAO studentDao;


    @Override
    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }

    @Override
    public void addStudent(String name, String surname, String phoneNumber, String city, String district, String description, UploadedFile file) {
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setPhoneNumber(phoneNumber);
        student.setCity(city);
        student.setDistrict(district);
        student.setDescription(description);

        studentDao.addStudent(student,file);
    }

    @Override
    public void updateStudent(Student s) {
        studentDao.updateStudent(s);
    }

    @Override
    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }

    @Override
    public void updateFile(Student s, UploadedFile file) {
        studentDao.updateFile(s,file);
    }

}
