package guru.springfamework.repositories;

import guru.springfamework.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by heindrichpaul on 18/09/2020
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
