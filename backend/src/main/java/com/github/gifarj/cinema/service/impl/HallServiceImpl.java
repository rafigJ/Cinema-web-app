package com.github.gifarj.cinema.service.impl;

import com.github.gifarj.cinema.dto.HallDto;
import com.github.gifarj.cinema.entity.HallEntity;
import com.github.gifarj.cinema.exception.BadRequestException;
import com.github.gifarj.cinema.exception.NotFoundException;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.repository.HallRepository;
import com.github.gifarj.cinema.service.HallService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Page<HallDto> getHalls(Pageable pageable) {
        Page<HallEntity> hallEntities = repository.findAll(pageable);
        return hallEntities.map(h -> modelMapper.map(h, HallDto.class));
    }

    @Override
    public HallDto createHall(HallDto hallDto) {
        HallEntity entity = modelMapper.map(hallDto, HallEntity.class);
        try {
            HallEntity createdHall = repository.saveAndFlush(entity);
            return modelMapper.map(createdHall, HallDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Client error: " + e.getMessage());
        } catch (RuntimeException e) {
            throw new RestException("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public HallDto updateHall(Integer id, HallDto hallDto) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Hall by id: " + id + " not found");
        }
        HallEntity hallEntity = modelMapper.map(hallDto, HallEntity.class);
        hallEntity.setId(id);
        repository.save(hallEntity);
        return modelMapper.map(hallEntity, HallDto.class);
    }

    @Override
    public void deleteHall(Integer id) {
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Client error: id must be not null");
        }
    }
}
