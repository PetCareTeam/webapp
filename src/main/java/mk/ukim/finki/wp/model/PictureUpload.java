package mk.ukim.finki.wp.model;

import org.springframework.web.multipart.MultipartFile;
public class PictureUpload {
		
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public PictureUpload(MultipartFile file) {
		super();
		this.file = file;
	}

	public PictureUpload() {
		super();
	}
	

	}
