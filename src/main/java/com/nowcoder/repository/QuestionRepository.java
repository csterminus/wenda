package com.nowcoder.repository;

import com.nowcoder.model.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


@Component
public interface QuestionRepository extends ElasticsearchRepository<Question,Integer> {
}
