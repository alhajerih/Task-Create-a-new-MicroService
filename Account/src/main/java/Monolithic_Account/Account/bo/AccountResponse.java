package Monolithic_Account.Account.bo;

import java.util.List;

public class AccountResponse {
    private List<StockRequest> stocks;

    public List<StockRequest> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockRequest> stocks) {
        this.stocks = stocks;
    }
}
