package com.reservation.servlet;

import com.reservation.dao.DriverDAO;
import com.reservation.model.Driver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addDriver")
public class DriverServlet extends HttpServlet {
    private DriverDAO driverDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        driverDAO = new DriverDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String licenseNumber = request.getParameter("license_number");
            Integer assignedVehicleId = request.getParameter("assigned_vehicle_id").isEmpty() ? null : Integer.parseInt(request.getParameter("assigned_vehicle_id"));
            String status = request.getParameter("status");

            Driver driver = new Driver(name, phone, licenseNumber, assignedVehicleId, status);

            boolean success = driverDAO.addDriver(driver);
            if (success) {
                response.sendRedirect("addDriver.jsp?success=1");
            } else {
                response.sendRedirect("addDriver.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addDriver.jsp?error=1");
        }
    }
}