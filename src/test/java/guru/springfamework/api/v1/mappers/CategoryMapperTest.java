package guru.springfamework.api.v1.mappers;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    private CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDto() {
        Category category = new Category();
        category.setName("Drinks");
        category.setId(2L);

        CategoryDTO categoryDTO = mapper.categoryToCategoyDto(category);

        assertEquals(Long.valueOf(2L), categoryDTO.getId());
        assertEquals("Drinks", categoryDTO.getName());
    }

}