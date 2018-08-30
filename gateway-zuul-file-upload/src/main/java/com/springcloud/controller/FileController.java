package com.springcloud.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
	// 上传文件保存位置
	// private static String UPLOAD_FOLDER =
	// "C://Users//lushuai//Desktop//record//upload_test//";

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
			HttpServletRequest request) {
		// 上传文件保存位置，获取upload绝对路径
		// String UPLOAD_PATH =
		// request.getSession().getServletContext().getRealPath("upload");
		String UPLOAD_PATH = request.getServletContext().getRealPath("upload");
		System.out.println("UPLOAD_PATH========" + UPLOAD_PATH);
		String UPLOAD_PATH1 = this.getClass().getResource("/").getPath();
		System.out.println("UPLOAD_PATH========" + UPLOAD_PATH1);

		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		// 获取文件并保存
		try {
			byte[] bytes = file.getBytes();
			Path filePath = Paths.get(UPLOAD_PATH + file.getOriginalFilename());
			// String fileName = file.getOriginalFilename();
			// String fileName_New = UUID.randomUUID() + fileName;

			Files.write(filePath, bytes);

			attributes.addFlashAttribute("message", "file successfully upload: '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

}
