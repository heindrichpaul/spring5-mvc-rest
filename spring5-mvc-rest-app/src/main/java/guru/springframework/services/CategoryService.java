package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * Created by heindrichpaul on 18/09/2020
 */
public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
