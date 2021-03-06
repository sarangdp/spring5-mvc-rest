package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
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

        return customerRepository.findById(id).map(mapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setUrl("api/v1/customer/" + id);
                    return customerDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(mapper.customerDTOToCustomer(customerDTO));
        savedCustomer.setUrl("/api/v1/customers/" + savedCustomer.getId());
        return mapper
                .customerToCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        Customer customer = mapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        Customer savedCustomer = customerRepository.save(customer);

        savedCustomer.setUrl("/api/v1/customers/" + savedCustomer.getId());
        return mapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = mapper.customerDTOToCustomer(customerDTO);
        return customerRepository.findById(id).map(customer1 -> {
            if (customerDTO.getFirstName() != null) {
                customer1.setFirstName(customerDTO.getFirstName());
            }

            if (customerDTO.getLastName() != null) {
                customer1.setLastName(customerDTO.getLastName());
            }

            return mapper.customerToCustomerDTO(customer1);
        }).orElseThrow(ResourceNotFoundException::new);
    }
}
