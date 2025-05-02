package com.example.product_service.service.impl;

import com.example.product_service.client.SellerClient;
import com.example.product_service.dto.request.ProductAttrValCreateRequest;
import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.dto.request.ProductImageCreateRequest;
import com.example.product_service.dto.request.ProductUpdateRequest;
import com.example.product_service.dto.rest.SellerForCreateProductResponse;
import com.example.product_service.exception.UserNotFoundException;
import com.example.product_service.model.Category;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductAttributeValue;
import com.example.product_service.model.ProductImage;
import com.example.product_service.model.document.ProductDocument;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.repository.search.ProductSearchRepository;
import com.example.product_service.service.CategoryService;
import com.example.product_service.service.LocalFileStorageService;
import com.example.product_service.service.ProductSearchService;
import com.example.product_service.service.ProductService;
import com.example.product_service.service.mapper.ProductAttributeValueMapper;
import com.example.product_service.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductSearchService productSearchService;
    private final ProductRepository productRepository;
    private final SellerClient sellerClient;
    private final ProductMapper productMapper;
    private final ProductAttributeValueMapper productAttributeValueMapper;
    private final LocalFileStorageService localFileStorageService;
    private final ProductSearchRepository productSearchRepository;
    private final CategoryService categoryService;

    @Value("${file.paths.products.relative-location}")
    private String IMG_DIR_PATH;

    @Autowired
    public ProductServiceImpl(ProductSearchService productSearchService, ProductRepository productRepository, SellerClient sellerClient, ProductMapper productMapper, ProductAttributeValueMapper productAttributeValueMapper, LocalFileStorageService localFileStorageService, ProductSearchRepository productSearchRepository, CategoryService categoryService) {
        this.productSearchService = productSearchService;
        this.productRepository = productRepository;
        this.sellerClient = sellerClient;
        this.productMapper = productMapper;
        this.productAttributeValueMapper = productAttributeValueMapper;
        this.localFileStorageService = localFileStorageService;
        this.productSearchRepository = productSearchRepository;
        this.categoryService = categoryService;
    }


    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product create(ProductCreateRequest data, List<MultipartFile> files) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SellerForCreateProductResponse sellerClientResponse = sellerClient.getSellerData().getBody();
        Product product = new Product();
        product.setId(UUID.randomUUID());
        if (authentication.getCredentials() instanceof UUID sellerId) {
            product.setSellerId(sellerId);
        } else {
            throw new UserNotFoundException("Ошибка при создании продукта: пользователь не найден.");
        }

        productMapper.fromProductCreateRequest(data, product);
        List<ProductAttributeValue> attributeList = new ArrayList<>();
        for (ProductAttrValCreateRequest attributeDTO : data.getAttributes()) {
            ProductAttributeValue attribute = productAttributeValueMapper.fromProductAttrValCreateRequest(attributeDTO);
            attribute.setProduct(product);
            attributeList.add(attribute);
        }
        product.setAttributes(attributeList);
        product.setRating(0);
        product.setNumberOfPurchases(0);
        Category category;
        if (data.getCategoryId() == null) {
            category = categoryService.getRootCategory();
        } else {
            category = categoryService.getCategoryById(data.getCategoryId());
        }
        product.setCategory(category);

        // добавление изображений
        List<ProductImageCreateRequest> imagesDTO = data.getImages();
        if (files.size() != imagesDTO.size()) {
            throw new IllegalArgumentException("Количество файлов и описаний изображений не совпадает.");
        }
        List<ProductImage> productImageList = new ArrayList<>();
        String filedir = IMG_DIR_PATH + "\\" + product.getId();
        for (int i = 0; i < imagesDTO.size(); i++) {
            MultipartFile file = files.get(i);
            ProductImageCreateRequest imgDTO = imagesDTO.get(i);

            String filename = product.getId() + "-" + file.getOriginalFilename();

            ProductImage image = new ProductImage();
            image.setProduct(product);
            image.setFileName(filename);
            image.setUrl(filedir + "\\" + filename);
            image.setSortOrder(imgDTO.getSortOrder());
            localFileStorageService.save(filename, file);
            productImageList.add(image);
        }
        product.setImages(productImageList);

        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(product.getId());
        productDocument.setCategoryName(category.getName());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());
        productDocument.setRating(product.getRating());
        productDocument.setCreatedAt(product.getCreatedAt());
        for (ProductAttributeValue attr : product.getAttributes()) {
            productDocument.addAttribute(new ProductDocument.Attribute(attr.getGroup(), attr.getName(), attr.getValue()));
        }
        productDocument.setSeller(new ProductDocument.Seller(
                sellerClientResponse.getSellerProfile().getFullCompanyName(),
                sellerClientResponse.getSellerProfile().getShortCompanyName()
        ));
        productSearchRepository.save(productDocument);

        return save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Product update(ProductUpdateRequest request) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getAllProductsBySellerId(UUID id, Pageable pageable) {
        return null;
    }
}
