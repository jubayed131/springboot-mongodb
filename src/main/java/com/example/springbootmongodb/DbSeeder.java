package com.example.springbootmongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private HotelRepository hotelRepository;


    public DbSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Hotel marriot=new Hotel(
                "Marriot",
                130,
                new Address("France","Paris"),
                Arrays.asList(
                   new Review("John",8,false),
                    new Review("Mike",7,true)
                )
        );

        Hotel ibis=new Hotel(
                "Ibis",
                90,
                new Address("Romania","Bucharest"),
                Arrays.asList(
                        new Review("Jill",9,true)

                )
        );

        Hotel sofitel=new Hotel(
                "Sofitel",
                200,
                new Address("Italy","Rome"),
                new ArrayList<>()
        );
        // drop all hotels
        this.hotelRepository.deleteAll();
        List<Hotel> hotels = Arrays.asList(marriot,ibis,sofitel);
        this.hotelRepository.saveAll(hotels);

    }
}
