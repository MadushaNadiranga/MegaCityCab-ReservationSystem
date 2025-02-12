package com.reservation.servlet;

import com.reservation.dao.VehicleDAO;
import com.reservation.model.Vehicle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/addVehicle")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class VehicleServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads/vehicles";
    private VehicleDAO vehicleDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        vehicleDAO = new VehicleDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String model = request.getParameter("model");
            int year = Integer.parseInt(request.getParameter("year"));
            String licensePlate = request.getParameter("license_plate");
            String status = request.getParameter("status");

            Part filePart = request.getPart("vehicle_image");
            String fileName = filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);

            Vehicle vehicle = new Vehicle(model, year, licensePlate, status, UPLOAD_DIR + "/" + fileName);

            boolean success = vehicleDAO.addVehicle(vehicle);
            if (success) {
                response.sendRedirect("addVehicle.jsp?success=1");
            } else {
                response.sendRedirect("addVehicle.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addVehicle.jsp?error=1");
        }
    }
}
