package com.project.util;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException, java.io.IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException ie) {
				throw new IOException("Error saving file",ie);
			}
		} 
		
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File Uploaded: "+ filePath);
		} catch (IOException e) {
			throw new IOException("Could not save file "+ fileName, e);
		}
		
	}
}
