package am.itspace.townrestaurantsrest.serviceRest.impl;

import am.itspace.townrestaurantscommon.dto.restaurantCategory.CreateRestaurantCategoryDto;
import am.itspace.townrestaurantscommon.dto.restaurantCategory.EditRestaurantCategoryDto;
import am.itspace.townrestaurantscommon.dto.restaurantCategory.RestaurantCategoryOverview;
import am.itspace.townrestaurantscommon.entity.RestaurantCategory;
import am.itspace.townrestaurantscommon.mapper.RestaurantCategoryMapper2;
import am.itspace.townrestaurantscommon.repository.RestaurantCategoryRepository;
import am.itspace.townrestaurantsrest.exception.EntityAlreadyExistsException;
import am.itspace.townrestaurantsrest.exception.EntityNotFoundException;
import am.itspace.townrestaurantsrest.exception.Error;
import am.itspace.townrestaurantsrest.serviceRest.RestaurantCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantCategoryServiceImpl implements RestaurantCategoryService {

    private final RestaurantCategoryRepository restaurantCategoryRepository;
    private final RestaurantCategoryMapper2 restaurantCategoryMapper;

    @Override
    public RestaurantCategoryOverview save(CreateRestaurantCategoryDto createRestaurantCategoryDto) {
        if (restaurantCategoryRepository.existsByName(createRestaurantCategoryDto.getName())) {
            log.info("Category with that name already exists {}", createRestaurantCategoryDto.getName());
            throw new EntityAlreadyExistsException(Error.RESTAURANT_CATEGORY_ALREADY_EXISTS);
        }
        log.info("The Category was successfully stored in the database {}", createRestaurantCategoryDto.getName());
        return restaurantCategoryMapper.mapToResponseDto(restaurantCategoryRepository.save(restaurantCategoryMapper.mapToEntity(createRestaurantCategoryDto)));
    }

    @Override
    public List<RestaurantCategoryOverview> getAll() {
        List<RestaurantCategory> categories = restaurantCategoryRepository.findAll();
        if (categories.isEmpty()) {
            log.info("Category not found");
            throw new EntityNotFoundException(Error.RESTAURANT_CATEGORY_NOT_FOUND);
        }
        log.info("Category successfully detected");
        return restaurantCategoryMapper.mapToResponseDtoList(categories);
    }

    @Override
    public RestaurantCategoryOverview getById(int id) {
        RestaurantCategory restaurantCategory = restaurantCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Error.RESTAURANT_CATEGORY_NOT_FOUND));
        log.info("Category successfully found {}", restaurantCategory.getName());
        return restaurantCategoryMapper.mapToResponseDto(restaurantCategory);
    }

    @Override
    public RestaurantCategoryOverview update(int id, EditRestaurantCategoryDto editRestaurantCategoryDto) {
        RestaurantCategory restaurantCategory = restaurantCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Error.RESTAURANT_CATEGORY_NOT_FOUND));
        log.info("Category with that id not found");
        if (editRestaurantCategoryDto.getName() != null) {
            restaurantCategory.setName(editRestaurantCategoryDto.getName());
            restaurantCategoryRepository.save(restaurantCategory);
        }
        log.info("The category was successfully stored in the database {}", restaurantCategory.getName());
        return restaurantCategoryMapper.mapToResponseDto(restaurantCategory);
    }

    @Override
    public void delete(int id) {
        if (restaurantCategoryRepository.existsById(id)) {
            restaurantCategoryRepository.deleteById(id);
            log.info("The category has been successfully deleted");
        } else {
            log.info("Category not found");
            throw new EntityNotFoundException(Error.RESTAURANT_CATEGORY_NOT_FOUND);
        }
    }
}