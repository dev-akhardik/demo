package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.entity.Customer;
import demo.respository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")

public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    // POST method to save a new user
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        // Save the user to the database
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer created successfully");

    }

    // GET method to read a user by id
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    // GET method to fetch all admins
    @GetMapping
    public List<Customer> getAllcustomers() {
        return customerRepository.findAll();
    }

    // PUT method to update an existing user
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPhone_no(customerDetails.getPhone_no());
            existingCustomer.setAddress(customerDetails.getAddress());
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    // DELETE method to delete a user by id
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            return "Customer with id " + id + " has been deleted";
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }
}
