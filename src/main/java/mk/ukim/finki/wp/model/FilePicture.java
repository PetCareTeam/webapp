package mk.ukim.finki.wp.model;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.binary.Base64;
public class FilePicture {

	public static void validateImage(MultipartFile image) {
			if (!image.getContentType().equals("image/jpeg")) {
				throw new RuntimeException("Only JPG images are accepted");
			}
		}
	public static String getBytes(byte[] bytes){
		String tmp=Base64.encodeBase64String(bytes);
		return tmp;
	}

	
}
