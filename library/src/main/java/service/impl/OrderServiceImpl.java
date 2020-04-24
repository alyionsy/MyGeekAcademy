package main.java.service.impl;

import main.java.dao.DAOFactory;
import main.java.dao.OrderDAO;
import main.java.domain.Reader;
import main.java.domain.Order;
import main.java.exception.ValidationException;
import main.java.service.OrderService;

import java.io.IOException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final OrderDAO dao = DAOFactory.getOrderDAO();

    @Override
    public Order create(Order order) throws IOException {
        if (order == null) {
            throw new ValidationException("Invalid order");
        }

        Reader owner = order.getReader();
        if (owner == null) {
            throw new ValidationException("Owner is required");
        }

        return dao.create(order);
    }

    @Override
    public Order update(Order order) throws IOException {
        if (order == null) {
            throw new ValidationException("Invalid order");
        }

        Reader owner = order.getReader();
        if (owner == null) {
            throw new ValidationException("Owner is required");
        }

        dao.update(order);
        return order;
    }

    @Override
    public void delete(Order order) throws IOException {
        if (order == null) {
            throw new ValidationException("Invalid book");
        }

        dao.delete(order);
    }

    @Override
    public Order read(long id) {
        return dao.read(id);
    }

    @Override
    public void listAllOrders() throws IOException, ClassNotFoundException {
        List<Order> allOrders = dao.readAll();
        for (Order order : allOrders) {
            System.out.println(order + "\n");
        }
    }
}