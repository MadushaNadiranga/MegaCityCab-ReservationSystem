package com.reservation.servlet;

import com.reservation.dao.DriverDAO;
import com.reservation.model.Driver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DriverManagementServlet")
public class DriverManagementServlet extends HttpServlet {
    private DriverDAO driverDAO;

    @Override
    public void init() throws ServletException {
        driverDAO = new DriverDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateDriver(request, response);
        } else if ("delete".equals(action)) {
            deleteDriver(request, response);
        }
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int driverId = Integer.parseInt(request.getParameter("driver_id"));
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String licenseNumber = request.getParameter("license_number");
            Integer assignedVehicleId = request.getParameter("assigned_vehicle_id").isEmpty() ? null : Integer.parseInt(request.getParameter("assigned_vehicle_id"));
            String status = request.getParameter("status");

            Driver driver = new Driver(driverId, name, phone, licenseNumber, assignedVehicleId, status);
            boolean success = driverDAO.updateDriver(driver);

            response.sendRedirect("manageDriver.jsp?updated=" + (success ? "1" : "0"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageDriver.jsp?updated=0");
        }
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int driverId = Integer.parseInt(request.getParameter("driver_id"));
            boolean success = driverDAO.deleteDriver(driverId);

            response.sendRedirect("manageDriver.jsp?deleted=" + (success ? "1" : "0"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageDriver.jsp?deleted=0");
        }
    }
}

