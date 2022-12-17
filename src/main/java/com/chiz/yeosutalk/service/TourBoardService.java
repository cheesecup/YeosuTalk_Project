package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.Member;
import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.dto.TourBoardDto;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.repository.MemberRepository;
import com.chiz.yeosutalk.repository.TourBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class TourBoardService {

    private final TourBoardRepository tourBoardRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public TourBoardService(TourBoardRepository tourBoardRepository,
                            MemberRepository memberRepository) {
        this.tourBoardRepository = tourBoardRepository;
        this.memberRepository = memberRepository;
    }

    /* 관광 게시판 게시글 작성 서비스 */
    public Long createTourBoard(TourBoardFormDto tourBoardFormDto) {
        Member member = memberRepository.findByAccountId(tourBoardFormDto.getAccountId());
        if (member == null) {
            return null;
        }

        TourBoard tourBoard = TourBoard.toEntity(tourBoardFormDto);
        tourBoard.setMember(member);

        TourBoard savedTourBoard = tourBoardRepository.save(tourBoard);

        return savedTourBoard.getId();
    }

    /* 관광 게시판 전체 게시글 조회 */
    public List<TourBoard> tourBoardList() {
        List<TourBoard> tourBoardList = tourBoardRepository.findAll();
        return tourBoardList;
    }

    /* 관광 게시판 게시글 상세내용 조회 서비스 */
    public TourBoardDto postInfo(Long id) {
        TourBoard tourBoard = tourBoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
        return TourBoardDto.toTourBoardDto(tourBoard);
    }

    /* 관광 게시판 게시글 수정 서비스 */
    public Long updateTourPost(Long id, TourBoardFormDto tourBoardFormDto) {
        TourBoard tourBoard = tourBoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
        tourBoard.setTitle(tourBoardFormDto.getTitle());
        tourBoard.setContent(tourBoardFormDto.getContent());

        TourBoard updatedTourBoard = tourBoardRepository.save(tourBoard);

        return updatedTourBoard.getId();
    }

    /* 관광 게시판 게시글 삭제 */
    public boolean deleteTourPost(Long id) {
        TourBoard tourBoard = tourBoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
        if (tourBoard == null) {
            return false;
        }

        tourBoardRepository.delete(tourBoard);
        return true;
    }

    /* 관광 게시판 검색 서비스 */
    public void searchTourBoardList(String category, String keyword) {

    }

}
