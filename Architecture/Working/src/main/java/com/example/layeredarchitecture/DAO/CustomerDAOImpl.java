package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO{
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.test("SELECT * FROM Customer");
        ArrayList<CustomerDTO> getAllCustomers = new ArrayList<>();
        while (rst.next()){
            CustomerDTO customerDTO =
                    new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));
            getAllCustomers.add(customerDTO);
        }
        return getAllCustomers;
    }
    @Override
    public void save(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        SQLUtil.test("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    }
    @Override
    public void update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        SQLUtil.test("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());
    }
    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.test("DELETE FROM Customer WHERE id=?",id);
    }
    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.test("SELECT id FROM Customer WHERE id=?",id);
    }
    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.test("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
}
