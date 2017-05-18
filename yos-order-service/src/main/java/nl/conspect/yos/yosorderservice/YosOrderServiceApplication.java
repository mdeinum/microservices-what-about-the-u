package nl.conspect.yos.yosorderservice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YosOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YosOrderServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializer(OrderRepository or) {
        return strings -> {

            OrderDetail d1 = OrderDetail.of("Spring Recipes 4th", 2, new BigDecimal("35.40"));
            OrderDetail d2 = OrderDetail.of("Clean Code", 1, new BigDecimal("26.32"));
            Order order1 = Order.of(Arrays.asList(d1, d2), 1, Address.of("Rammersveld", "41", "Meppel", "7943LS", "NL"));
            order1.setDeliveryDate(LocalDate.now().plusDays(1));

            OrderDetail d41 = OrderDetail.of("Go in Action", 1, new BigDecimal("44.99"));
            Order order4 = Order.of(Arrays.asList(d41), 1, Address.of("Rammersveld", "41", "Meppel", "7943LS", "NL"));

            OrderDetail d51 = OrderDetail.of("Expert One-on-One J2EE Design and Development", 1, new BigDecimal("59.99"));
            OrderDetail d52 = OrderDetail.of("Expert One-on-One J2EE Development without EJB", 1, new BigDecimal("39.99"));
            OrderDetail d53 = OrderDetail.of("Professional Java Development with the Spring Framework", 1, new BigDecimal("39.99"));
            Order order5 = Order.of(Arrays.asList(d51, d52, d53), 1, Address.of("Rammersveld", "41", "Meppel", "7943LS", "NL"));

            OrderDetail d21 = OrderDetail.of("MacBook Pro", 1, new BigDecimal("1235.40"));
            OrderDetail d22 = OrderDetail.of("OSX for Dummies", 1, new BigDecimal("12.50"));
            Order order2 = Order.of(Arrays.asList(d21, d22), 2, Address.of("Mattson Street", "3705", "Portland", "97205", "USA"));

            OrderDetail d31 = OrderDetail.of("MacBook Pro", 1, new BigDecimal("1235.40"));
            OrderDetail d32 = OrderDetail.of("Sandwich", 1024, new BigDecimal("1.45"));
            OrderDetail d33 = OrderDetail.of("Assortment of Drinks", 4087, new BigDecimal("0.87"));
            OrderDetail d34 = OrderDetail.of("Badges", 600, new BigDecimal("1.20"));
            Order order3 = Order.of(Arrays.asList(d31, d32, d33, d34), 4, Address.of("Avinguda Diagonal", "547", "Barcelona", "08029", "SP"));

            or.save(Arrays.asList(order1, order2, order3, order4, order5));

        };
    }
}
