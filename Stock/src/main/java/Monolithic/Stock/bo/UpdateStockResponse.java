package Monolithic.Stock.bo;

public class UpdateStockResponse {
    private String item;
    private int remainingQty;
    private String status;
    private int orderId;


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(int remainingQty) {
        this.remainingQty = remainingQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
