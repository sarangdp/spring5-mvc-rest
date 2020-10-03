package guru.springfamework.api.v1.mappers;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    @Test
    public void testCustomerToCustomerDTO() {

        Customer customer = new Customer();

        customer.setFirstName("Sarang");
        customer.setLastName("Panch");
        customer.setId(1L);

        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);

        assertEquals(Long.valueOf(1L), customerDTO.getId());
        assertEquals("Sarang", customerDTO.getFirstName());
        assertEquals("Panch", customerDTO.getLastName());
    }

}