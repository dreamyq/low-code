package com.simbest.metadata.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanqi
 * @description 元数据的实例，这是一个灵活的数据结构，能够表达各种领域模型的对象
 * @date 2024/01/03 10:18
 **/
@Data
public class Inst implements Cloneable {
    /**
     * 表示示例关联到的Dna对象
     */
    private String businessType;
    private Dna dna;
    /**
     * 表示示例关联到的Dna对象
     */
    private String dnaCode;
    /**
     * 冗余名字
     */
    private String dnaName;
    /**
     * 表示实例的类型
     */
    private String instType;
    //-1表示属性不可用
    private int total = -1;

    /**
     *
     */
    private Cell parentCell;

    private List<Cell> cells = new ArrayList<>();

    public Inst() {
    }


    public Inst(String instType, Dna dna, Cell parentCell) {
        this.dna = dna;
        this.instType = instType;
        this.parentCell = parentCell;
    }

    public Cell getSingleCell() {
        if (parentCell != null) {
            return parentCell;
        } else {
            Cell cell = new Cell();
            cell.setDnaCode(dnaCode);
            return cell;
        }
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    @Override
    public Inst clone() {
        try {
            Inst clone = (Inst) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
