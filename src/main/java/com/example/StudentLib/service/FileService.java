package com.example.StudentLib.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	    String uploadFile(MultipartFile file) throws IOException;
	    ResponseEntity<Object> downloadFile(String filename) throws IOException;
	    String appendToFile (MultipartFile filename) throws IOException;

}
