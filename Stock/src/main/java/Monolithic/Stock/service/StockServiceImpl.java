package Monolithic.Stock.service;

import Monolithic.Stock.bo.AccountResponse;
import Monolithic.Stock.bo.CreateStockRequest;
import Monolithic.Stock.bo.StockRequest;
import Monolithic.Stock.bo.UpdateStockResponse;
import Monolithic.Stock.entity.StockEntity;
import Monolithic.Stock.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Override
    public UpdateStockResponse updateStock(CreateStockRequest request) {

        UpdateStockResponse orderStatus = new UpdateStockResponse();

        try{
            Iterable<StockEntity> inventories = stockRepository.findByItem(request.getItem());
            boolean exists = inventories.iterator().hasNext();
            if(!exists){
                System.out.println("Stock not exist so reverting the order");
                throw new Exception("Stock not available");
            }
            inventories.forEach(i->{
                i.setQuantity(i.getQuantity()-request.getQuantity());
                stockRepository.save(i);

                orderStatus.setItem(i.getItem());
                orderStatus.setOrderId(Integer.valueOf(i.getId().intValue()));
                orderStatus.setRemainingQty(i.getQuantity());
                orderStatus.setStatus("Success");
            });


        } catch (Exception e) {
            throw new RuntimeException(e);
        }






        return orderStatus;
    }

    @Override
    public void addItems(CreateStockRequest stock) {
        Iterable<StockEntity> items = stockRepository.findByItem(stock.getItem());

        if(items.iterator().hasNext()){
            items.forEach(i->{
                i.setQuantity(i.getQuantity()+stock.getQuantity());
                stockRepository.save(i);
            });
        }else {
            StockEntity i = new StockEntity();
            i.setItem(stock.getItem());
            i.setQuantity(stock.getQuantity());
            i.setPrice(stock.getPrice());
            stockRepository.save(i);
        }
    }

    @Override
    public AccountResponse getAllStocks() {
        AccountResponse response = new AccountResponse();
        try{
            List<StockEntity> stockEntities = stockRepository.findAll();

            List<StockRequest> stocks = stockEntities.stream()
                    .map(stockEntity -> {
                        StockRequest stock = new StockRequest();
                        stock.setItem(stockEntity.getItem());
                        stock.setRemainingQuantity(stockEntity.getQuantity());
                        stock.setPrice(stockEntity.getPrice());
                        stock.setStatus("Available");
                        return stock;

            }).collect(Collectors.toList());

            response.setStocks(stocks);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return response;
    }


    @Override
    public AccountResponse filteredByPrice(double price) {
        AccountResponse response = new AccountResponse();
        try{
            List<StockEntity> stockEntities = stockRepository.findAll();

            List<StockRequest> stocks = stockEntities.stream()
                    .filter(stockEntity -> stockEntity.getPrice()>=price)
                    .map(stockEntity -> {
                        StockRequest stock = new StockRequest();
                        stock.setItem(stockEntity.getItem());
                        stock.setRemainingQuantity(stockEntity.getQuantity());
                        stock.setPrice(stockEntity.getPrice());
                        stock.setStatus("Available");
                        return stock;

                    }).collect(Collectors.toList());

            response.setStocks(stocks);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return response;
    }




}
