package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
	// @PostMapping("/filesUpload")
	public List<String> filesUpload1(MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {

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

	/**
	 * 多个文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	@PostMapping("/filesUpload")
	public String filesUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 定义上传文件类型
		String fileType = "mp3,mp4,video,rmvb,pdf,txt,xml,doc,gif,png,bmp,jpeg,jpg";
		// 允许上传的文件最大值(100M，单位byte)
		int maxSize = 100 * 1024 * 1024;
		response.addHeader("Access-Control-Allow-Origin", "*");
		// 文件保存的路径
		String savePath = "C://Users//lushuai//Desktop//record//upload//";
		response.setContentType("text/html; charset=UTF-8");
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		if (!uploadDir.canWrite()) {
			return "上传目录没有写权限！";
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024); // 设置缓冲区大小，1M
		factory.setRepository(uploadDir); // 设置缓冲区目录

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		// StandardMultipartHttpServletRequest multiRequest =
		// (StandardMultipartHttpServletRequest) request;
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> it = items.iterator();
		FileItem item = null;
		String fileName = "";
		String name = "";
		String extName = "";
		String newFileName = "";

		while (it.hasNext()) {
			item = (FileItem) it.next();
			fileName = item.getName();

			if (null == fileName || "".equals(fileName)) {
				continue;
			}

			// 判断文件大小后是否超限
			if (item.getSize() > maxSize) {
				item.delete();
				JOptionPane.showMessageDialog(null, "文件大小不得超过" + maxSize / 1024 / 1024 + "M");

				return "文件大小超限！不得超过" + maxSize;
			}

			// 获取文件名
			name = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));

			// 获取文件后缀名
			extName = fileName.substring(fileName.indexOf(".") + 1).toLowerCase().trim();

			// 判断是否为允许上传的文件类型
			if (!Arrays.asList(fileType.split(",")).contains(extName)) {
				item.delete();
				JOptionPane.showMessageDialog(null, "文件类型不争取，必须为" + fileType + "类型的文件！");

				return "文件类型不争取，必须为" + fileType + "类型的文件！";
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			newFileName = name + df.format(new Date()) + "." + extName;
			File uoloadFile = new File(savePath, newFileName);
			item.write(uoloadFile);
			RedirectAttributes attributes = null;
			attributes.addFlashAttribute("message", newFileName);
		}
		return "redirect:/uploadStatus";
	}

	@GetMapping("/delete")
	public static Map<String, Object> deleteFile(String path) {
		Map<String, Object> map = new HashMap<>();
		String filePath = "C://Users//lushuai//Desktop//record//upload//" + path;
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
