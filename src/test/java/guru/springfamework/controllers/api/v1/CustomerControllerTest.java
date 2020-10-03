package guru.springfamework.controllers.api.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;

    MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void getCustomers() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setFirstName("Sarang");
        customer.setLastName("Panch");

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(2L);
        customer1.setFirstName("Kshiti");
        customer1.setLastName("Panchaw");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(3L);
        customer2.setFirstName("Rajani");
        customer2.setLastName("Deshpande");

        when(customerService.getCustomers()).thenReturn(Arrays.asList(customer, customer1, customer2));

        mvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));
    }

    @Test
    public void gtCustomerById() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        //customer1.setCustomerUrl("/api/v1/customer/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        //when
        mvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")));
    }
}