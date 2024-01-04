package lk.ijse.layeredarchitecture.DAO.Custom.Impl;

import lk.ijse.layeredarchitecture.DAO.Custom.OrderDetailDAO;
import lk.ijse.layeredarchitecture.DAO.SQLUtil;
import lk.ijse.layeredarchitecture.Entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {


    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                entity.getOid(),entity.getItemCode(),entity.getUnitPrice(),entity.getQty());
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
