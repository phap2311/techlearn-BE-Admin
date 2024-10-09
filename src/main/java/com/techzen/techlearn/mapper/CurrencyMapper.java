package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.response.CurrencyResponseDTO;
import com.techzen.techlearn.entity.CurrencyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyResponseDTO toCurrencyResponseDTO(CurrencyEntity currency);
}
