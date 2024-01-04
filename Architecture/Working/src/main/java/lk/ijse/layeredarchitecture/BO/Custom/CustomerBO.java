package lk.ijse.layeredarchitecture.BO.Custom;

import lk.ijse.layeredarchitecture.BO.SuperBO;
import lk.ijse.layeredarchitecture.Dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean saveCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean existCustomers(String id) throws SQLException, ClassNotFoundException;

    void updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    void deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateNextCustomerID() throws SQLException, ClassNotFoundException;
}
