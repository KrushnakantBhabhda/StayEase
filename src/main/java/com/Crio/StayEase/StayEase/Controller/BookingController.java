package com.Crio.StayEase.StayEase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Crio.StayEase.StayEase.Dto.BookHotelDto;
import com.Crio.StayEase.StayEase.Entity.Booking;
import com.Crio.StayEase.StayEase.Entity.User;
import com.Crio.StayEase.StayEase.Services.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/hotels/{hotelId}/book")
    public Booking bookRoom(@RequestBody BookHotelDto bookHotelDto) {
        return bookingService.bookRoom(bookHotelDto.getHotelId(), bookHotelDto.getUserId());
    }

    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
    
}
