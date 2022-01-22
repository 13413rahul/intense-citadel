package com.rk.mtms.repository;

import com.rk.mtms.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public interface RegisterRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query(value="insert into User(first_name,last_name,user_name,password,phone_no,email,address_id,user_type) VALUES(:firstName,:lastName,:userName,:password,:mobile,:email,0,'student')",nativeQuery = true)
    @Transactional
    void insertIntoUserTable(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("userName") String userName, @Param("password") String password, @Param("mobile") String mobile, @Param("email") String email);

}
