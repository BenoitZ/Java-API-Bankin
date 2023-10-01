package com.bank.banking.services;

import org.hibernate.id.IntegralDataTypeHolder;

import java.util.List;

public interface AbstractServices<T> {

    //Pour renvoyer l'id de l'entité (toujours communiquer avec dto pas l'entité)
    Integer save(T dto);

    //Pour renvoyer plusieurs éléments et les stocker dans une list
    List<T> findAll();

    //Pour retrouver des éléments par leur id
    T findById(Integer id);

    //Pour effacer des éléments par leur id
    void delete(Integer id);


}
