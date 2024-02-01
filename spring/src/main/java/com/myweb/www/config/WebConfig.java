package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.Registration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	// 루트 설정 클래스를 반환하는 메서드로, 애플리케이션 전반의 설정을 담당
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class , SecurityConfig.class};
	}

	@Override
	// 서블릿 설정 클래스를 반환하는 메서드로, 웹 계층에 대한 설정을 담당
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// 처음 열렸을때 기본 경로
		// index.jsp
		return new String[] {"/"};
	}

	@Override
	// 한글이나 특수문자와같은 문자 데이터를 처리하기위한...
	protected Filter[] getServletFilters() { 
		// 필터 설정
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8"); // 문자 인코딩을 UTF-8로 설정
		encoding.setForceEncoding(true);// 외부로 나가는 데이터도 인코딩 설정
		return new Filter[] {encoding}; 
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 파일을 저장할 폴더 경로
		String uploadLocation = "D:\\_myProject\\_java\\_fileUpload";
		
		// 업로드할수 있는 파일의 최대 크기 (20MB로 설정)
		int maxFileSize = 1024 * 1024 * 20;
		
		// 업로드된 파일의 최대크기 (maxFileSize의 2배로 설정)
		int maxReSize = maxFileSize * 2;
		
		// 파일이 메모리에 저장되기 전에 최대 크기를 결정하는 임계값 (maxFileSize)
		int fileSizeThreshole = maxFileSize;
		
		MultipartConfigElement multipartConfig =
				new MultipartConfigElement(uploadLocation , maxFileSize , maxReSize , fileSizeThreshole);
		
		registration.setMultipartConfig(multipartConfig);
	}
	
	

	
	
}
