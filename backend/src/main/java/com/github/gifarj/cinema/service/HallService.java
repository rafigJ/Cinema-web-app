package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.HallDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HallService {

    Page<HallDto> getHalls(Pageable pageable);

    HallDto createHall(HallDto hallDto);

    HallDto updateHall(Integer id, HallDto hallDto);

    void deleteHall(Integer id);

}
