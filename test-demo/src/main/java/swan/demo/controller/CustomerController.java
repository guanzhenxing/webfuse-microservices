package swan.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swan.demo.model.Customer;
import swan.demo.service.TestService;

import java.util.List;

/**
 * Created by guanzhenxing on 2017/8/9.
 */
@RestController
public class CustomerController {


    @Autowired
    TestService testService;

    @GetMapping("/do_insert")
    public String insert() {
        testService.addCustomer("zxguan", "hing@dynamaxcn.com");
        return "SUCCESS";
    }

    @GetMapping("/find")
    public List<Customer> find() {
        List<Customer> customers = testService.findAll();
        return customers;
    }

    @GetMapping("/all")
    public String doAll() {
        testService.doAll();
        return "SUCCESS";
    }

}
