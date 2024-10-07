package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.response.CurrencyResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService{
    List<CurrencyResponseDTO> findAllCurrency();
}
