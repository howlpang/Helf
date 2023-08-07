package kr.co.helf.service;

import kr.co.helf.dto.Pagination;
import kr.co.helf.form.GroupLessonForm;
import kr.co.helf.mapper.GroupLessonMapper;
import kr.co.helf.vo.Lesson;
import kr.co.helf.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GroupLessonService {

    private final GroupLessonMapper groupLessonMapper;

    // 레슨 등록
    public void createLesson(Lesson lesson){
        groupLessonMapper.insertLesson(lesson);
    }
    // 레슨번호로 레슨 상세조회
    public Lesson getLessonByNo(int lessonNo){
        return groupLessonMapper.getLessonByNo(lessonNo);

    }
    //
    // 레슨 목록 조회(페이징처리)
    public Map<String,Object> getAllLessons(Map<String,Object> param){
        int totalRows = groupLessonMapper.getTotalRows(param);
        int page = (int) param.get("page");

        Pagination pagination = new Pagination(page, totalRows);
        int begin = pagination.getBegin();
        int end = pagination.getEnd();
        param.put("begin", begin);
        param.put("end", end);

        List<Lesson> lessons = groupLessonMapper.getAllLessons(param);

        return Map.of("lessons", lessons, "pagination", pagination);
    }
}
