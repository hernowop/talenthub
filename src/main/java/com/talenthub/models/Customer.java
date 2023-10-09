package com.talenthub.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String email;

    @Column(name = "first_name")
    @NotBlank
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @NotBlank
    public String password;

    @Column(name = "phone_number")
    @NotBlank
    public String phoneNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    public List<Address> addresses;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "cart",
            joinColumns = @JoinColumn(name = "costumer_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id",nullable = false)
    )
    public List<Product> products;

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonGetter
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonIgnore
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonGetter
    public List<Product> getProducts() {
        return products;
    }

    @JsonIgnore
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
