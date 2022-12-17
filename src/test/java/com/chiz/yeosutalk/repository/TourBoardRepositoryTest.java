package com.chiz.yeosutalk.repository;

import com.chiz.yeosutalk.domain.TourBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TourBoardRepositoryTest {

    @Autowired
    TourBoardRepository tourBoardRepository;

//    @BeforeEach
//    void before() {
//        tourBoardRepository.deleteAll();
//    }

    @Test
    @DisplayName("관광 게시글 저장 테스트")
    void create_tour_board_test() {
        //Given
        TourBoard tourBoard = new TourBoard("관광 게시글 제목", "관광 게시글 내용", "게시글 작성자");

        //When
        TourBoard savedBoard = tourBoardRepository.save(tourBoard);

        //Then
        assertThat(savedBoard.getTitle()).isEqualTo("관광 게시글 제목");

    }

}