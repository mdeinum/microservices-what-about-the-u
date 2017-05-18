package nl.conspect.yos.yosorderservice;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Address implements Serializable {

    @NotEmpty
    private String street;
    private String houseNumber;
    private String boxNumber;

    @NotEmpty
    private String city;
    private String postalCode;

    @NotEmpty
    private String country;

    private Address() {
        super();
    }

    private Address(String street, String houseNumber, String city, String postalCode, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    private Address(Address source) {
        super();
        this.street = source.street;
        this.houseNumber = source.houseNumber;
        this.boxNumber = source.boxNumber;
        this.city = source.city;
        this.postalCode = source.postalCode;
        this.country = source.country;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public String getBoxNumber() {
        return this.boxNumber;
    }

    public String getCity() {
        return this.city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public static Address of(Address address) {
        return new Address(address);
    }

    public static Address of(String street, String houseNumber, String city, String postalCode, String country) {
        return new Address(street, houseNumber, city, postalCode, country);
    }
}