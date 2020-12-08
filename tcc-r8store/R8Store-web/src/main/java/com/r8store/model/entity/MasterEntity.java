package com.r8store.model.entity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

@MappedSuperclass
public abstract class MasterEntity {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Transient
	private final String CLASS_NAME = this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1);
	
	@Transient
	private UploadedFile file;

	public MasterEntity(Long id, UploadedFile file) {
		super();
		this.id = id;
		this.file = file;
	}
	
	public MasterEntity() {
		super();
	}

	public void upload(String fileName) {
		String pathUpload = System.getProperty("user.home") + "/uploads/" + CLASS_NAME + "/" + this.getId() + "/";
		Path folder = Paths.get(pathUpload);
		File dir = new File(pathUpload);
		dir.mkdirs();

		String extension = FilenameUtils.getExtension(file.getFileName());	
		try {
			Path tmpFile = Files.createTempFile(folder, fileName + "-", "." + extension);
			InputStream input = file.getInputstream();
			Files.copy(input, tmpFile, StandardCopyOption.REPLACE_EXISTING);
			       
	        File[] listOfFiles = dir.listFiles();

	        for (File file : listOfFiles) {        	
	        	String[] names = file.getName().split(Pattern.quote("."));
	            if (file.isFile() && names[0].equals(fileName)) {               	
	            	file.delete();
	            	break;
	            }
	        }
	        
			Files.move(tmpFile, tmpFile.resolveSibling(fileName + "." + extension));
		} catch (IOException e) {			 
			e.printStackTrace();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterEntity other = (MasterEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MasterEntity [id=" + id + "]";
	}
	
}
