package com.springcloud.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DownloadFilesController {

	@GetMapping("/download")
	public void download(HttpServletResponse response, RedirectAttributes attributes) {
		String path = "E://msdia80.dll";
		File file = new File(path);
		// 得到文件名
		String fileName = path.substring(path.indexOf(File.separator) + 1);

		try {
			// 吧文件名按UTF-8取出按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，
			// 中文不要太多，最多支持17个中文，因为header有150个字节限制
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			// 告诉浏览器输出内容为流
			response.setContentType("application/octet-stream");
			// Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片
			// 是文件的扩展名显示的，点保存后，文件以filename的值命名，保存类型以Content中设置的为准。
			// 注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			String len = String.valueOf(file.length());

			// 设置内容长度
			response.setHeader("Content-Length", len);
			OutputStream os = response.getOutputStream();
			FileInputStream is = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			int n;
			while ((n = is.read(bytes)) != -1) {
				os.write(bytes, 0, n);
			}
			is.close();
			os.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		attributes.addFlashAttribute("message", "文件" + fileName + "下载成功");
	}
}