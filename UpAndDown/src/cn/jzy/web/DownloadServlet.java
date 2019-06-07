package cn.jzy.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DownloadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filename = request.getParameter("filename");
		//下载文件 需要设置响应头
		response.addHeader("contenType", "application/octet-stream");
		//filename包含了后缀
		response.addHeader("content-Disposition", "attachment; filename="+filename);
		
		//Servlet通过文件的地址，将文件转换成输入流 读到Servlet中
		InputStream in = this.getServletContext().getResourceAsStream("/res/a.png");
		//通过输出流将刚才已经转化为输入流的文件 输出给用户
		ServletOutputStream out = response.getOutputStream();
		byte[] b = new byte[10];
		int len= -1;
		while((len = in.read(b)) !=-1) {
			out.write(b, 0, len);
		}
		out.close();
		in.close();
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
