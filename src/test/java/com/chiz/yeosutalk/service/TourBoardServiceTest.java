package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.repository.TourBoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TourBoardServiceTest {

    @Autowired TourBoardService tourBoardService;
    @Autowired TourBoardRepository tourBoardRepository;

    @BeforeEach
    void before() {
        tourBoardRepository.deleteAll();
    }

    @Test
    @DisplayName("관광 게시판 게시글 저장 서비스 테스트")
    void save_tourBoard_service_test() {
        //Given
        TourBoardFormDto tourBoardFormDto = new TourBoardFormDto("titleA", "contentA", "writerA");

        //When
        Long tourBoardId = tourBoardService.createTourBoard(tourBoardFormDto);
        TourBoard tourBoard = tourBoardRepository.findById(tourBoardId).orElseThrow(EntityNotFoundException::new);

        //Then
        assertThat(tourBoardId).isEqualTo(tourBoard.getId());
    }

    @Test
    @DisplayName("관광 게시판 게시글 전체 조회")
    void findAllTourBoardList() {
        //Given
        for (int i=0; i< 5; i++) {
            TourBoardFormDto tourBoardFormDto = new TourBoardFormDto("title"+i, "content"+i, "writer"+i);
            TourBoard tourBoard = TourBoard.toEntity(tourBoardFormDto);
            tourBoardRepository.save(tourBoard);
        }

        //When
        List<TourBoard> tourBoardList = tourBoardService.tourBoardList();

        //Given
        assertThat(tourBoardList.size()).isEqualTo(5);
    }
}