package com.inventory.manager.services;

import com.inventory.manager.domain.location.Location;
import com.inventory.manager.domain.product.Product;
import com.inventory.manager.domain.stock.*;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductService productService;
    @Autowired
    LocationService locationService;

    public Stock findById (Long productId, Long locationId){
        StockId stockId = new StockId(productId, locationId);

        return stockRepository.findById(stockId).orElseThrow(
                ()->new ResourceNotFoundException(
                        "Stock do produto id \"" + productId
                                + "\" e localização id \"" + locationId
                                + "\" não encontrado!")
        );
    }

    public List<StockDTO> findAll (){
        List<Stock> stockList = stockRepository.findAll();
        return stockList.stream().map(StockDTO::new).collect(Collectors.toList());
    }

    public StockDTO createStock (StockRequestDTO stockRequestDTO){
        Product product = productService.findById(stockRequestDTO.productId());
        Location location = locationService.findById(stockRequestDTO.locationId());
        StockId stockId = new StockId(product.getId(), location.getId());
        
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setProduct(product);
        stock.setLocation(location);
        stock.setAmount(stockRequestDTO.amount());
        stock.setIsActive(true);

        Stock savedStock = stockRepository.save(stock);
        return new StockDTO(savedStock);
    }

    @Transactional
    public StockDTO updateStock (StockDTO stockDTO){
        Stock dto = findById(stockDTO.productId(), stockDTO.locationId());
        dto.setAmount(stockDTO.amount());
        dto.setIsActive(stockDTO.isActive());

        Stock stock = stockRepository.save(dto);
        return new StockDTO(stock);
    }

    public void deleteById (Long productId, Long locationId){
        StockId stockId = new StockId (productId, locationId);
        if(!stockRepository.existsById(stockId)){
            throw new ResourceNotFoundException(
                    "Stock do produto id \"" + productId
                            + "\" e localização id \"" + locationId
                            + "\" não encontrado!");

        }
        stockRepository.deleteById(stockId);
    }
}
