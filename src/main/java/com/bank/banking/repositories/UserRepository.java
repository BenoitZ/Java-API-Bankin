package com.bank.banking.repositories;

import com.bank.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);


    //----------------------------------------------

   /* //select * from _user where firstname = 'Ali'
    List<User> findAllByFirstname(String firstname);

    //La méme Avec Query JPQL,
    // :firstname =  binding -> il lie automatiquement le bind avec le paramétre de la méthode
    @Query("from User where firstname = :firstname")
    List<User> searchByFirstname(String firstname);

    // OU :fn  -> il prend fn dans les param et l'associe au paramétre firstname
    @Query("from User where firstname = :fn")
    List<User> searchByFirstnameTwo(@Param("fn") String firstname);

    //----------------------------------------------

    //select * from _user where firstname like '%Ali%'
    List<User> findAllByFirstnameContaining(String firstname);

    //La méme Avec Query JPQL,
    // :firstname =  binding -> il lie automatiquement le bind avec le paramétre de la méthode
    // et pour le containing -> comme SQL '% %'
    @Query("from User where firstname = '%:firstname%'")
    List<User> searchByFirstnameContaining(String firstname);

    //------------------------------------------------

    //select * from _user u inner join account a on u.id = a.id_user and a.iban = '45342C'
    //jointure de la table user vers la table account pour renvoyer tous les utilisateurs avec un nom des comptes iban
    List<User> findAllByAccountIban(String iban);

    //LA MEME AVEC UNE REQUETE JPQL
    // On maniopule dans la requéte des objets Java et non des tables SQL
    @Query("from user u inner join account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByAccountIban(String iban);

    //LA MEME AVEC UNE REQUETE SQL NATIVE
    @Query(value = "select * from _user u inner join account a on u.id = a.id_user and a.iban = :iban", nativeQuery = true)
    List<User> searchByAccountIbanNative(String iban);

    //-----------------------------------------------


    //select * from _user where firstname ilike 'ali'     ilike = en ignorant la casse
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    //select * from _user where firstname = '%ali%' and email = 'bouali.pro@gmail.com'
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);*/

}
