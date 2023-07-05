package com.dcankayrak.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
public class User extends BaseEntity{
    private String email;
    private String firstName;
    private String lastName;
    private String nationalityId;
    private String phoneNumber;
    private String address;
}
