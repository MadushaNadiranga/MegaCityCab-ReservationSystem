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

@WebServlet("/manageVehicles")
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
        vehicleDAO = new VehicleDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                // Add Vehicle
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
                response.sendRedirect("manageVehicles.jsp?success=" + (success ? "1" : "0"));

            } else if ("update".equals(action)) {
                // Update Vehicle
                int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
                String model = request.getParameter("model");
                int year = Integer.parseInt(request.getParameter("year"));
                String licensePlate = request.getParameter("license_plate");
                String status = request.getParameter("status");

                Vehicle vehicle = new Vehicle(vehicleId, model, year, licensePlate, status, null);
                boolean success = vehicleDAO.updateVehicle(vehicle);
                response.sendRedirect("manageVehicles.jsp?update=" + (success ? "1" : "0"));

            } else if ("delete".equals(action)) {
                // Delete Vehicle
                int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
                boolean success = vehicleDAO.deleteVehicle(vehicleId);
                response.sendRedirect("manageVehicles.jsp?delete=" + (success ? "1" : "0"));

            } else {
                response.sendRedirect("manageVehicles.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageVehicles.jsp?error=1");
        }
    }
}
