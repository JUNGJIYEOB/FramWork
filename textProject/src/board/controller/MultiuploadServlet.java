package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class MultiuploadServlet
 */
@WebServlet(name = "Multiupload", urlPatterns = { "/multiupload" })
public class MultiuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MultiuploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1.메모리 파일 업로드 파일을 보관하는 FileItem 의 Factory 설정
		// import org.apach.common.fileupload.dis.DiskFileItemFactory
		DiskFileItemFactory disFactory = new DiskFileItemFactory();
		// 없로드시 사용할 임시 메모르 크기 설정(단위는 byte , 기본값 10240(10kb))
		disFactory.setSizeThreshold(4096);
		// 임시 저장 폴더 지정
		disFactory.setRepository(new File(getServletContext().getRealPath("/") + "upload/test2"));
		// 2.업로드 요청을 처리하는 servletFileUpload객체 생성
		ServletFileUpload upload = new ServletFileUpload(disFactory);
		upload.setSizeMax(10 * 1024 * 1024);
		// setSizeMax -> 전체 파일 업로드 최대 크기
		// setFileSizeMax -> 개별 파일 업로드 최대 크기
		// 3.ServletFileUpload 객체를 이용해서 requset 에서 보낸 파라미터 값 처리
		// 모든 파라미터가 FileItem 객체 형태로 저장되고 List 로 묶어서 가져옴

		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator item = items.iterator();
			ArrayList<String> params = new ArrayList<String>();
			while (item.hasNext()) {
				FileItem fi = (FileItem) item.next();
				// isFormField()에서 true 가 리턴되면 일반 파라미터
				if (fi.isFormField()) {
					String decript = fi.getFieldName();
					params.add(decript);
					// 파일 인경우
				} else {
					// 파일 이름 변수에 저장/파일패쓰 의미
					String fileOriginName = fi.getName();
					// 파일 이름
					String files = fi.getFieldName();
					// 중복파일명 처리 코드
					// aaa.txt ->aaa_1(증가) . txt
					String fileNameFront = fileOriginName.substring(0, fileOriginName.lastIndexOf('.'));
					String fileNameExtension = fileOriginName.substring(fileOriginName.lastIndexOf('.'));
					File uploadFile = null;
					StringBuilder fileName = new StringBuilder();
					int num = 0;// 중복 이름이 있는 경우 붙여줄 숫자 변수
					while (true) {
						//한바퀴 돌떄마다 초기화
						fileName.setLength(0);
						fileName.append(fileNameFront);
						if (num != 0) {
							fileName.append("_" + num);
						}
						fileName.append(fileNameExtension);
						uploadFile = new File(
								getServletContext().getRealPath("/") + "upload/test/" + fileName.toString());
						// 현재 파일 명과 일치하는 파일이 존재하지 않으면 파일 업로드 로직 수행
						if (!uploadFile.exists()) {
							params.add(fileName.toString());
							try {
								fi.write(uploadFile);
								break;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						num++;
					}
				}
			}
			for(String param :params) {
				System.out.println(param);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
