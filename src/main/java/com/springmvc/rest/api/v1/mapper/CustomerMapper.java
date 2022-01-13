package com.springmvc.rest.api.v1.mapper;

import com.springmvc.rest.api.v1.model.CustomerDTO;
import com.springmvc.rest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOTocustomer(CustomerDTO customerDTO);
}
