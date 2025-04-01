package com.learn.e_commerce.Customer;

import com.learn.e_commerce.Exception.CustomerNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public String createCustomer( CustomerRequest request) {
        var customer=repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer( CustomerRequest request) {
        var customer=repository.findById(request.id()).orElseThrow(()->new CustomerNotFoundException(
                format("Cannot update customer:: No customer found with the provided ID:: %s",request.id())));
            mergeCustomer(customer,request);
            repository.save(customer); 
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {

        if(StringUtils.isNotBlank(request.firstname()))
        {
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname()))
        {
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email()))
        {
            customer.setEmail(request.email());
        }
        if(request.address() !=null)
        {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String id) {

        return repository.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {

        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(
                        format("No customer found with the provided ID:: %s",id)
                ));
    }

    public void deleteCustomer(String id) {
        repository.deleteById(id);
    }
}























