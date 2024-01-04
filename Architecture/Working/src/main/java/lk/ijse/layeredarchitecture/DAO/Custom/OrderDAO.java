package lk.ijse.layeredarchitecture.DAO.Custom;

import lk.ijse.layeredarchitecture.DAO.CrudDAO;
import lk.ijse.layeredarchitecture.Entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    boolean save(Order dto) throws SQLException, ClassNotFoundException;

}
