package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    void save(ItemDTO dto) throws SQLException, ClassNotFoundException;

    void update(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean existItem(String code) throws SQLException, ClassNotFoundException;

    void delete(String code) throws SQLException, ClassNotFoundException;

    String generateNextID() throws SQLException, ClassNotFoundException;

    boolean updateItem(List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    boolean updateItem(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItems(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItems(List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    ItemDTO findItem(String code) throws SQLException, ClassNotFoundException;
}
