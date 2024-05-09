package com.empresa.hito_2_3t_programacion_fx.DTO;

import java.math.BigDecimal;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DataDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
}
