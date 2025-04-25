package com.example.product_service.config.elasticsearch.repository.search;

import com.example.product_service.config.elasticsearch.model.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, UUID> {
}
