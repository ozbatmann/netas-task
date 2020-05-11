package com.netas.project.dao;

import com.netas.project.conf.HibernateUtil;
import com.netas.project.conf.ValidationUtil;
import com.netas.project.model.Files;
import com.netas.project.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    FilesDAO filesDAO;
    @Override
    public void addStudent(Student s, UploadedFile file) {
        Session session = null;
        Transaction tx = null;
        try {
            if(file != null) {
                int id = filesDAO.addFile(file);
                Files files = filesDAO.getFileById(id);
                s.setFiles(files);
            }
                ValidationUtil.validate(s);
                session =  HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                session.persist(s);
                tx.commit();
    }
        catch (Exception e) {
        if (tx!=null) tx.rollback();
        throw new ValidationException(e.getMessage());
    }
        finally {
            if(session != null && session.isConnected()) session.close();
    }
}

    @Override
    public List<Student> getStudentList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Student> studentList = session.createQuery("from Student").list();
        session.close();
        return studentList;
    }

    @Override
    public void updateStudent(Student s) {
        Session session = null;
        Transaction tx = null;
        try {
                ValidationUtil.validate(s);
                session =  HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                session.persist(s);
                tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
        }
        finally {
            if(session != null && session.isConnected()) session.close();
        }
    }

    @Override
    public void deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = (Student) session.get(Student.class, id);
            session.delete(student);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
        }
        finally {
            if(session.isConnected()) session.close();
        }
    }

    @Override
    public void updateFile(Student s, UploadedFile file) {
        Files files = new Files();
        files.setName(file.getFileName());
        files.setData(file.getContent());
        s.setFiles(files);
        updateStudent(s);
    }
}
