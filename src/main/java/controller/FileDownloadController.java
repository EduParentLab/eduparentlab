package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String mode = request.getParameter("mode"); 
        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다.");
            return;
        }
        String uploadPath = request.getServletContext().getRealPath("/upload");
        String filePath = uploadPath + File.separator + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
            return;
        }
        if ("view".equals(mode)) {
            
            String mimeType = getServletContext().getMimeType(file.getName());
            if (mimeType == null) mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition",
                "inline; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        } else {   
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        }
        response.setHeader("Content-Length", String.valueOf(file.length()));

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        }
    }
}
