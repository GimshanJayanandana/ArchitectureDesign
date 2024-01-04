package lk.ijse.layeredarchitecture.BO.Custom;

import lk.ijse.layeredarchitecture.BO.SuperBO;
import lk.ijse.layeredarchitecture.Dto.CustomerDTO;
import lk.ijse.layeredarchitecture.Dto.ItemDTO;
import lk.ijse.layeredarchitecture.Dto.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean existItems(String code) throws SQLException, ClassNotFoundException;

    ItemDTO findItems(String code) throws SQLException, ClassNotFoundException;

    String generateNextOrderID() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
}
