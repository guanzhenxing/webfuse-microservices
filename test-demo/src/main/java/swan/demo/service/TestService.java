package swan.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swan.demo.dao.CustomerRepository;
import swan.demo.model.Customer;

import java.util.List;

/**
 * Created by guanzhenxing on 2017/8/10.
 */
@Service
public class TestService {

    @Autowired
    CustomerRepository customerRepository;

    public void addCustomer(String name, String email) {
        customerRepository.addCustomer(name, email);
    }

    public void doAll() {
        customerRepository.doAll();
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
