package mk.ukim.finki.wp.model;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
public class FilePicture {

	public static void validateImage(MultipartFile image) {
			if (!image.getContentType().equals("image/jpeg")) {
				throw new RuntimeException("Only JPG images are accepted");
			}
	}
	
	public static void saveImage(String filename, MultipartFile image, ServletContext servletContext,String folder)
			throws RuntimeException, IOException {
			try {
				
				
			File file = new File(servletContext.getRealPath("/") + "/app/images/"+folder+"/"+ filename);
			 
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			
			} catch (IOException e) {
			
				System.out.println("Exception:" + e.getMessage());
				throw e;
			}
			}


	
}
