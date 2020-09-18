package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by heindrichpaul on 18/09/2020
 */

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO (Category category);
}
