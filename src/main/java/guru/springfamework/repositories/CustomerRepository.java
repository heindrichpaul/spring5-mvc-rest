package guru.springfamework.repositories;

import guru.springfamework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by heindrichpaul on 18/09/2020
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
