package com.example.layeredarchitecture.DAO.Custom;

import com.example.layeredarchitecture.model.OrderSummary;

import java.sql.SQLException;

public interface SQLQueryDAO {

    OrderSummary OrderDetails() throws SQLException, ClassNotFoundException;

}
