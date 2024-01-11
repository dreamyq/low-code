package com.simbest.metadata.model;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 09:32
 **/
public class InstVisitor {
    public static void visitInst(Inst inst, VisitInstInf visitor) {
        if (inst == null) {
            visitor.visit(inst);
        }

        for (Cell cell : inst.getCells()) {
            for (Inst childInst : cell.getChildren().values()) {
                visitInst(childInst, visitor);
            }
        }
    }
}
