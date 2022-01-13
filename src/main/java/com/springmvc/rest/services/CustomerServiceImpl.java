package com.springmvc.rest.services;

import com.springmvc.rest.api.v1.mapper.CustomerMapper;
import com.springmvc.rest.api.v1.model.CustomerDTO;
import com.springmvc.rest.api.v1.model.CustomerListDTO;
import com.springmvc.rest.controller.v1.CustomerController;
import com.springmvc.rest.domain.Customer;
import com.springmvc.rest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());

        return customerDTOList;

    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        CustomerDTO customerDTO = customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO1 -> {
                    customerDTO1.setCustomerUrl(getCustomerUrl(id));
                    return customerDTO1;
                })
                .orElseThrow(RuntimeException::new);

        return customerDTO;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO){

        Customer customer = customerMapper.customerDTOTocustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnCustomerDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnCustomerDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        // Convert DTO to Customer
        Customer customer = customerMapper.customerDTOTocustomer(customerDTO);

        // Set Customer id with param id
        customer.setId(id);

        // call save function and return DTO
        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

        return customerRepository.findById(id).map(customer -> {
           if (customerDTO.getFirstname() != null){
               customer.setFirstname(customerDTO.getFirstname());
           }

           if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
           }
            CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));

            returnDto.setCustomerUrl(getCustomerUrl(id));

            return returnDto;
        }).orElseThrow(RuntimeException::new);

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer){
        // save to db
        Customer savedCustomer = customerRepository.save(customer);

        // convert savedCustomer to DTO
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);

        // Set DTO url with customer id
        returnDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        //return DTO
        return returnDTO;
    }

    private String getCustomerUrl(Long id){
        return CustomerController.BASE_URL + "/" + id;
    }
}




















