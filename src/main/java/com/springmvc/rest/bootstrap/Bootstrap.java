package com.springmvc.rest.bootstrap;

import com.springmvc.rest.domain.Category;
import com.springmvc.rest.domain.Customer;
import com.springmvc.rest.repositories.CategoryRepository;
import com.springmvc.rest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCustomers(){

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Joe");
        customer1.setLastname("Joe");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Johny");
        customer2.setLastname("Red");
        customerRepository.save(customer2);

        System.out.println("Customer Data Loaded = " + customerRepository.count());

    }

    private void loadCategories(){

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Data Loaded = " + categoryRepository.count());
    }

}
