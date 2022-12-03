package am.itspace.townrestaurantsrest.controller;


import am.itspace.townrestaurantscommon.dto.restaurantCategory.CreateRestaurantCategoryDto;
import am.itspace.townrestaurantscommon.dto.restaurantCategory.EditRestaurantCategoryDto;
import am.itspace.townrestaurantscommon.dto.restaurantCategory.RestaurantCategoryOverview;
import am.itspace.townrestaurantsrest.api.RestaurantCategoryApi;
import am.itspace.townrestaurantsrest.serviceRest.RestaurantCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantsCategory")
public class RestaurantCategoryEndpoint implements RestaurantCategoryApi {

    private final RestaurantCategoryService restaurantCategoryService;

    @Override
    @PostMapping
    public ResponseEntity<RestaurantCategoryOverview> create(@Valid @RequestBody CreateRestaurantCategoryDto createRestaurantCategoryDto) {
        return ResponseEntity.ok(restaurantCategoryService.save(createRestaurantCategoryDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RestaurantCategoryOverview>> getAll() {
        return ResponseEntity.ok(restaurantCategoryService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantCategoryOverview> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(restaurantCategoryService.getById(id));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantCategoryOverview> update(@Valid @PathVariable("id") int id,
                                                             @RequestBody EditRestaurantCategoryDto editRestaurantCategoryDto) {
        return ResponseEntity.ok(restaurantCategoryService.update(id, editRestaurantCategoryDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        restaurantCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
