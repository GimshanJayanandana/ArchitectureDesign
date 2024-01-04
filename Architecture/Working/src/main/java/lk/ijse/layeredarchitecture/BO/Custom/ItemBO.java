package lk.ijse.layeredarchitecture.BO.Custom;

import lk.ijse.layeredarchitecture.BO.SuperBO;
import lk.ijse.layeredarchitecture.Dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    boolean existItems(String code) throws SQLException, ClassNotFoundException;

    void saveItems(ItemDTO dto) throws SQLException, ClassNotFoundException;

    void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    void deleteItems(String code) throws SQLException, ClassNotFoundException;

    String generateNextItemID() throws SQLException, ClassNotFoundException;
}
