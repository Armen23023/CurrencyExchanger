package com.example.CurrencyExchanger.repository;

import com.example.CurrencyExchanger.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    boolean existsByName(String name);
    Optional<Market> findByName(String name);

    void deleteByName(String name);
}