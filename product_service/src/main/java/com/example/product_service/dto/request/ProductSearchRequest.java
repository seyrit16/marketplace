package com.example.product_service.dto.request;

import lombok.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSearchRequest {
    private String query;
    private String sortBy;
    @Pattern(regexp = "^(ASC|DESC)$", message = "Порядок сортировки должен быть 'ASC' или 'DESC'")
    private String sortOrder;
    private Pageable pageable;

    @Valid
    private List<FilterField> filterFields = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class FilterField{
        private String name;
        private String value;
        private String min;
        private String max;
    }
}
