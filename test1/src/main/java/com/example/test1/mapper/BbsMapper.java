package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Bbs;

@Mapper
public interface BbsMapper {
	// 게시글 목록
	List<Bbs> selectBbsList(HashMap<String, Object> map);
	
	// 게시글 전체 개수
	int selectBbsListCnt(HashMap<String, Object> map);
	
	// 게시글 삽입
	int insertBbs(HashMap<String, Object> map);
	
	// 게시글 삭제
	int deleteBbs(HashMap<String, Object> map);
	
	// 게시글 상세 조회
	Bbs selectBbs(HashMap<String, Object> map);
	
	// 게시글 수정
	int updateBbs(HashMap<String, Object> map);
	
	
}
