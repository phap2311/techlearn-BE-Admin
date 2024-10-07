package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.response.CurrencyResponseDTO;
import com.techzen.techlearn.entity.CurrencyEntity;
import com.techzen.techlearn.mapper.CurrencyMapper;
import com.techzen.techlearn.repository.CurrencyRepository;
import com.techzen.techlearn.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CurrencyServiceImpl implements CurrencyService {
    CurrencyMapper currencyMapper;
    CurrencyRepository currencyRepository;
    @Override
    public List<CurrencyResponseDTO> findAllCurrency() {
        List<CurrencyEntity> currencies = currencyRepository.findAll();
        return currencies.stream()
                .map(currencyMapper::toCurrencyResponseDTO)
                .collect(Collectors.toList());
    }
}
