package com.ye.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ye.back.entity.Course;
import com.ye.back.entity.Term;
import com.ye.back.mapper.CourseMapper;
import com.ye.back.mapper.TermMapper;
import com.ye.back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:com.ye.back.service.impl
 * @ClassName:TermServiceImpl
 * @Description:
 * @author:何进业
 * @date:2021/6/11 23:58
 */
@Service
public class TermServiceImpl extends ServiceImpl<TermMapper, Term> implements TermService {

    @Autowired
    private TermMapper termMapper;

    @Override
    public List<Term> getAllTerm() {
        return termMapper.getAllTerm();
    }

    @Override
    public void setThisTerm(Integer termId) {
        List<Term> terms = termMapper.selectList(null);
        for (Term term : terms) {
            term.setTermStatus(0);
            termMapper.updateById(term);
        }
        Term term = termMapper.selectById(termId);
        term.setTermStatus(1);
        termMapper.updateById(term);

    }
}
