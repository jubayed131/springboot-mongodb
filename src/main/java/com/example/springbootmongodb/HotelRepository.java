package com.example.springbootmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String> {
    Hotel findHotelById(String id);
    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    @Query(value = "{'address.city':?0}")
    List<Hotel> findByCity(String city);
}
