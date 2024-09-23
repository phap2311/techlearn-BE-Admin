package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.response.PageResponse;
import org.springframework.stereotype.Service;

@Service
public interface TechStackService {

    PageResponse<?> getAllTechStacks(int page, int size);
}
