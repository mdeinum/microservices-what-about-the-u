package nl.conspect.yos.yoscustomerservice;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerSummary {

    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();

    @Value("#{target.address.country}")
    String getCountry();

    long getId();
    String getUsername();
    String getEmailAddress();
    LocalDate getDateOfBirth();


}
