package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
                    customerDTO.setUrl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return mapper.customerToCustomerDTO(customerRepository.findById(id).get());
    }
}
