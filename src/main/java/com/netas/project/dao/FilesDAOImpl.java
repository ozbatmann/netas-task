package com.netas.project.dao;

import com.netas.project.conf.HibernateUtil;
import com.netas.project.conf.ValidationUtil;
import com.netas.project.model.Files;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.file.UploadedFile;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.util.List;
import java.util.Set;

@Repository
public class FilesDAOImpl implements FilesDAO {

    @Override
    public int addFile(UploadedFile f) {

        Session session = null;
        Transaction tx = null;
        int id = 0;
        try {
            Files files = new Files();
            files.setName(f.getFileName());
            files.setData(f.getContent());
           if( ValidationUtil.validate(files)) {
               session = HibernateUtil.getSessionFactory().getCurrentSession();
               tx = session.beginTransaction();
               id = (Integer) session.save(files);
               tx.commit();
           }
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
        }
        finally {
            if(session != null && session.isConnected()) session.close();

        }
        return id;
    }
        public Files getFileById(int fileId) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "FROM Files E WHERE id = :fileId";
            Query query = session.createQuery(hql);
            query.setParameter("fileId",fileId);
            List<Files> filesList = query.list();
            session.close();
            return filesList.get(0);
        }
}
