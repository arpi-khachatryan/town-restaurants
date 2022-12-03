package am.itspace.townrestaurantscommon.mapper;

import am.itspace.townrestaurantscommon.dto.restaurant.CreateRestaurantDto;
import am.itspace.townrestaurantscommon.dto.restaurant.RestaurantOverview;
import am.itspace.townrestaurantscommon.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper2 {

//    @Mapping(target = "restaurant", ignore = true)
//    @Mapping(source = "restaurantCategoryId", target = "restaurantCategory.id")
    Restaurant mapToEntity(CreateRestaurantDto createRestaurantDto);

//    @Mapping(source = "restaurant.restaurantCategory",target = "restaurantCategoryOverview")
    RestaurantOverview mapToResponseDto(Restaurant restaurant);

    List<RestaurantOverview> mapToResponseDtoList(List<Restaurant> restaurants);
}