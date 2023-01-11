package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.dto.TourBoardDto;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.repository.TourBoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TourBoardServiceTest {

    @Autowired TourBoardService tourBoardService;
    @Autowired TourBoardRepository tourBoardRepository;

    @Test
    @DisplayName("관광 게시판 게시글 저장 서비스 테스트")
    void save_tourBoard_service_test() {
        //Given
        TourBoardFormDto tourBoardFormDto = new TourBoardFormDto("titleA", "contentA", "writerA", "gmeekins2@godaddy.com");

        //When
        Long tourBoardId = tourBoardService.createTourBoard(tourBoardFormDto);
        TourBoard tourBoard = tourBoardRepository.findById(tourBoardId).orElseThrow(EntityNotFoundException::new);

        //Then
        assertThat(tourBoardId).isEqualTo(tourBoard.getId());
        assertThat(tourBoard.getMember().getAccountId()).isEqualTo("gmeekins2@godaddy.com");
    }

    @Test
    @DisplayName("관광 게시판 게시글 전체 조회")
    void findAllTourBoardList() {
        //Given
        Optional<Integer> page = Optional.of(1);

        //When
        Page<TourBoardDto> tourBoardList = tourBoardService.tourBoardList(page);

        //Given
        for (int i=0; i<tourBoardList.getSize(); i++) {
            System.out.println(i + "번째 게시글 제목 = " + tourBoardList.getContent().get(i).getTitle());
        }
    }

    @Test
    @DisplayName("관광 게시판 게시글 상세 조회 서비스 테스트")
    void findTourBoardPostByIdTest() {
        //When
        TourBoardDto tourBoardDto = tourBoardService.postInfo(1L);

        //Then
        assertThat(tourBoardDto.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("관광 게시판 게시글 수정 서버스 테스트")
    void updateTourPostTest() {
        TourBoardFormDto tourBoardFormDto = new TourBoardFormDto("updateTitle", "updateContent", "gmeekins2", "gmeekins2@godaddy.com");
        TourBoard tourBoard = tourBoardRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));

        //When
        Long updateId = tourBoardService.updateTourPost(1L, tourBoardFormDto);

        //Then
        assertThat(updateId).isEqualTo(1L);
    }

    @Test
    @DisplayName("관광 게시판 작성자 검색 테스트")
    void searchTourBoardTest() {
        //Given
        String category = "writer";
        String keyword = "kiddons";

        //When
        List<TourBoardDto> tourBoardList = tourBoardService.searchTourBoardList(category, keyword);

        //Then
        for (TourBoardDto tourBoard : tourBoardList) {
            System.out.println(tourBoard.getWriter());
        }
    }
}