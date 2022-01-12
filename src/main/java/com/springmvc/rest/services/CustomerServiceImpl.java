package com.springmvc.rest.services;

import com.springmvc.rest.api.v1.mapper.CustomerMapper;
import com.springmvc.rest.api.v1.model.CustomerDTO;
import com.springmvc.rest.api.v1.model.CustomerListDTO;
import com.springmvc.rest.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        List<CustomerDTO> customerDTOList = customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());

        return customerDTOList;

    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        CustomerDTO customerDTO = customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);

        return customerDTO;
    }
}
