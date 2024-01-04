package lk.ijse.layeredarchitecture.BO.Custom.Impl;

import lk.ijse.layeredarchitecture.BO.Custom.ItemBO;
import lk.ijse.layeredarchitecture.DAO.Custom.ItemDAO;
import lk.ijse.layeredarchitecture.DAO.DAOFactory;
import lk.ijse.layeredarchitecture.Dto.ItemDTO;
import lk.ijse.layeredarchitecture.Entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();

        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }

    @Override
    public boolean existItems(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public void saveItems(ItemDTO dto) throws SQLException, ClassNotFoundException {
        itemDAO.save(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        itemDAO.update(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public void deleteItems(String code) throws SQLException, ClassNotFoundException {
        itemDAO.delete(code);
    }

    @Override
    public String generateNextItemID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNextID();
    }

}
