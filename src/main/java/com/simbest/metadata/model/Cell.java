package com.simbest.metadata.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanqi
 * @description
 * @date 2024/01/08 10:23
 **/
@Data
public class Cell {
    private Long id;
    private Long rootId;

    private Long parentId;
    /**
     * 表示操作标记，有新增、修改、删除和不变化四个可选值
     */
    private String operationFlag;

    private String dnaCode;
    /**
     * 表示实例中每个属性的值
     */
    private Map<String, Va> vas = new HashMap<>();
    /**
     * 记录每个孩子的dna对象的实例对象
     */
    private Map<String, Inst> children = new HashMap<>();

    private Inst owner;

    public void addChildInst(String dnaName,String dnaCode,Inst inst){

    }

    public void setVaByName(String code,String name){
        Va va = new Va();
        va.setCode(code);
        va.setName(name);
//        va.setDataType();
        vas.put(code,va);
    }

}
