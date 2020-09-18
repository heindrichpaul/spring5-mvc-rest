package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Created by heindrichpaul on 18/09/2020
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run(); //load data

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    void patchCustomerUpdateFirstName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        //save original first name
        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Optional<Customer> updatedCustomerOptional = customerRepository.findById(id);
        if (updatedCustomerOptional.isPresent()) {
            Customer updatedCustomer = updatedCustomerOptional.get();
            assertNotNull(updatedCustomer);
            assertEquals(updatedName, updatedCustomer.getFirstname());
            assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstname())));
            assertThat(originalLastName, equalTo(updatedCustomer.getLastname()));
        }
    }

    @Test
    void patchCustomerUpdateLastName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        //save original first/last name
        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Optional<Customer> updatedCustomerOptional = customerRepository.findById(id);
        if (updatedCustomerOptional.isPresent()) {
            Customer updatedCustomer = updatedCustomerOptional.get();

            assertNotNull(updatedCustomer);
            assertEquals(updatedName, updatedCustomer.getLastname());
            assertThat(originalFirstName, equalTo(updatedCustomer.getFirstname()));
            assertThat(originalLastName, not(equalTo(updatedCustomer.getLastname())));
        }
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers Found: " + customers.size());

        //return first id
        return customers.get(0).getId();
    }
}
