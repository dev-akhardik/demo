package demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
