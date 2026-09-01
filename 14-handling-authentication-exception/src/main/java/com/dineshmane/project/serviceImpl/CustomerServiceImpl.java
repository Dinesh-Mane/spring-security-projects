package com.dineshmane.project.serviceImpl;

import com.dineshmane.project.entity.Customer;
import com.dineshmane.project.repository.CustomerRepository;
import com.dineshmane.project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer saveUser(Customer customer) {
        return customerRepository.save(customer);
    }
}
