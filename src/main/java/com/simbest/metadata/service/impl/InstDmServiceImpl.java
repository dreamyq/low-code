package com.simbest.metadata.service.impl;

import com.simbest.metadata.constants.OperationFlag;
import com.simbest.metadata.dao.InstDAO;
import com.simbest.metadata.model.Cell;
import com.simbest.metadata.model.Dna;
import com.simbest.metadata.model.DnaDbMap;
import com.simbest.metadata.model.Inst;
import com.simbest.metadata.service.DbMapCacheService;
import com.simbest.metadata.service.InstDmService;
import org.springframework.stereotype.Service;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 09:38
 **/
@Service
public class InstDmServiceImpl implements InstDmService {

    private DbMapCacheService dbMapCacheService;
    private InstDAO instDAO;

    /**
     * TODO save to db
     *
     * @param inst
     * @return
     */
    @Override
    public Inst saveInst(Inst inst) {
        if (inst == null || inst.getCells().isEmpty()) {
            return inst;
        }

        if (inst.getDna() == null) {
            throw new RuntimeException("Inst 不存在Dna对象");
        }

        Dna dna = inst.getDna();
        DnaDbMap dnaDbMap = dbMapCacheService.getDnaDbMap(dna.getDbMapCode());

        for (int i = inst.getCells().size() - 1; i >= 0; i--) {
            Cell cell = inst.getCells().get(i);
            if (cell.getOperationFlag().equals(OperationFlag.OPERATION_FLAG_DELETE)) {
                this.instDAO.deleteCell(cell);
                inst.getCells().remove(i);
            }
        }

        for (Cell cell : inst.getCells()) {
            if (cell.getOperationFlag().equals(OperationFlag.OPERATION_FLAG_NEW)) {
                this.instDAO.insertCell(cell, dnaDbMap);
            } else if (cell.getOperationFlag().equals(OperationFlag.OPERATION_FLAG_UPDATE)) {
                this.instDAO.updateCell(cell, dnaDbMap);
            }

            for (String childName : cell.getChildren().keySet()) {
                Inst childInst = cell.getChildInst(childName);
                this.saveInst(childInst);
            }

            cell.setOperationFlag(OperationFlag.OPERATION_FLAG_UNCHANGED);
        }

        return inst;
    }

    @Override
    public Inst getInst(Dna dna, Object cellKey) {
        Inst inst = this.instDAO.getInst(dna, cellKey);
        getChildInst(inst);

        return inst;
    }

    @Override
    public void getChildInst(Inst inst) {
        Dna dna = inst.getDna();
        DnaDbMap dnaDbMap = dbMapCacheService.getDnaDbMap(dna.getDbMapCode());
        for (Cell cell : inst.getCells()) {
            Object cellKey = KeyValueOperateFactory.getKey(dnaDbMap, cell);
            for (Dna childDna : dna.getChildren()) {
                Inst childInst = this.instDAO.getInstByParentKey(childDna, cellKey);
                cell.addChildInst(childDna.getDnaName(), childDna.getDnaCode(), childInst);
                getChildInst(cell.getChildInst(childDna.getDnaName()));
            }
            if (dna.isCursive()) {
                Dna childDna = dna;
                Inst childInst = this.instDAO.getInstByParentKey(childDna, cellKey);
                cell.addChildInst(childDna.getDnaName(), childDna.getDnaCode(), childInst);
                getChildInst(cell.getChildInst(childDna.getDnaName()));
            }
        }
    }

    @Override
    public void deleteInst(Dna dna, Object cellKey) {
        this.instDAO.deleteCellByKey(cellKey, dna);
    }
}