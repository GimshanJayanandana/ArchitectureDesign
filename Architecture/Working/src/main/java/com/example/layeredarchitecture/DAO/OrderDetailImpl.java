package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailImpl implements  OrderDetailDAO{
    @Override
    public boolean saveDetails(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO dto : orderDetails) {
            if (!saveOrderDetails(orderId,dto)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetails(String orderId, OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
        stm.setString(1,orderId);
        stm.setString(2,dto.getItemCode());
        stm.setInt(3,dto.getQty());
        stm.setBigDecimal(4,dto.getUnitPrice());

        return stm.executeUpdate() > 0;
    }
}
