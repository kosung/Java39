package bit.java39.controls.upload;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload/upload")
public class FileUploadAction {
	@Autowired
	ServletContext ctx;
	
	@RequestMapping
	public String execute(
			@RequestParam("name") String name,
			@RequestParam(value="age",defaultValue="0") int age,
			@RequestParam("photo1") MultipartFile photo1,
			@RequestParam("photo2") MultipartFile photo2,
			Model model) throws Exception {
		
		/*
		System.out.println(name);
		System.out.println(age);
		System.out.println(photo1.getContentType());
		System.out.println(photo1.getName());
		System.out.println(photo1.getOriginalFilename());
		System.out.println(photo1.getSize());
		System.out.println("------------------------");
		System.out.println(photo2.getContentType());
		System.out.println(photo2.getName());
		System.out.println(photo2.getOriginalFilename());
		System.out.println(photo2.getSize());
		*/
		
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		File photo1File = getNewFile();
		photo1.transferTo(photo1File);
		
		File photo2File = getNewFile();
		photo2.transferTo(photo2File);
		
		model.addAttribute("photo1", photo1File.getName());
		model.addAttribute("photo2", photo2File.getName());
		
		return "upload/upload";
	}
	
	private File getNewFile() {
		String repositoryPath = ctx.getRealPath("/files");
		String newFileName = 
				"file_" + System.currentTimeMillis() + Math.random();
		return new File(repositoryPath + "/" + newFileName);
	}

}






