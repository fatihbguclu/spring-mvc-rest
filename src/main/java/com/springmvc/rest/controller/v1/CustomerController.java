package com.springmvc.rest.controller.v1;

import com.springmvc.rest.api.v1.model.CustomerDTO;
import com.springmvc.rest.api.v1.model.CustomerListDTO;
import com.springmvc.rest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){

        CustomerListDTO customerListDTO = new CustomerListDTO(customerService.getAllCustomers());

        ResponseEntity<CustomerListDTO> response = new ResponseEntity<>(customerListDTO, HttpStatus.OK);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){

        CustomerDTO customerDTO = customerService.getCustomerById(Long.valueOf(id));

        ResponseEntity<CustomerDTO> response = new ResponseEntity<>(customerDTO, HttpStatus.OK);

        return response;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<CustomerDTO>(customerService.createCustomer(customerDTO),
                HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(Long.valueOf(id), customerDTO),
                HttpStatus.OK);

    }

}
