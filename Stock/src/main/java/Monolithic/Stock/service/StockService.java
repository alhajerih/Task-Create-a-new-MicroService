package Monolithic.Stock.service;

import Monolithic.Stock.bo.AccountResponse;
import Monolithic.Stock.bo.CreateStockRequest;
import Monolithic.Stock.bo.UpdateStockResponse;

public interface StockService {
    UpdateStockResponse updateStock(CreateStockRequest request);

    void addItems(CreateStockRequest stock);
    AccountResponse getAllStocks();
    AccountResponse filteredByPrice(double price);
}
