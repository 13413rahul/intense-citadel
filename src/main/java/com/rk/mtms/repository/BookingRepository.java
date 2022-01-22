package com.rk.mtms.repository;

import com.rk.mtms.entity.Booking;
import com.rk.mtms.enums.BookingType;
import com.rk.mtms.enums.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookingRepository extends CrudRepository<Booking,Long> {
    @Modifying
    @Query(value="insert into Booking(amount,seat_list,show_id,status,user_id,receipt_id,type_booked) VALUES(:amount,:seatList,:showId,:status,:useId,:receiptId,:bookingType)",nativeQuery = true)
    @Transactional
    void insertIntoBookingTable(@Param("amount") int amount, @Param("seatList") String seatList, @Param("showId") int showId, @Param("status") String Status, @Param("useId") int useId, @Param("receiptId") int receiptId, @Param("bookingType") String bookingType);

    @Query("SELECT u from Booking u where u.userId=:RequestedUserId")
    Booking getBookingForUserId(@Param("RequestedUserId") int RequestedUserId);

    @Modifying
    @Query(value = "UPDATE Booking SET receipt_id = :RequestedReceiptId and status = :RequestedStatus WHERE user_id = :RequestedUserId", nativeQuery = true)
    @Transactional
    void updateBookingTable(@Param("RequestedReceiptId") int RequestedReceiptId, @Param("RequestedStatus") String RequestedStatus, @Param("RequestedUserId") int RequestedUserId);

    @Modifying
    @Query(value = "DELETE FROM Booking WHERE user_id = :RequestedUserId", nativeQuery = true)
    @Transactional
    void deleteBookingDetail(@Param("RequestedUserId") int RequestedUserId);
}
