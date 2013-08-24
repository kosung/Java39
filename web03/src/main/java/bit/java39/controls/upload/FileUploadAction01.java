package bit.java39.controls.upload;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;

import bit.java39.servlets.Action;

//@Controller("/upload/upload.do")
public class FileUploadAction01 implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1. 멀티파트로 넘어온 파일 데이터를 임시 보관하는 역할을 수행할 객체
		FileItemFactory factory = new DiskFileItemFactory();
		
		// 2. HTTP POST 형식의 멀티파트로 넘어온 데이터를 파싱하는 역할
		// - 멀티파트 데이터를 파싱하여 factory에게 넘겨 보관처리 한다.
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 3. upload에게 요청 정보를 파싱하라고 명령을 내린다.
		// - 리턴 값은 factory가 관리하고 있는 파일 데이터들의 목록을 리턴한다.
		// - 목록에 들어 있는 것은 한 개의 파라미터 값을 보관한 FileItem 객체이다.
		List<FileItem> items = upload.parseRequest(request);
		int count = 0;
		for (FileItem item : items) {
			System.out.println(item.getFieldName());
			if (item.isFormField()) { // 일반 데이터
				request.setAttribute(
						item.getFieldName(), item.getString("UTF-8"));
			} else { // 파일 데이터
				
				ServletContext ctx = request.getServletContext();
				String repositoryPath = ctx.getRealPath("/files");
				String newFileName = 
						"file_" + System.currentTimeMillis() + "_" + count;
				count++;
				File file = new File(repositoryPath + "/" + newFileName);
				item.write(file);
				
				request.setAttribute(
						item.getFieldName(), newFileName);
			}
		}
		
		return "/upload/upload.jsp";
	}

}






