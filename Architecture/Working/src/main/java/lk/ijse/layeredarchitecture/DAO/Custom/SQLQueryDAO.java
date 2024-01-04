package lk.ijse.layeredarchitecture.DAO.Custom;

import lk.ijse.layeredarchitecture.DAO.SuperDAO;
import lk.ijse.layeredarchitecture.Dto.OrderSummary;

import java.sql.SQLException;

public interface SQLQueryDAO extends SuperDAO {

    OrderSummary OrderDetails() throws SQLException, ClassNotFoundException;

}
