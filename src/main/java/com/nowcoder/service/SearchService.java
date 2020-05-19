package com.nowcoder.service;

import com.nowcoder.model.Question;
import com.nowcoder.repository.QuestionRepository;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private QuestionRepository questionRepository;

    public void save(List<Question> questions){
        questionRepository.save(questions);
    }

    public List<Question> search(String keywords,int offset,int limit){
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(keywords);
        Pageable pageable = new PageRequest(offset,limit);
        Page<Question> page = questionRepository.search(builder,pageable);
        return page.getContent();
    }


}
