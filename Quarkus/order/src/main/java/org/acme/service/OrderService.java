package org.acme.service;

import client.CustomerClient;
import client.ProductClient;
import dto.CustomerDTO;
import dto.OrderDTO;
import org.acme.entity.OrderEntity;
import org.acme.repository.OrderRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    @RestClient
    CustomerClient customerClient;

    @Inject
    @RestClient
    ProductClient productClient;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public void saveNewOrder(OrderDTO orderDTO) {
        CustomerDTO customerDTO = customerClient.findCustomerById(orderDTO.getCustomerId());

        if (customerDTO.getName().equalsIgnoreCase(orderDTO.getCustomerName())
                && productClient.getProductById(orderDTO.getProductId()) != null) {
            orderRepository.persist(mapDtoToEntity(orderDTO));
        } else {
            throw new NotFoundException();
        }
    }

    private OrderDTO mapEntityToDto(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .orderValue(orderEntity.getOrderValue())
                .customerName(orderEntity.getCustomerName())
                .customerId(orderEntity.getCustomerId())
                .productId(orderEntity.getProductId())
                .build();
    }

    private OrderEntity mapDtoToEntity(OrderDTO orderDTO) {
        return OrderEntity.builder()
                .orderValue(orderDTO.getOrderValue())
                .customerName(orderDTO.getCustomerName())
                .customerId(orderDTO.getCustomerId())
                .productId(orderDTO.getProductId())
                .build();
    }


}
