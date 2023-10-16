package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5,    // 5MB
        maxRequestSize = 1024 * 1024 * 5) // 5MB
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "WEB-INF/uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/upload.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String fileName = req.getParameter("fileName");

        Part part = req.getPart("file");
        if(fileName == null || fileName.isEmpty()){
            fileName = getSubmittedFileName(part);
        }else {
            String[] component = fileName.split("\\.");
            fileName = component[0];
            fileName = fileName + "." + getFileExtension(getSubmittedFileName(part));
        }
        boolean isOverride = req.getParameter("override") != null;
        String fileExtension = getFileExtension(fileName);
        if(!isValidExtension(fileExtension)){
            printWriter.print("<h1>Unsupported file extension</h1>");
            return;
        }
        String upLoadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File upLoadDir = new File(upLoadPath);
        if(!upLoadDir.exists()){
            upLoadDir.mkdir();
        }
        File fileExexisting = new File(upLoadPath + File.separator + fileName);
        if(fileExexisting.exists() && !isOverride){
            printWriter.print("<h1>File already exists</h1>");
            return;
        }
        part.write(upLoadPath + File.separator + fileName);
        req.setAttribute("message" ,"The file is uploaded successfully");
        req.setAttribute("urlDownload", "/DowloadServlet?file=" + fileName);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/result.jsp");
        rd.forward(req,resp);
    }
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
    private boolean isValidExtension(String extension) {
        String[] supportedExtensions = {"txt", "doc", "docx", "img", "pdf", "rar", "zip"};
        for (String ext : supportedExtensions) {
            if (ext.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
    private String getSubmittedFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for(String token : tokens){
            if(token.trim().startsWith("filename")){
                return token.substring(token.indexOf('=') + 1).trim().replace("\"","");
            }
        }
        return null;
    }
}
