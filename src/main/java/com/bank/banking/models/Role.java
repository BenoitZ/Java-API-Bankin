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
public class Role extends AbstractEntity{

    private String name;

    //UN UTILISATEUR TIENT UN ROLE
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
