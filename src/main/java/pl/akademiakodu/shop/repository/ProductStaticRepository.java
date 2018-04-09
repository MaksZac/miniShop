package pl.akademiakodu.shop.repository;

import org.springframework.stereotype.Component;
import pl.akademiakodu.shop.controller.ProductNotFoundException;
import pl.akademiakodu.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductStaticRepository implements ProductRepository {

    private static List<Product> productList = new ArrayList<>();

    @Override
    public Product findProductByName(String name) throws ProductNotFoundException {
        Product product = null;
        for (Product p : productList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException();
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void remove(Product product) throws ProductNotFoundException {
        boolean success = productList.remove(product);
        if (!success)
            throw new ProductNotFoundException();
    }

    @Override
    public void removeProductByName(String name) throws ProductNotFoundException {
        Product product = findProductByName(name);
        remove(product);
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }
}
