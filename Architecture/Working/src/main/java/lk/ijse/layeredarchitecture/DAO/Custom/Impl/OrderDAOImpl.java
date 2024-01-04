package lk.ijse.layeredarchitecture.DAO.Custom.Impl;

import lk.ijse.layeredarchitecture.DAO.Custom.OrderDAO;
import lk.ijse.layeredarchitecture.DAO.SQLUtil;
import lk.ijse.layeredarchitecture.Entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    /*To refactor the transaction we need to brake down the code for smaller parts
      1.Need to have a orderDetailDAO to implementation of the saving the order details
      2.Need to update the Item quantity
      3.Need to save the order we can leave the order saving logic here because there is no need to create another class
        just for saving the orders by doing that we can achieve High Cohesion*/

    /*The transaction starts when the setAutoCommit is false in state by doing this we can pass the values to the database temporarily ane the database will not save those data
      until the setAutoCommit is set to true*/
    /*The main advantage is we can rollback which means simply calling the rollback method if the order or item saving have an issue the database won't save the data until the error is fixed*/
    /*So the refactoring goes the same like items and customers create a DAO class and implement tbe queries then to achieve loosely coupling
    create an interface then override those methods through the inter face*/
    /*SO we can achieve the High Cohesion,less Boiler Plate Cods as possible,Loosely Coupling and Property Injection*/

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",entity.getOrderId(),
                entity.getOrderDate(),entity.getCustomerId());
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        return resultSet.next();
    }
}
