package com.monocept.app.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.app.model.ImageStructure;
import com.monocept.app.model.ImageUtil;
import com.monocept.app.repository.Filerepository;

@Service
public class FileUploadDownloadService {
	
	private Filerepository filerepository;
	
    
    public FileUploadDownloadService(Filerepository filerepository) {
		super();
		this.filerepository = filerepository;
	}

	private final String Uploaded_Folder = "F:\\uploaded file";
    
    public ImageStructure uploadFile(MultipartFile file) throws IOException {
    	
    	ImageStructure image=new ImageStructure(0, file.getOriginalFilename(), file.getContentType(), ImageUtil.compressImage(file.getBytes()));
		ImageStructure save = filerepository.save(image);
		return save;
    	
      
    }
    
    public byte[] downloadFile(String fileName) throws IOException {
		ImageStructure image=filerepository.findByName(fileName);
		byte[] decompressImage = ImageUtil.decompressImage(image.getImageData());
		return decompressImage;
	}
}
