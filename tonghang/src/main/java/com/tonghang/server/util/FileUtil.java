package com.tonghang.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


public class FileUtil {

	/**
	 * 根据表单提交多媒体文件，保存图片
	 * 
	 * @param request
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String savePic(HttpServletRequest request, MultipartFile file) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String realPath = path.substring(0, a + 7) + "/upload/" + today;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		// String fileName = new Date().getTime()+".jpg";
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String pic = "/upload/" + today + "/" + fileName;
		pic = OSSUtil.instance().uploadOss(targetFile, "upload"+File.separatorChar+today);
		targetFile.delete();
		return pic;
	}

	/**
	 * 根据输入流保存图片
	 * 
	 * @param request
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String savePic(HttpServletRequest request, InputStream in)
			throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String realPath = path.substring(0, a + 7) + "/upload/" + today;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		String fileName = UUID.randomUUID() + ".png";
		// String fileName = new Date().getTime()+".jpg";
		File targetDir = new File(realPath);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}
		FileOutputStream fileStream = null;
		// 保存
		try {
			/* 创建存取文件 */
			fileStream = new FileOutputStream(targetFile);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				/* 写入流 */
				fileStream.write(b, 0, i);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			fileStream.close();
		}
		String pic = "/upload/" + today + "/" + fileName;
		return pic;
	}

	/**
	 * 根据表单提交文件和版本号，保存app
	 * 
	 * @param request
	 * @param file
	 * @param version
	 * @return
	 */
	public static String saveApp(HttpServletRequest request,
			MultipartFile file, String version) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		String realPath = path.substring(0, a + 7) + "/upload/app/" + version;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		String fileName = file.getOriginalFilename();
		// String fileName = new Date().getTime()+".jpg";
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/upload/app/" + version + "/" + fileName;
	}

	/**
	 * 根据文件名和版本号，下载app
	 * 
	 * @param request
	 * @param fileName
	 * @param version
	 * @return
	 */
	public static InputStream downloadApp(HttpServletRequest request,
			String fileName, String version) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		String realPath = path.substring(0, a + 7) + "/upload/app/" + version;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		File targetFile = new File(realPath, fileName);
		try {
			InputStream inputStream = new FileInputStream(targetFile);
			return inputStream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据包名保存app
	 * @param request
	 * @param file
	 * @param name
	 * @param version
	 * @return
	 */
	public static String saveApp(HttpServletRequest request,
			MultipartFile file, String name, String version) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		String realPath = path.substring(0, a + 7) + "/upload/app/" + version;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		// String fileName = new Date().getTime()+".jpg";
		File targetFile = new File(realPath, name);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/upload/app/" + version + "/" + name;
	}
	

	/**
	 * 根据文件名和版本号，下载app
	 * 
	 * @param request
	 * @param fileName
	 * @param version
	 * @return
	 */
	public static InputStream downloadLastVersionApp(HttpServletRequest request,
			String fileName, String filePath) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		String realPath = path.substring(0, a + 7) + filePath;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		File targetFile = new File(realPath);
		try {
			InputStream inputStream = new FileInputStream(targetFile);
			return inputStream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
