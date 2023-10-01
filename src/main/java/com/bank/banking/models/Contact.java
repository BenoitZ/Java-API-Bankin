package com.bank.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Contact extends AbstractEntity {

    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    //PLUSIEURS CONTACTS PEUVENT APPERTENIR À UN UTILISATEUR
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
