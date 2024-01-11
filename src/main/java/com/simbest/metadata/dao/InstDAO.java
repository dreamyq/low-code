package com.simbest.metadata.dao;

import com.simbest.metadata.model.Cell;
import com.simbest.metadata.model.Dna;
import com.simbest.metadata.model.DnaDbMap;
import com.simbest.metadata.model.Inst;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 10:35
 **/
public interface InstDAO {
    void deleteCell(Cell cell);

    void insertCell(Cell cell, DnaDbMap dnaDbMap);

    void updateCell(Cell cell, DnaDbMap dnaDbMap);

    Inst getInst(Dna dna, Object cellKey);

    Inst getInstByParentKey(Dna childDna, Object cellKey);

    void deleteCellByKey(Object cellKey, Dna dna);
}
