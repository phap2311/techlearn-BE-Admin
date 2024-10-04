package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.PointRequestDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.PointResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface PointService {
    PointResponseDTO createPoints(PointRequestDTO requestDTO);
    PageResponse<?> findAllPoints(int page, int pageSize);
    PointResponseDTO findPointsById(Integer id);
    PointResponseDTO updatePoint(Integer id,PointRequestDTO pointRequestDTO);
    void deletePointsById (Integer id);

}
