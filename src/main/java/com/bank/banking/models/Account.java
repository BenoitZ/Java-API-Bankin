package com.bank.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Account extends AbstractEntity {

    private String iban;

    //UN COMPTE POSSÉDE UN UTILISATEUR
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}


