package com.simbest.metadata.service.impl;

import com.simbest.metadata.constants.CodeDefConst;
import com.simbest.metadata.model.Cell;
import com.simbest.metadata.model.Inst;
import com.simbest.metadata.service.InstService;
import com.simbest.metadata.util.DnaTool;
import org.springframework.stereotype.Service;

/**
 * @author yanqi
 * @description
 * @date 2024/01/08 10:36
 **/
@Service
public class InstServiceImpl implements InstService {

    @Override
    public Inst initInst(String businessType, String dnaCode) {
        Cell cell = DnaTool.dna2Cell(CodeDefConst.BUSINESS_TYPE_PARTY, null);

        return null;
    }
}
