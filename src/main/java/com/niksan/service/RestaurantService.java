package com.niksan.service;

import com.niksan.dto.RestaurantDto;
import com.niksan.model.Restaurant;
import com.niksan.model.User;
import com.niksan.request.CreateRestaurantRequest;
import lombok.extern.java.Log;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest req) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant>  getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
