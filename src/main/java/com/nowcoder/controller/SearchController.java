package com.nowcoder.controller;

import com.nowcoder.model.EntityType;
import com.nowcoder.model.Question;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.FollowService;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.SearchService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping(path = {"/save"},method = {RequestMethod.GET})
    @ResponseBody
    public String save(){
        List<Question> questions = questionService.getLatestQuestions(3,0,500);
        searchService.save(questions);
        return WendaUtil.getJSONString(0);
    }

    @RequestMapping(path = {"/search"},method = {RequestMethod.GET})
    public String search(Model model, @RequestParam("q") String keyword,@RequestParam(value = "page",defaultValue = "1") int page){
        try{
            int offset = (page - 1) * 10;
            List<Question> questionList = searchService.search(keyword,offset,10);
            List<ViewObject> vos = new ArrayList<>();
            for(Question question : questionList){
                ViewObject vo = new ViewObject();
                vo.set("question",question);
                vo.set("followCount",followService.getFollowerCount(EntityType.ENTITY_QUESTION,question.getId()));
                vo.set("user",userService.getUser(question.getUserId()));
                vos.add(vo);
            }
            model.addAttribute("vos",vos);
            model.addAttribute("keyword",keyword);
        }catch (Exception e){
            logger.error("搜索失败" + e.getMessage());
        }
        return "result";
    }


}
