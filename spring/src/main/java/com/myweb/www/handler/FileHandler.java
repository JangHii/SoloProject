package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component // 사용자 클래스를 빈으로 등록

public class FileHandler {
	
	private final String UP_DIR = "D:\\_myProject\\_java\\_fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		
		// FileVO 생성 , 파일을 경로에 맞춰서 저장 , 썸네일 저장
		// 날짜를 폴더로 생성하여 그날그날 업로드 파일을 관리
		LocalDate date = LocalDate.now(); // 오늘의 날짜
		String today = date.toString(); // string으로 변환
		today = today.replace("-", File.separator);
		
		//D:\\_myProject\\_java\\_fileUpload\\2024\\01\\10 으로 만들고싶다
		File folders = new File(UP_DIR , today);
		
		// 폴더 생성 exists : 있는지 없는지 확인
		if(!folders.exists()) {
			// mkdir() : 하나의 폴더만 생성
			// mkdirs() : 여러개의 폴더를 동시생성
			folders.mkdirs(); 
		}
		
		// files 객체에 대한 설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1); // 가장 마지막 경로의 앞
			log.info(">>>>> fileName >>>>> {}" , fileName);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidstr = uuid.toString();
			fvo.setUuid(uuidstr);
			
			// ------------------ 기본 fvo setting 완료 ----------------------
			
			// 디스크에 저장할 파일 객체를 생성
			String fullFileName = uuidstr + "_" + fileName ;
			File storeFile = new File(folders , fullFileName);
			
			// 실제 파일이 저장되려면 첫 경로부터 다 설정
			try {
				file.transferTo(storeFile); // 저장
				// 썸네일 생성 => 이미지 파일만 썸네일 생성 가능
				// 이미지 인지 확인
				if(isImageFile(storeFile)) {
					fvo.setFileType(1); // 이미지파일은 타입이 1
					// 썸네일 생성
					File thumbNail = new File(folders , uuidstr + "_th_" + fileName);
					Thumbnails.of(storeFile).size(75 ,75).toFile(thumbNail);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 생성 오류");
			}
			// list에 fvo 추가ㄴ
			flist.add(fvo);
		}
		
		return flist;
	}
	
	
	// 이미지 체크 메서드 생성
	private boolean isImageFile(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile); // type image/
		return mimeType.startsWith("image")? true : false ;
	}
	

}