package com.rk.mtms.repository;

import com.rk.mtms.entity.Payment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long> {
    @Modifying
    @Query(value="insert into Payment(amount, payment_status, payment_type, transaction_id) VALUES(:amount,:paymentStatus,:paymentType,:transactionId)",nativeQuery = true)
    @Transactional
    void insertIntoPaymentTable(@Param("amount") int amount, @Param("paymentStatus") String paymentStatus, @Param("paymentType") String paymentType, @Param("transactionId") int transactionId);

    @Query("SELECT u from Payment u where u.transactionId=:RequestedTransactionId")
    Payment getPaymentForTransactionId(@Param("RequestedTransactionId") int RequestedTransactionId);

}
