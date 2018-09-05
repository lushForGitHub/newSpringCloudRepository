package com.springcloud.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DownloadFileController {

	@GetMapping("/download_bak1")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
		String fileName = "msdia80.dll";
		// 设置强制下载不打开
		response.setContentType("application/force-download");
		// 设置文件名
		response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

		if (fileName != null) {
			// 设置文件路径
			String path = "E://";
			File file = new File(path, fileName);

			if (file.exists()) {
				byte[] bytes = new byte[1024];
				FileInputStream fileInputStream = null;
				BufferedInputStream bufferedInputStream = null;
				try {
					fileInputStream = new FileInputStream(file);
					bufferedInputStream = new BufferedInputStream(fileInputStream);
					OutputStream outputStream = response.getOutputStream();
					int i = bufferedInputStream.read(bytes);
					while (i != -1) {
						outputStream.write(bytes, 0, i);
						i = bufferedInputStream.read(bytes);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					attributes.addFlashAttribute("message", e.getMessage());
				}
			}
		}
		attributes.addFlashAttribute("message", "文件" + fileName + "下载成功");
	}
}
