package com.rk.mtms.repository;

import com.rk.mtms.entity.Payment;
import com.rk.mtms.entity.Receipt;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt,Long> {
    @Modifying
    @Query(value="insert into Receipt(payment_id, total_amount, total_booked_seat, type_booked) VALUES(:paymentId,:totalAmount,:totalBookedSeat,:typeBooked)",nativeQuery = true)
    @Transactional
    void insertIntoReceiptTable(@Param("paymentId") int paymentId, @Param("totalAmount") int totalAmount, @Param("totalBookedSeat") int totalBookedSeat, @Param("typeBooked") String typeBooked);

    @Query("SELECT u from Receipt u where u.paymentId=:RequestedPaymentId")
    Receipt getPaymentForPaymentId(@Param("RequestedPaymentId") int RequestedPaymentId);
}
