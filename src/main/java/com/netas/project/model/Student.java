package com.netas.project.model;


import org.hibernate.engine.internal.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="STUDENT", schema = "STUDENTS")
public class Student {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

	@NotEmpty(message = "Name cannot be null")
    private String name;

	@NotEmpty(message = "Surname cannot be null")
    private String surname;

    @NotEmpty(message = "Phone number cannot be null")
    private String phoneNumber;

    @NotEmpty(message = "Description cannot be null")
    private String description;

    @NotEmpty(message = "City cannot be null")
    private String city;

    @NotEmpty(message = "District cannot be null")
    private String district;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Files files;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }
}