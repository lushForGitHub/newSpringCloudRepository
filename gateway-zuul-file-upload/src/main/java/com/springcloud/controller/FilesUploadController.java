package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FilesUploadController {

	private static String UPLOAD_FOLDER = "C://Users//lushuai//Desktop//record//upload_test//";

	@GetMapping("/")
	public String index() {
		return "index";
	}

	private void upload(String uploadPath, MultipartFile file) throws IllegalStateException, IOException {
		// 文件后缀名
		String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		// 上传文件名
		String fileName = UUID.randomUUID() + suffixName;
		// 服务器保存的文件对象
		File serverFile = new File(uploadPath + fileName);
		// 将上传的文件保存在服务端文件内
		file.transferTo(serverFile);
	}

	@PostMapping("/filesUpload")
	public String filesUpload(HttpServletRequest request, MultipartFile[] file, RedirectAttributes attributes) {
		int count = 0;
		try {
			// 如果地址不存在，自动创建文件
			File localPath = new File(UPLOAD_FOLDER);

			if (!localPath.exists()) {
				localPath.mkdirs();
			}
			// 遍历文件数组，执行上传
			for (int i = 0; i < file.length; i++) {
				if (file[i] != null) {
					upload(UPLOAD_FOLDER, file[i]);
					count++;
				}
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("message", e.getMessage());
			return "redirect:uploadStatus";
		}
		attributes.addFlashAttribute("message", "成功上传" + count + "个文件");
		return "redirect:uploadStatus";
	}
}
