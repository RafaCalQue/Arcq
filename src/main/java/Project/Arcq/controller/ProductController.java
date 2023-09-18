package Project.Arcq.controller;


import Project.Arcq.data.dto.ProductRequestDto;
import Project.Arcq.data.dto.ProductResponseDto;
import Project.Arcq.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/price")
    public ResponseEntity<ProductResponseDto> getPrice(@RequestBody ProductRequestDto productRequest) {
        try {
            ProductResponseDto response = productService.getPrice(productRequest);
            if (response != null) {
                return ResponseEntity.ok(response);

            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}


