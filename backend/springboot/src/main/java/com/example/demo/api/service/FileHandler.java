package com.example.demo.api.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.api.request.FileReq;

public class FileHandler {
	public static List<String> parseFileInfo(FileReq filereq)
	throws Exception{
		System.out.println("aaa");
	        // 경로를 지정하고 그곳에다가 저장할 심산이다
		System.out.println(" sdasdfasdf "+filereq.getFiles().size());

		int Room_id = filereq.getRoom_id();
		int user_id = filereq.getUser_id();
	        String path = "C:\\Users\\multicampus\\Pictures\\"+Room_id+"\\"+user_id;
	        File file = new File(path);
	        // 저장할 위치의 디렉토리가 존지하지 않을 경우
	        if(!file.exists()){
	            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
	            file.mkdirs();
			}
			List<String> files = new ArrayList<String>();
			// 파일들을 이제 만져볼 것이다
			int cnt = 1;
			for (MultipartFile multipartFile : filereq.getFiles()) {
				// 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
				if (!multipartFile.isEmpty()) {
					String filename = path +"\\"+cnt;
					files.add(filename);
					cnt++;
					file = new File(filename+".jpg");
					multipartFile.transferTo(file);
					file.setWritable(true);
	                file.setReadable(true);
				}
			}
			return files;
		}
	}