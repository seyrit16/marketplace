package com.example.product_service.service.impl;

import ch.qos.logback.classic.Logger;
import com.example.product_service.dto.request.ProductSearchRequest;
import com.example.product_service.service.ProductSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.AbstractHighlighterBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import javax.swing.text.Highlighter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ProductSearchServiceImpl implements ProductSearchService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final RestHighLevelClient client;
    private Logger log;

    @Autowired
    public ProductSearchServiceImpl(ElasticsearchOperations elasticsearchOperations, RestHighLevelClient client) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.client = client;
    }

    @Override
    public List<UUID> searchIdsByQuery(ProductSearchRequest data) throws IOException {

        SearchRequest request = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.multiMatchQuery(data.getQuery())
                        .field("name", 5.0f)
                        .field("attributes.name", 1.5f)
                        .field("attributes.value", 2.5f)
                        .field("seller.fullCompanyName", 1.5f)
                        .field("seller.shortCompanyName", 1.5f)
                        .field("categoryName", 1.0f)
                        .field("description", 0.3f)
                        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .sort(
                        data.getSortBy(),
                        "desc".equalsIgnoreCase(data.getSortOrder())
                                ? SortOrder.DESC : SortOrder.ASC
                )
                .from(data.getPageable().getPageNumber()-1)
                .size(data.getPageable().getPageSize())
                .fetchSource(new String[]{"id"}, null);

        request.source(sourceBuilder);

        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        List<UUID> ids = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            ids.add(UUID.fromString((String) sourceAsMap.get("id")));
            System.out.println(ids.get(ids.size()-1));
        }
        return ids;
    }
}
