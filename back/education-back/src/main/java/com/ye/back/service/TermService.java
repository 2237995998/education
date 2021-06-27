package com.ye.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ye.back.entity.Role;
import com.ye.back.entity.Term;

import java.util.List;
import java.util.Set;

public interface TermService extends IService<Term> {

    List<Term> getAllTerm();


    void setThisTerm(Integer termId);

}
