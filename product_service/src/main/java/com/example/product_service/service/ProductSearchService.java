package com.example.product_service.service;

import com.example.product_service.dto.request.ProductSearchRequest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ProductSearchService {

    List<UUID> searchIdsByQuery(ProductSearchRequest data) throws IOException;
}
