package com.example.layeredarchitecture.DAO.Custom.Impl;

import com.example.layeredarchitecture.DAO.Custom.SQLQueryDAO;
import com.example.layeredarchitecture.DAO.SQLUtil;
import com.example.layeredarchitecture.model.OrderSummary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQueryImpl implements SQLQueryDAO {

    /*This join Query will show the summary of all Orders in ascending order*/

    @Override
    public OrderSummary OrderDetails() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.test("SELECT o.oid,o.date,o.customerID,d.itemCode,i.description,d.qty,d.unitPrice\n" +
                "FROM Orders o\n" +
                "JOIN OrderDetails d ON o.oid = d.oid\n" +
                "JOIN Item i ON d.itemCode = i.code\n" +
                "ORDER BY o.oid ASC;");

        rst.next();
        return new OrderSummary(
                rst.getString(1),
                rst.getDate(2).toLocalDate(),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5),
                rst.getInt(6),
                rst.getBigDecimal(7));
    }
}
