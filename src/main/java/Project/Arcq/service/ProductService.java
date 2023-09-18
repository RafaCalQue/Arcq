package Project.Arcq.service;

import Project.Arcq.data.dto.ProductRequestDto;
import Project.Arcq.data.dto.ProductResponseDto;
import Project.Arcq.data.entity.Product;
import Project.Arcq.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto getPrice (ProductRequestDto productRequest){
        ProductResponseDto resultData;
        List<Product> productList = productRepository.findByBrandIdProductIdAndAppDate(
                productRequest.getBrandId(),
                productRequest.getProductId(),
                productRequest.getDate()
        );
        LocalDateTime date = productRequest.getDate();
        if(productList.size()>1){
            Product product = productList.stream().max(Comparator.comparing(Product::getPriority)).get();
            resultData = converter(date, product);
        }
        else if(productList.size() == 1){
            resultData = converter(date , productList.get(0));
        }
        else{ return null;}
        return resultData;
    }

    private ProductResponseDto converter(LocalDateTime date, Product product){
        ProductResponseDto productResponse = new ProductResponseDto();
        productResponse.setProductId(product.getProductId());
        productResponse.setBrandId(product.getBrand().getId());
        productResponse.setPriceList(product.getPriceList());
        productResponse.setResultPrice(product.getPrice());
        productResponse.setApplicationDate(date);
        return productResponse;


    }
}
