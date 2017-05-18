package nl.conspect.yos.yoscustomerservice;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class YosCustomerServiceApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContextInterceptor());
    }

    public static void main(String[] args) {
        SpringApplication.run(YosCustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializer(CustomerRepository cr) {
        return strings -> {

            Customer customer1 = Customer.of("Marten", "Deinum", LocalDate.of(1976, 5, 6),
                    Address.of("Rammersveld", "41", "Meppel", "7943LS", "NL"),
                    "marten.deinum@conspect.nl", "marten.deinum", "top-secret");

            Customer customer2 = Customer.of("John", "Doe",
                    LocalDate.of(1956, 3, 21),
                    Address.of("Mattson Street", "3705", "Portland", "97205", "USA"),
                    "john.doe@unknown.io", "john.doe", "unknown");

            Customer customer3 = Customer.of("Jane", "Doe",
                    LocalDate.of(1961, 6, 15),
                    Address.of("Mattson Street", "3705", "Portland", "97205", "USA"),
                    "jane.doe@unknown.io", "jane.doe", "unknown");

            Customer customer4 = Customer.of("Sergi", "Almar",
                    LocalDate.of(1961, 6, 15),
                    Address.of("Avinguda Diagonal", "547", "Barcelona", "08029", "SP"),
                    "sergi.almar@javahispano.oro", "sergi.almar", "springio2017");


            cr.save(Arrays.asList(customer1, customer2, customer3,customer4));


        };
    }

}



