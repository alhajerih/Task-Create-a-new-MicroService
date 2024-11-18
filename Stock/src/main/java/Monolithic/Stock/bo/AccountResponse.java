package Monolithic.Stock.bo;

import java.util.List;

public class AccountResponse {
    private List<StockRequest>stocks;

    public List<StockRequest> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockRequest> stocks) {
        this.stocks = stocks;
    }
}
