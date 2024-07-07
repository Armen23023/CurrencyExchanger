package com.example.CurrencyExchanger.repository;

import com.example.CurrencyExchanger.model.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory,Long> {


}
