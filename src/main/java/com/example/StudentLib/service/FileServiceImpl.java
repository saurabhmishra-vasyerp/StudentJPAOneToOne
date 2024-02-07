package com.example.StudentLib.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileServiceImpl implements FileService {
	private static final String UPLOAD_DIR = "C:\\Users\\saurabhmishra\\Downloads\\FileOpeartion\\";

	@Override
	public String uploadFile(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		if (file.isEmpty()) {
			return "please select a file to uplaod";
		}
		byte[] bytes = file.getBytes();
		String filePath = UPLOAD_DIR + file.getOriginalFilename();
		String fileName = file.getOriginalFilename();
		File newFile = new File(filePath);
		file.transferTo(newFile);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName)
				.toUriString();
		return "File Uploaded Successfully " + fileDownloadUri;
	}

	@Override

	public ResponseEntity<Object> downloadFile(String filename) throws IOException {
		// TODO Auto-generated method stub
		ResponseEntity<Object> response = null;
		Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
		File file = filePath.toFile();
		if (file.exists()) {
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", filename);

			response = ResponseEntity.ok().headers(headers).contentLength(file.length())
					.body(new FileSystemResource(file));
		} else {
			response = ResponseEntity.notFound().build();
		}
		return response;

//		File file = new File(UPLOAD_DIR+filename);
//		if(!file.exists()) {
//			throw new FileNotFoundException("File not found "+filename);
//		}
//		
//		return Files.readAllBytes(Paths.get(UPLOAD_DIR+filename));

	}

	@Override
	public String appendToFile(MultipartFile file) throws IOException {
		String fileName;
		try {

			File directory = new File(UPLOAD_DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			if (file.getContentType().equals("text/plain")) {
				fileName = "index.txt";
				Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
				if (!filePath.toFile().exists()) {
					filePath.toFile().createNewFile();
				}
				Files.write(filePath, file.getBytes(), StandardOpenOption.APPEND);
			} else {

				fileName = file.getOriginalFilename();
				Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
				Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
			}

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
					.path(fileName).toUriString();

			System.out.println(file.getContentType());

			return ("File uploaded successfully. Download URL: " + fileDownloadUri);
		} catch (IOException ex) {
			throw new IOException("Could not upload the file: " + ex.getMessage());
		}
	}

//	public String appendToFile(String filename, String content) throws IOException {
//		// TODO Auto-generated method stub
//		String filePath = UPLOAD_DIR+filename;
//		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));
//		writer.write(content);
//		writer.close();
//		return "Content Appended Successfully";
//	}

}
