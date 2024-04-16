package com.RapidGrill.service;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RapidGrill.model.Address;
import com.RapidGrill.model.Cart;
import com.RapidGrill.model.CartItem;
import com.RapidGrill.model.Order;
import com.RapidGrill.model.OrderItem;
import com.RapidGrill.model.Restaurant;
import com.RapidGrill.model.User;
import com.RapidGrill.repository.AddressRepository;
import com.RapidGrill.repository.OrderItemRepository;
import com.RapidGrill.repository.OrderRepository;
import com.RapidGrill.repository.RestaurantRepository;
import com.RapidGrill.repository.UserRepository;
import com.RapidGrill.request.OrderRequest;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest req, User user) throws Exception {

        Address shippingAddress = req.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);

        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Order creatOrder = new Order();
        creatOrder.setCustomer(user);
        creatOrder.setCreatedAt(new Date());
        creatOrder.setOrderStatus("PENDING");
        creatOrder.setDeliveryAddress(savedAddress);

        Cart cart = cartService.findCartById(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedItem = orderItemRepository.save(orderItem);
            orderItems.add(savedItem);
        }
        Long totalPrice = cartService.calculateCartTotal(cart);

        creatOrder.setItems(orderItems);
        creatOrder.setTotalPrice(cart.getTotal());

        Order savedOrder = orderRepository.save(creatOrder);
        restaurant.getOrders().add(savedOrder);

        return creatOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please set a valid ORDER STATUS");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        if (order != null) {
            orderRepository.delete(order);
        }
    }

    @Override
    public List<Order> getUserOrders(Long id) throws Exception {
        return orderRepository.findByCustomerId(id);
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

        if (orderStatus != null) {
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()) {
            throw new Exception("Order not found");
        }
        return order.get();
    }

}
