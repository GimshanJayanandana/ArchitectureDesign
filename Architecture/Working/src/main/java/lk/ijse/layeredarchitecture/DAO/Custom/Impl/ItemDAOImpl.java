package lk.ijse.layeredarchitecture.DAO.Custom.Impl;

import lk.ijse.layeredarchitecture.DAO.Custom.ItemDAO;
import lk.ijse.layeredarchitecture.DAO.SQLUtil;
import lk.ijse.layeredarchitecture.Entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<Item> getAllItems = new ArrayList<>();
        while (rst.next()) {
            Item item =
                    new Item(
                            rst.getString("code"),
                            rst.getString("description"),
                            rst.getBigDecimal("unitPrice"),
                            rst.getInt("qtyOnHand"));

            getAllItems.add(item);
        }
        return getAllItems;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",entity.getCode(),entity.getDescription(),
                entity.getUnitPrice(),entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
        return false;
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return resultSet.next();
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public Item getItems(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",code);
        rst.next();
        return new Item(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }
}
