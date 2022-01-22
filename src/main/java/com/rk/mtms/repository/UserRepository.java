package com.rk.mtms.repository;

import com.rk.mtms.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u from User u where u.userName=:RequestedUserName And u.userType=:userType")
    User getUserForUserName(@Param("RequestedUserName") String RequestedUserName, @Param("userType") String userTYpe);

    @Modifying
    @Query(value = "UPDATE User SET first_name = :firstName, last_name = :lastName, user_name = :userName, email = :email WHERE id = :id", nativeQuery = true)
    @Transactional
    void updateUserTable(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("userName") String userName, @Param("email") String email, @Param("id") int id);

}
