package nl.conspect.yos.yoscustomerservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * A account resembles an authenticated user of our system. A account is able
 * to submit orders. A account is identified by his or her username. When
 * authenticating the user supplies its username and password. Besides
 * identification information we also store basic legal information such as
 * address, firstname, lastname and email address.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Entity
public class Customer extends AbstractPersistable<Long> {

    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;

    @Embedded
    @Valid
    private Address address;

    @NotEmpty
    @Email
    private String emailAddress;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    private Customer() {}

    private Customer(String firstName, String lastName, LocalDate dateOfBirth, Address address, String emailAddress, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Address getAddress() {
        return this.address;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public static Customer of(String firstName, String lastName, LocalDate dateOfBirth, Address address, String emailAddress, String username,
            String password) {
        return new Customer(firstName, lastName, dateOfBirth, address, emailAddress, username, password);
    }

}