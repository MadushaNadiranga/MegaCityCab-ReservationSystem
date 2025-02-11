package com.reservation.servlet;

import com.reservation.dao.UserDAO;
import com.reservation.model.User;
import com.reservation.util.HashUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UserServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
    private UserDAO userDAO;

    public UserServlet() {
        this.userDAO = new UserDAO(); // Dependency Injection could be enhanced with a DI framework (e.g., Spring)
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fullName = request.getParameter("full_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        Part filePart = request.getPart("profile_picture");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        User user = new User(fullName, username, email, password, phone, role, UPLOAD_DIR + "/" + fileName);

        boolean success = userDAO.addUser(user);
        if (success) {
            response.sendRedirect("addUsers.jsp?success=1");
        } else {
            response.sendRedirect("addUsers.jsp?error=1");
        }
    }
}
