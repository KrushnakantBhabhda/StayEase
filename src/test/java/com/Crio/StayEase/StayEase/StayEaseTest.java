package com.Crio.StayEase.StayEase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.Crio.StayEase.StayEase.Controller.AuthController;
import com.Crio.StayEase.StayEase.Controller.BookingController;
import com.Crio.StayEase.StayEase.Dto.BookHotelDto;
import com.Crio.StayEase.StayEase.Entity.Booking;
import com.Crio.StayEase.StayEase.Entity.User;
import com.Crio.StayEase.StayEase.Services.BookingService;
import com.Crio.StayEase.StayEase.Services.UserService;

public class StayEaseTest {

     @Test
    public void test_booking_with_valid_hotelId_and_userId() {
        // Arrange
        BookingService bookingService = mock(BookingService.class);
        BookingController bookingController = new BookingController();
        ReflectionTestUtils.setField(bookingController, "bookingService", bookingService);
    
        BookHotelDto bookHotelDto = new BookHotelDto();
        bookHotelDto.setHotelId(1L);
        bookHotelDto.setUserId(1L);
    
        Booking expectedBooking = new Booking();
        when(bookingService.bookRoom(1L, 1L)).thenReturn(expectedBooking);
    
        // Act
        Booking actualBooking = bookingController.bookRoom(bookHotelDto);
    
        // Assert
        assertEquals(expectedBooking, actualBooking);
    }

     @Test
    public void test_cancel_booking_success() {
        BookingService bookingService = mock(BookingService.class);
        BookingController bookingController = new BookingController();
        ReflectionTestUtils.setField(bookingController, "bookingService", bookingService);

        Long validBookingId = 1L;
        doNothing().when(bookingService).cancelBooking(validBookingId);

        ResponseEntity<?> response = bookingController.cancelBooking(validBookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking cancelled successfully", response.getBody());
    }

    @Test
    public void register_valid_user_success() {
        UserService userService = Mockito.mock(UserService.class);
        AuthController authController = new AuthController();
        ReflectionTestUtils.setField(authController, "userService", userService);
    
        User validUser = new User();
        validUser.setFirstName("validUser");
        validUser.setPassword("validPassword");
    
        ResponseEntity<?> response = authController.registerUser(validUser);
    
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }



    
}
