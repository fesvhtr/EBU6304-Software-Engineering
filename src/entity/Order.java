package entity;

import util.IdGenerator;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-21 9:14
 */
public class Order {
    private final String id;
    private String product;
    private int amount;
    private String deliveryDate;
    private String dueDate;
    private double price;
    private String receiver;
    private String contact;
    private String address;
    private String status;

    public Order(String product, int amount, String deliveryDate, String dueDate, String receiver, String contact, String address) {
        this.product = product;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.dueDate = dueDate;
        this.receiver = receiver;
        this.contact = contact;
        this.address = address;
        status = "已保存";
        id = "ORD" + IdGenerator.getCode();
    }

    public String getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public double getPrice() {
        return price;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
