package Project.Arcq.data.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDto {
    private Long productId; //identificador de producto
    private Long brandId; //identificador de cadena
    private Long priceList; //tarifa a aplicar
    private LocalDateTime applicationDate; //fecha de aplicacion
    private Double resultPrice; //precio final aplicado


}
