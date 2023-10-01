package com.bank.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "_user")
public class User extends AbstractEntity{

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private boolean active;

    //UN UTILISATEUR NE PEUT AVOIR QU'UNE ADRESSE
    @OneToOne
    private Address address;

    //UN UTILISATEUR NE PEUT AVOIR QU'UN ROLE
    @OneToOne
    private Role role;

    //Un utilisateur a 0 ou plusieurs transactions (plusieurs = List)
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    //Un utilisateur peut figurer plusieurs fois dans les contacts
    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    //UN UTILISATEUR POSSÉDE UN COMPTE
    @OneToOne
    private Account account;

}
