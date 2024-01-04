package lk.ijse.layeredarchitecture.DAO.Custom;

import lk.ijse.layeredarchitecture.DAO.CrudDAO;
import lk.ijse.layeredarchitecture.Entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    Item getItems(String code) throws SQLException, ClassNotFoundException;
}
