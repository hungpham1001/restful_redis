package com.example.demo.Services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class Key {
	String dir ="/home/hungpham/eclipse-workspace/RestfullApi_UseRedis/src/main/resources/static/stt.txt";
	public void storeKey() throws IOException {
		FileOutputStream fos = new FileOutputStream(dir);
		String content = "1";
		Integer key = this.readKey();
		if(key<1) {
			fos.write(content.getBytes());
			fos.close();
			return;
		} else {
			key++;
			content = Integer.toString(key);
			fos.write(content.getBytes());
			fos.close();
		}
	}
	public Integer readKey() throws IOException {
		FileInputStream fis = new FileInputStream(dir);
		int n = fis.available();
		byte[] bytes = new byte[n];
		fis.read(bytes);
		fis.close();
		String content = new String(bytes);
		if(content.isEmpty()) {
			return 0;
		}
		Integer key = new Integer(content);
		return key;
	}
}
