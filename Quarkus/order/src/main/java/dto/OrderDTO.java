package dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class OrderDTO {
    private Long customerId;
    private String customerName;
    private Long productId;
    private BigDecimal orderValue;
}
