package cn.jzy.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 上传
		// 设置环境:创建一个DiskFileItemFactory工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 设置临时文件目录
		diskFileItemFactory.setRepository(
				new File("D:\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\uploadtemp"));
		// 设置临时文件的大小
		diskFileItemFactory.setSizeThreshold(10240);
		// 核心操作类:创建一个文件上传解析器。 负责处理上传的文件数据,并将表单中每个输入项封装成一个FileItem对象
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		// 限制文件大小
		upload.setFileSizeMax(1024 * 100);
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		// 判断前台是否有multipart属性
		boolean ismultipart = ServletFileUpload.isMultipartContent(request);
		if (ismultipart) {

			// 获取文件的内容并上传
			// 通过parseRequest解析form中的所有请求字段,并保存到items集合中
			try {
				List<FileItem> items = upload.parseRequest(request);
				// 遍历items中的数据
				Iterator<FileItem> iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					String fieldName = item.getFieldName();
					int sno = -1;
					String sname = null;
					// 判断前台字段是普通form表单字段，还是文件字段
					if (item.isFormField()) {
						if (fieldName.equals("sno")) {
							sno = Integer.parseInt(item.getString("utf-8"));
						} else if (fieldName.equals("sname")) {
							sname = item.getString("utf-8");
						} else {
							System.out.println("其他字段");
						}
					} else {// 是文件字段
							// 文件 上传
							// 获取文件名称和文件内容
							// getFiledName是获取普通form表单字段的name值
							// getName是获取 文件名
						String fileName = item.getName();
						// 限制上传的文件类型
						String ext = fileName.substring(fileName.indexOf(".") + 1);
						if (!ext.equals("png") || ext.equals("txt") || ext.equals("jpg")) {
							System.out.println("上传失败，文件类型只能是png,gif,jpg");
							return;
						}
						// 定义文件路径:指定上传的位置(一般是服务器路径)
						String path = this.getServletContext().getRealPath("/upload");
					
						File file = new File(path, fileName);

						item.write(file);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.out.println(fileName + "上传成功");
						return;
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
