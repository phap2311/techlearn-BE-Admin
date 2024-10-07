package com.techzen.techlearn.dto.response;

import com.techzen.techlearn.entity.CurrencyEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PointResponseDTO {
    String id;
    String name;
    String points;
    String price;
    String idCurrency;
}
