package com.kafpin.smallDelivery.services;

import com.kafpin.smallDelivery.models.Stock;
import com.kafpin.smallDelivery.repositoryes.StockRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class StockService {
    private final StockRepository repository;

    public List<Stock> getAll(int page, int perPage) {
        var pag = PageRequest.of(page, perPage);
        return repository.findAll(pag).stream().toList();
    }
    public Optional<Stock> findById(Long id) {
        return repository.findById(id);
    }

    public Stock create(Stock stock) {
        return repository.save(stock);
    }
    public Stock update(Stock stock) {
        return repository.save(stock);
    }
    public void delete(Stock stock) {
        repository.delete(stock);
    }
}
