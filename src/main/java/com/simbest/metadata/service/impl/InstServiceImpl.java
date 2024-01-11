package com.simbest.metadata.service.impl;

import com.simbest.metadata.constants.CodeDefConst;
import com.simbest.metadata.model.Cell;
import com.simbest.metadata.model.Dna;
import com.simbest.metadata.model.Inst;
import com.simbest.metadata.model.InstVisitor;
import com.simbest.metadata.service.DnaCacheService;
import com.simbest.metadata.service.InstDmService;
import com.simbest.metadata.service.InstService;
import com.simbest.metadata.util.DnaTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanqi
 * @description
 * @date 2024/01/08 10:36
 **/
@Service
public class InstServiceImpl implements InstService {
    @Autowired
    private DnaCacheService dnaCacheService;
    @Autowired
    private InstDmService instDmService;

    @Override
    public Inst initInst(String businessType, String dnaCode) {
        Dna dna = dnaCacheService.getDna(businessType, dnaCode);
        Inst inst = new Inst(CodeDefConst.INST_TYPE_DEFAULT, dna, null);
        inst.setDna(dna);
        Cell cell = DnaTool.dna2Cell(CodeDefConst.INST_TYPE_DEFAULT, dna);
        inst.addCell(cell);
        return inst;
    }

    @Override
    public Inst saveInst(Inst inst) {
        InstVisitor.visitInst(inst, (Inst localInst) -> {
            if (localInst.getDna() == null) {
                Dna dna = dnaCacheService.getDna(localInst.getBusinessType(), localInst.getDnaCode());
                localInst.setDna(dna);
            }
        });
        return instDmService.saveInst(inst);
    }

    @Override
    public Inst getInst(String businessType, String dnaCode) {
        return null;
    }

    @Override
    public void deleteInst(String businessType, String dnaCode) {

    }
}
