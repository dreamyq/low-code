package com.simbest.metadata.service;

import com.simbest.metadata.model.Inst;

/**
 * @author yanqi
 * @description
 * @date 2024/01/08 10:36
 **/
public interface InstService {
    Inst initInst(String  businessType,String dnaCode);
}
