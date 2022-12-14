package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.dto.TourBoardDto;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.repository.TourBoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TourBoardServiceTest {

    @Autowired TourBoardService tourBoardService;
    @Autowired TourBoardRepository tourBoardRepository;

//    @BeforeEach
//    void before() {
//        tourBoardRepository.deleteAll();
//    }

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
        long count = tourBoardRepository.count();

        //When
        List<TourBoard> tourBoardList = tourBoardService.tourBoardList();

        //Given
        assertThat(tourBoardList.size()).isEqualTo(count);
    }

    @Test
    @DisplayName("관광 게시판 게시글 상세 조회 서비스 테스트")
    void findTourBoardPostByIdTest() {
        //When
        TourBoardDto tourBoardDto = tourBoardService.postInfo(1L);

        //Then
        assertThat(tourBoardDto.getWriter()).isEqualTo("mlerhinan0");
        System.out.println(tourBoardDto.getContent());
    }

    @Test
    @DisplayName("관광 게시판 게시글 수정 서버스 테스트")
    void updateTourPostTest() {
        TourBoardFormDto tourBoardFormDto = new TourBoardFormDto("updateTitle", "updateContent", "mlerhinan0");
        TourBoard tourBoard = tourBoardRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));

        //When
        Long updateId = tourBoardService.updateTourPost(1L, tourBoardFormDto);

        //Then
        assertThat(updateId).isEqualTo(1L);
    }
}