package com.monocept.app.controller;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.app.model.ImageStructure;
import com.monocept.app.services.FileUploadDownloadService;

@RestController
public class UploadDownloadController {

	private FileUploadDownloadService fileService;

	public UploadDownloadController(FileUploadDownloadService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/upload-file")
	public ResponseEntity<String> fileUpload(@RequestParam(name = "file") MultipartFile multipartFile)
			throws IOException {
		if (multipartFile.isEmpty()) {
			return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
		}

		ImageStructure isUploaded = fileService.uploadFile(multipartFile);
		if (isUploaded != null) {
			return new ResponseEntity<>("File successfully uploaded", HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/download-file")
	public ResponseEntity<byte[]> downloadFile(@RequestParam(name = "fileName") String fileName) throws IOException {
		byte[] resource = fileService.downloadFile(fileName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(resource);
	}
}
