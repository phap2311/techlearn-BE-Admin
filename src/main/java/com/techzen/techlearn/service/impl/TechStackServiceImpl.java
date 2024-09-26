package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.TechStackResponseDTO;
import com.techzen.techlearn.entity.TechStackEntity;
import com.techzen.techlearn.mapper.TechStackMapper;
import com.techzen.techlearn.repository.TechStackRepository;
import com.techzen.techlearn.service.TechStackService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class TechStackServiceImpl implements TechStackService {

    TechStackRepository techStackRepository;
    TechStackMapper techStackMapper;

    @Override
    public PageResponse<?> getAllTechStacks(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        Page<TechStackEntity> techStack = techStackRepository.findAll(pageable);
        List<TechStackResponseDTO> list = techStack.map(techStackMapper::toTechStackResponse)
                .stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(size)
                .totalPage(techStack.getTotalPages())
                .items(list)
                .build();
    }
}
