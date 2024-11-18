package Monolithic.Stock.controller;

import Monolithic.Stock.bo.AccountResponse;
import Monolithic.Stock.bo.CreateStockRequest;
import Monolithic.Stock.bo.UpdateStockResponse;
import Monolithic.Stock.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StockController {

    private final StockService stockService;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }



    @PostMapping("/addItems")
    public void addItems(@RequestBody CreateStockRequest stock){
        stockService.addItems(stock);
    }

    @PostMapping("/updateStock")
    public UpdateStockResponse updateStock(@RequestBody CreateStockRequest request) {
        return stockService.updateStock(request);
    }

    @GetMapping("/allStocks")
    public AccountResponse getAllStocks() {
        return stockService.getAllStocks();
    }

    @PostMapping("/filtered")
    public AccountResponse filteredByPrice(@RequestParam Integer price) {
        return stockService.filteredByPrice(price);
    }


}
