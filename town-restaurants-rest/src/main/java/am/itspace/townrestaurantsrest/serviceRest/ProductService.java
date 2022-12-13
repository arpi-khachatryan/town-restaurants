package am.itspace.townrestaurantsrest.serviceRest;

import am.itspace.townrestaurantscommon.dto.FileDto;
import am.itspace.townrestaurantscommon.dto.product.CreateProductDto;
import am.itspace.townrestaurantscommon.dto.product.EditProductDto;
import am.itspace.townrestaurantscommon.dto.product.ProductOverview;
import am.itspace.townrestaurantsrest.exception.EntityNotFoundException;

import java.util.List;

public interface ProductService {

    void delete(int id);

    List<ProductOverview> getAll();

    byte[] getProductImage(String fileName);

    List<ProductOverview> findProductByUser();

    List<ProductOverview> findProductsByRestaurant(int id);

    ProductOverview update(int id, EditProductDto editProductDto);

    ProductOverview getById(int id) throws EntityNotFoundException;

    ProductOverview save(CreateProductDto createProductDto, FileDto fileDto);

    List<ProductOverview> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
}

