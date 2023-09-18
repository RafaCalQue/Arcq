package Project.Arcq.data.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductRequestDto {

   private LocalDateTime date;
   private Long productId;
   private Long brandId;

}
