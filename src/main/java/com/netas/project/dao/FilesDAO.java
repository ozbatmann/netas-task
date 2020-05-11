package com.netas.project.dao;

import com.netas.project.model.Files;
import org.primefaces.model.file.UploadedFile;


public interface FilesDAO {

    public int addFile(UploadedFile f);
    public Files getFileById(int fileId);
}
