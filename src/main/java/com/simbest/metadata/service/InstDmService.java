package com.simbest.metadata.service;

import com.simbest.metadata.model.Dna;
import com.simbest.metadata.model.Inst;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 09:37
 **/
public interface InstDmService {

    Inst saveInst(Inst inst);

    Inst getInst(Dna dna, Object cellKey);

    void getChildInst(Inst inst);

    void deleteInst(Dna dna, Object cellKey);
}
