package com.ryu.mypptbe.domain.restaurant.repository;

import com.ryu.mypptbe.domain.restaurant.Restaurant;
import com.ryu.mypptbe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findByRestaurantId(String restaurantId);

}
