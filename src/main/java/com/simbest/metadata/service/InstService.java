package com.simbest.metadata.service;

import com.simbest.metadata.model.Inst;

/**
 * @author yanqi
 * @description
 * @date 2024/01/08 10:36
 **/
public interface InstService {
    Inst initInst(String  businessType,String dnaCode);

    Inst saveInst(Inst inst);

    Inst getInst(String businessType,String dnaCode);

    void deleteInst(String businessType,String dnaCode);


}
