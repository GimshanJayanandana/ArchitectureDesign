package lk.ijse.layeredarchitecture.DAO.Custom;

import lk.ijse.layeredarchitecture.DAO.CrudDAO;
import lk.ijse.layeredarchitecture.Entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
}
