package lk.ijse.layeredarchitecture.DAO.Custom.Impl;

import lk.ijse.layeredarchitecture.DAO.Custom.CustomerDAO;
import lk.ijse.layeredarchitecture.DAO.SQLUtil;
import lk.ijse.layeredarchitecture.Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<Customer> getAllCustomers = new ArrayList<>();
        while (rst.next()){
            Customer customer =
                    new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));
            getAllCustomers.add(customer);
        }
        return getAllCustomers;
    }
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getAddress());
    }
    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return  resultSet.next();
    }
    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("address"));
    }
}
