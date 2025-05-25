package com.sbs.locally.post.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateForm {

	@NotEmpty(message = "제목을 입력해주세요.")
	private String title;
	
	@NotEmpty(message = "사진을 업로드해주세요.")
	@Size(min = 1, max = 10, message="최대 10개의 이미지만 업로드 할 수 있습니다.")
	private List<MultipartFile> files;
}
