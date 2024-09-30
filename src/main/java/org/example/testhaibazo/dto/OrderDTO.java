package org.example.testhaibazo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private Double totalPrice;
    private Long userId;
    private List<Long> productIds;
}
