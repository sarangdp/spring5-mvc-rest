package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void testGetCustomers() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Sarang");
        customer.setLastName("Panch");

        Customer customer1 = new Customer();
        customer1.setId(2L);
        customer1.setFirstName("Kshiti");
        customer1.setLastName("Panchaw");

        Customer customer2 = new Customer();
        customer2.setId(3L);
        customer2.setFirstName("Rajani");
        customer2.setLastName("Deshpande");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer, customer1, customer2));

        List<CustomerDTO> customers = customerService.getCustomers();

        assertEquals(3, customers.size());

    }

    @Test
    public void testGetCustomerById() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Sarang");
        customer.setLastName("Panch");


        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(3L);

        assertEquals(Long.valueOf(1L), customerDTO.getId());
        assertEquals("Sarang", customerDTO.getFirstName());
        assertEquals("Panch", customerDTO.getLastName());

    }

}