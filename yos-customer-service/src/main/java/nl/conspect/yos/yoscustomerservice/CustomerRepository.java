package nl.conspect.yos.yoscustomerservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<CustomerSummary> findAllSummarizedBy();

}
