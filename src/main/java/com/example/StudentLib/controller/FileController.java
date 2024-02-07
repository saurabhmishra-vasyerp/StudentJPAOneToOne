package com.example.StudentLib.controller;

import java.io.IOException;

import com.example.StudentLib.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/files")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file)throws IOException{
		String response = fileService.uploadFile(file);
		return ResponseEntity.ok(response);
		
	}
	@GetMapping("/download/{filename}")
	 public ResponseEntity<Object> downloadFile(@PathVariable String filename) throws IOException {
//	        Object fileContent = fileService.downloadFile(filename);
	        return fileService.downloadFile(filename);
		
	}
    @PostMapping("/append")
	public String appendToFile(@RequestParam MultipartFile file)throws IOException{
		//ResponseEntity<Object> response = fileService.appendToFile(fileName, Content);
		return fileService.appendToFile(file);
	}

}
