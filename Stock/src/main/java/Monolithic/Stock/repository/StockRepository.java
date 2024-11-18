package Monolithic.Stock.repository;

import Monolithic.Stock.StockApplication;
import Monolithic.Stock.bo.CreateStockRequest;
import Monolithic.Stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends JpaRepository<StockEntity,Long> {

    Iterable<StockEntity> findByItem(String item);

}
