package com.tonghang.server.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class OSSUtil {
	private String host = "http://oss-cn-shanghai.aliyuncs.com";
	private OSSClient client = new OSSClient(host, "PlQXvaWcQFfnraoC", "yHr0DLbxZ71BCu4KHADhGUKdZGMUMA");
	private String VideoBucket = "tonghangimg";
	private String videoPath = "http://tonghangimg.oss-cn-shanghai.aliyuncs.com";
	private static OSSUtil ossUtil;

	private OSSUtil() {

	}

	public static OSSUtil instance() {
		if (ossUtil == null) {
			ossUtil = new OSSUtil();
		}
		return ossUtil;
	}

	public String uploadOss(String filepath) throws IOException {

		File videofile = new File(filepath);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(videofile.getTotalSpace());
		client.putObject(VideoBucket, videofile.getPath(), videofile, metadata);

		videofile.delete();

		return videoPath + filepath;
	}

	public String uploadOss(InputStream inputstream, ObjectMetadata metadata, String path) throws IOException {

		client.putObject(VideoBucket, path, inputstream, metadata);
		return videoPath + path;
	}

	public static void main(String[] args) throws IOException {

		OSSUtil.instance().uploadOss("G://nginx.conf");
	}

}
