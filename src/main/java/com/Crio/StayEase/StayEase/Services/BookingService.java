package com.Crio.StayEase.StayEase.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Crio.StayEase.StayEase.Entity.Booking;
import com.Crio.StayEase.StayEase.Entity.Hotel;
import com.Crio.StayEase.StayEase.Entity.User;
import com.Crio.StayEase.StayEase.Exceptions.ResourceNotFoundException;
import com.Crio.StayEase.StayEase.Repository.BookingRepository;
import com.Crio.StayEase.StayEase.Repository.HotelRepository;
import com.Crio.StayEase.StayEase.Repository.UserRepository;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    UserRepository userRepository;

    public Booking bookRoom(Long userId, Long hotelId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        if (hotel.getAvailableRooms() <= 0) {
            throw new IllegalStateException("No rooms available");
        }

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotelRepository.save(hotel);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHotel(hotel);
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        Hotel hotel = booking.getHotel();
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
        hotelRepository.save(hotel);
        bookingRepository.deleteById(bookingId);
    }

    

    
} 
