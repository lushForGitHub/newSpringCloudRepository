package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

	/**
	 * 单个文件上传
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @param attributes
	 * @return
	 */
	@PostMapping("/fileUpload")
	public String fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) {
		// 获取文件名称
		String getFileName = file.getOriginalFilename();
		// 重命名,原名称拼接时间戳
		String newFileName = System.currentTimeMillis() + getFileName;
		String path = "C://Users//lushuai//Desktop//record//upload//";

		File parentPath = new File(path);

		if (!parentPath.exists()) {
			parentPath.mkdirs();
		}
		String src = "";
		try {
			file.transferTo(new File(parentPath, newFileName));
			File viewFile = new File(parentPath + "/" + newFileName);

			if (viewFile.exists()) {
				// 拼接图片的相对路径作为URL
				src = "/" + newFileName;
			} else {
				src = "";
			}
			attributes.addFlashAttribute("message", src);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:uploadStatus";
	}

	/**
	 * 多文件上传
	 * 
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/filesUpload")
	public List<String> filesUpload(MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {

		List<String> filesList = new ArrayList<>();

		if (files != null && files.length > 0) {
			String path = "C://Users//lushuai//Desktop//record//upload//";
			File parentPath = new File(path);

			if (!parentPath.exists()) {
				parentPath.mkdirs();
			}

			String folderName = System.currentTimeMillis() + "";
			File folder = new File(path + "/" + folderName);

			// 文件夹不存在则新建文件夹
			if (!folder.exists()) {
				folder.mkdirs();
			}
			for (int i = 0; i < files.length; i++) {
				try {
					MultipartFile file = files[i];
					// 使用当前的时间戳为文件命名
					String fileName = folderName + file.getOriginalFilename();
					file.transferTo(new File(folder, fileName));
					File newFile = new File(folder + "/" + fileName);

					if (newFile.exists()) {
						String url = folderName + "/" + fileName;
						filesList.add(url);
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return filesList;
		} else {
			return filesList;
		}
	}

	@GetMapping("/delete")
	public static Map<String, Object> deleteFile(String path) {
		Map<String, Object> map = new HashMap<>();
		String filePath = "D:/uploadFile" + path;
		System.out.println(filePath);
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			File file1 = new File(filePath);
			if (file1.exists()) {
				map.put("status", "error");
				map.put("msg", "文件删除失败！");
			} else {
				map.put("status", "ok");
				map.put("msg", "文件已删除！");
			}
		} else {
			map.put("status", "error");
			map.put("msg", "文件不存在！");
		}
		return map;
	}

}
