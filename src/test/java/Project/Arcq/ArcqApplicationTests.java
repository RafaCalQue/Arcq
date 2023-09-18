package Project.Arcq;

import Project.Arcq.controller.ProductController;
import Project.Arcq.data.dto.ProductRequestDto;
import Project.Arcq.data.dto.ProductResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ArcqApplicationTests {


    @Autowired
    private ProductController productController;


    @DisplayName("Prueba de control")
    @ParameterizedTest
    @CsvFileSource(resources = "/product.csv")
    void IntegrationTest(ArgumentsAccessor argumentsAccessor){

        ProductRequestDto productRequestData = ProductRequestDto.builder()
                .date(LocalDateTime.of(
                        argumentsAccessor.getInteger(0),
                        argumentsAccessor.getInteger(1),
                        argumentsAccessor.getInteger(2),
                        argumentsAccessor.getInteger(3),0
                ))
                .productId(argumentsAccessor.getLong(4))
                .brandId(argumentsAccessor.getLong(5)).build();

        ResponseEntity<ProductResponseDto> data = productController.getPrice(productRequestData);

        if(data.getStatusCode() == HttpStatus.OK) {
            ProductResponseDto body = data.getBody();
            if (body != null) {


                //probamos los valores basicos del csv
                //date
                assertEquals(body.getApplicationDate(), LocalDateTime.of(
                        argumentsAccessor.getInteger(0),
                        argumentsAccessor.getInteger(1),
                        argumentsAccessor.getInteger(2),
                        argumentsAccessor.getInteger(3), 0));
                //productId
                assertEquals(body.getProductId(), argumentsAccessor.getLong(4));
                //brandId
                assertEquals(body.getBrandId(), argumentsAccessor.getLong(5));

                //Probamos los precios finales
                List<Double> finalPrice = new ArrayList<>();
                //agregamos los valores esperados a la lista (sacados de la logica de la prueba)
                finalPrice.add(35.5);
                finalPrice.add(25.45);
                finalPrice.add(35.5);
                finalPrice.add(30.5);
                finalPrice.add(38.95);
                //cogemos el valor 6 del csv que se ha utilizado como id de contol
                assertEquals(body.getResultPrice(), finalPrice.get(argumentsAccessor.getInteger(6)));

                List<Long> finalPriceList = new ArrayList<>();
                //agregamos los valores esperados a la lista (sacados de la logica de la prueba)
                finalPriceList.add(1L);
                finalPriceList.add(2L);
                finalPriceList.add(1L);
                finalPriceList.add(3L);
                finalPriceList.add(4L);
                assertEquals(body.getPriceList(), finalPriceList.get(argumentsAccessor.getInteger(6)));
            } else {
                System.out.println("error");
            }
        }

    }


}
