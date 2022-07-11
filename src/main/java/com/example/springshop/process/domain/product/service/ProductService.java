package com.example.springshop.process.domain.product.service;

import com.example.springshop.process.domain.model.CategoryType;
import com.example.springshop.process.domain.product.dto.CreateProductDto;
import com.example.springshop.process.domain.product.dto.ProductInfoDto;
import com.example.springshop.process.domain.product.dto.UpdateProductInfoDto;
import com.example.springshop.process.domain.product.dto.response.CategoryRankingResponseDto;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.productImage.domain.ProductImage;
import com.example.springshop.process.domain.productImage.service.ProductImageService;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userSerivce;

    public void createProduct(Long userId, CreateProductDto createProductDto){
        Product product = new Product(createProductDto.getProductName(), createProductDto.getCategory(), createProductDto.getProductPrice(), userSerivce.findUserById(userId));

        productRepository.save(product);

    }

    public void productAddLike(Long productId){
        Product product = findProductByid(productId);
        product.productAddLike();

    }

    public void productCencelLike(Long productId) {
        Product product = findProductByid(productId);
        product.productCancelLike();
    }

    public Product findProductByid(Long productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("상품이 없습니다."));
    }

    public ProductInfoDto selectProductInfo(Long productId) {
        // productId를 이용해서 정보랑 imagefile까지 반환

        Product product = findProductByid(productId);
        List<ProductImage> productImageList = product.getProductImage();

        ProductInfoDto productInfoDto = new ProductInfoDto(product, productImageList);
        return productInfoDto;
    }

    public void updateProductInfo(Long productId, UpdateProductInfoDto updateProductInfoDto) {
        Product product = findProductByid(productId);
        product.updateProductInfo(
                updateProductInfoDto.getProductName(),
                updateProductInfoDto.getCategory(),
                updateProductInfoDto.getProductPrice()
        );
        productRepository.save(product);

    }

    public void deleteProduct(Long productId) {
        Product product = findProductByid(productId);
        productRepository.delete(product);
    }

    public CategoryRankingResponseDto findCategoryRanking(CategoryType categoryType) {
        List<Product> productList = productRepository.findAllByCategoryOrderByLikeCountDesc(categoryType);
        return new CategoryRankingResponseDto(
                productList
        );
    }
}
