package com.example.layeredarchitecture.DAO.Custom.Impl;
import com.example.layeredarchitecture.DAO.Custom.ItemDAO;
import com.example.layeredarchitecture.DAO.SQLUtil;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.test("SELECT * FROM Item");
        ArrayList<ItemDTO> getAllItems = new ArrayList<>();
        while (rst.next()) {
            ItemDTO itemDTO =
                    new ItemDTO(
                            rst.getString("code"),
                            rst.getString("description"),
                            rst.getBigDecimal("unitPrice"),
                            rst.getInt("qtyOnHand"));

            getAllItems.add(itemDTO);
        }
        return getAllItems;
    }
    @Override
    public void save(ItemDTO dto) throws SQLException, ClassNotFoundException {
        SQLUtil.test("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }
    @Override
    public void update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        SQLUtil.test("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }
    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.test("SELECT code FROM Item WHERE code=?",code);
    }
    @Override
    public void delete(String code) throws SQLException, ClassNotFoundException {
        SQLUtil.test("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.test("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public boolean updateItem(List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO dto : orderDetails){
            if (!updateItem(dto)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean updateItem(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean updateItems(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        ItemDTO item = findItem(dto.getItemCode());
        item.setQtyOnHand(item.getQtyOnHand() - dto.getQty());

        return SQLUtil.test("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",item.getDescription(),
                item.getUnitPrice(),item.getQtyOnHand(),item.getCode());
    }
    @Override
    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.test("SELECT * FROM Item WHERE code=?");
        rst.next();
        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }
}
