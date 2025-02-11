package com.reservation.servlet;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.reservation.dao.UserDAO;
import com.reservation.model.User;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UserServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fullName = request.getParameter("full_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        // Handle file upload
        Part filePart = request.getPart("profile_picture");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Store only the relative path
        String profilePicturePath = UPLOAD_DIR + "/" + fileName;

        // Create User Object
        User user = new User(fullName, username, email, password, phone, role, profilePicturePath);

        // Save user using UserDAO
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.addUser(user);

        if (success) {
            response.sendRedirect("addUsers.jsp?success=1");
        } else {
            response.sendRedirect("addUsers.jsp?error=1");
        }
    }
}
