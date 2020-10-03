package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        loadCategories();

        loadCustomers();

    }

    private void loadCustomers() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Sarang");
        customer.setLastName("Panch");
        customer.setUrl("/api/v1/customers/1");

        Customer customer1 = new Customer();
        customer1.setId(2L);
        customer1.setFirstName("Kshiti");
        customer1.setLastName("Panchaw");
        customer1.setUrl("/api/v1/customers/2");

        customerRepository.save(customer);
        customerRepository.save(customer1);

        System.out.println("Customers loaded:" + customerRepository.findAll().size());


    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(nuts);
        categoryRepository.save(dried);
        categoryRepository.save(exotic);
        categoryRepository.save(fresh);

        System.out.println("No of categories added:" + categoryRepository.count());
    }
}
