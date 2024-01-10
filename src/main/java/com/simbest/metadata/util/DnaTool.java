package com.simbest.metadata.util;

import com.simbest.metadata.constants.CodeDefConst;
import com.simbest.metadata.constants.DataType;
import com.simbest.metadata.constants.OperationFlag;
import com.simbest.metadata.model.*;

/**
 * @author yanqi
 * @description
 * @date 2024/01/09 09:11
 **/
public class DnaTool {

    public static Cell dna2Cell(String instType, Dna dna) {
        Cell cell = singleDna2Cell(instType, dna);

        for (Dna childDna : dna.getChildren()) {
            Inst childInst = new Inst(instType, childDna, cell);
            cell.addChildInst(childDna.getDnaName(), childDna.getDnaCode(), childInst);

            for (int i = 0; i < childDna.getMinCount(); i++) {
                Cell childCell = dna2Cell(instType, childDna);
                childInst.addCell(childCell);
            }
        }

        if (dna.isCursive()) {
            Inst childInst = new Inst(instType, dna, cell);
            cell.addChildInst(dna.getDnaName(), dna.getDnaCode(), childInst);
        }

        return cell;
    }

    public static Cell singleDna2Cell(String instType, Dna dna) {
        if (dna == null) {
            return null;
        }

        Cell cell = null;

        if (instType.equals(CodeDefConst.INST_TYPE_DEFAULT)) {
            cell = new Cell();
        } else if (instType.equals(CodeDefConst.INST_TYPE_FILTER_RESULT)) {
            cell = new FilterResultCell(dna.getDnaCode());
        }

        cell.setOperationFlag(OperationFlag.OPERATION_FLAG_NEW);

        for (Vd vd : dna.getVds()) {
            Va va = vd2va(vd);
            cell.getVas().put(vd.getVdName(), va);
        }

        return cell;
    }

    private static Va vd2va(Vd vd) {
        if (vd == null) {
            throw new RuntimeException("属性定义vd不能为空");
        }

        Va va = new Va();
        va.setCode(vd.getVdCode());
        va.setName(vd.getVdName());
        String dataType = vd.getDataType();
        va.setDataType(dataType);
        if (dataType.equals(DataType.INTEGER)) {
            va.setValue(0);
        } else if (dataType.equals(DataType.LONG)) {
            va.setValue(0L);
        } else if (dataType.equals(DataType.FLOAT)) {
            va.setValue(0.0);
        }

        return va;
    }
}
