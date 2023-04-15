package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 1:31
 */
public class OrderManager {
    private List<Order> orders;
    private static OrderManager singletonInstance;

    private OrderManager() {
        orders = FileOperator.loadData("Orders.json", Order.class);
    }

    public static OrderManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new OrderManager();
        }
        return singletonInstance;
    }


    public void addOrder(Order order) {
        orders.add(order);
        FileOperator.writeData(order, "Orders.json");
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        FileOperator.writeData(orders, "Orders.json");
    }

    public List<Order> getOrderList() {
        return orders;
    }

}
