package org.acme.service;

import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repoitory.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAllCostumers() {
        List<CustomerDTO> customers = new ArrayList<>();
        customerRepository.findAll().stream().forEach(item -> {
                    customers.add(mapCustomerEntityToDTO(item));
                }
        );

        return customers;
    }

    public CustomerDTO findCustomerById(Long id) {
        return mapCustomerEntityToDTO(customerRepository.findById(id));
    }

    public void createNewCustomer(CustomerDTO customerDTO) {
        customerRepository.persist(mapCustomerDtoToEntity(customerDTO));
    }

    public void changeCustomer(Long id, CustomerDTO customerDTO) {
        CustomerEntity customer = customerRepository.findById(id);

        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());

        customerRepository.persist(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerEntity mapCustomerDtoToEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setAge(customerDTO.getAge());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhone(customerDTO.getPhone());

        return customerEntity;
    }

    private CustomerDTO mapCustomerEntityToDTO(CustomerEntity customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setAddress(customer.getAddress());
        customerDTO.setAge(customer.getAge());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setId(customer.getId());

        return customerDTO;
    }
}
