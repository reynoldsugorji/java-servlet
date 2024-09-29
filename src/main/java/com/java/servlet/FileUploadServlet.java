package com.java.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB (file size threshold before writing to disk)
        maxFileSize = 1024 * 1024 * 10,       // 10MB (max size for each file)
        maxRequestSize = 1024 * 1024 * 50     // 50MB (max size for total request)
)
public class FileUploadServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(FileUploadServlet.class.getName());
    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File file = new File(uploadPath);

        // Create directory if it doesn't exist
        if (!file.exists()) {
            file.mkdir();
        }

        // Store a success message
        String message = "File uploaded successfully!";

        // Get the uploaded file part and save the file
        for (Part part : req.getParts()) {
            String fileName = getFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                part.write(uploadPath + File.separator + fileName);
            } else {
                message = "File upload failed. No file selected.";
            }
        }

        // Set success or error message as request attribute
        req.setAttribute("message", message);

        // Forward to fileUpload.jsp with message
        req.getRequestDispatcher("fileUpload.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("fileUpload.jsp").forward(req, resp);
    }

    //Content-Disposition: form-data; name="file"; filename="example.txt"
    //Helper method to extract filename;
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }
}
