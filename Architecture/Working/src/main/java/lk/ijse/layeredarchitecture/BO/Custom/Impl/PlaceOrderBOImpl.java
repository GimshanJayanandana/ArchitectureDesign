package lk.ijse.layeredarchitecture.BO.Custom.Impl;

import lk.ijse.layeredarchitecture.BO.Custom.PlaceOrderBO;
import lk.ijse.layeredarchitecture.DAO.Custom.CustomerDAO;
import lk.ijse.layeredarchitecture.DAO.Custom.ItemDAO;
import lk.ijse.layeredarchitecture.DAO.Custom.OrderDAO;
import lk.ijse.layeredarchitecture.DAO.Custom.OrderDetailDAO;
import lk.ijse.layeredarchitecture.DAO.DAOFactory;
import lk.ijse.layeredarchitecture.Dto.CustomerDTO;
import lk.ijse.layeredarchitecture.Dto.ItemDTO;
import lk.ijse.layeredarchitecture.Dto.OrderDetailDTO;
import lk.ijse.layeredarchitecture.Entity.Customer;
import lk.ijse.layeredarchitecture.Entity.Item;
import lk.ijse.layeredarchitecture.Entity.Order;
import lk.ijse.layeredarchitecture.Entity.OrderDetails;
import lk.ijse.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrderDetailDAO orderDetailImpl = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> oDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        connection = DBConnection.getDbConnection().getConnection();
        boolean isExist = orderDAO.exist(orderId);
        /*if order id already exist*/
        if (isExist) {
            return false;
        }

        connection.setAutoCommit(false);
        /*Refactored*/
        boolean isSaved = orderDAO.save(new Order(orderId,orderDate,customerId));
        if (isSaved) {
            for (OrderDetailDTO dto : oDetails) {
                boolean isOrderDetailSaved = orderDetailImpl.save(new OrderDetails(dto.getOid(),dto.getItemCode(),
                        dto.getQty(),dto.getUnitPrice()));

                ItemDTO item = findItem(dto.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - dto.getQty());

                if (isOrderDetailSaved) {
                    boolean isUpdated = itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),
                            item.getQtyOnHand()));
                    if (isUpdated) {
                        connection.commit();
                    }
                }
            }
        }
        connection.rollback();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer =  customerDAO.getCustomer(id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
        return customerDTO;
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean existItems(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public ItemDTO findItems(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getItems(code);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public String generateNextOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();

        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();

        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }

    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
        try {
            Item item = itemDAO.getItems(code);
            return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
