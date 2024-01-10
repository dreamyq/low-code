package com.simbest.metadata.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 将多个Vd对象组织在一个逻辑单元中，它属于元数据模型的定义部分
 * 该定义是一个递归树结构
 *
 * @author yanqi
 */
@Data
public class Dna {
    private long id;
    /**
     * 对dna对象进行业务分类，可以通过businessType和dnaCode唯一确定一个Dna对象
     */
    private String businessType;
    /**
     * dna的唯一编码
     */
    private String dnaCode;
    /**
     * 序号，用于排序
     */
    private int serNo;

    private Dna parent;

    /**
     * Dna所属的一级分类
     */
    private String category;

    /**
     * Dna所属的二级分类
     */
    private String secondCategory;

    /**
     * 与数据库映射相关
     */
    private String dbMapCode;

    /**
     * Dna的结构名称
     */
    private String dnaName;

    /**
     * dna的描述信息，用于描述dna的数据结构
     */
    private String dnaDescription;

    /**
     * 该数据结构的最小长度
     */
    private int minCount;

    /**
     * 该数据结构的最大长度
     */
    private int maxCount;

    /**
     * 是否是递归结构
     */
    private boolean cursive = false;

    private List<Dna> children = new ArrayList<>();

    /**
     * 用于描述Dna包含的属性列表
     */
    private List<Vd> vds = new ArrayList<>();

    private Date lastTime;//最后修改时间

    public Dna(String businessType, String dnaCode, String dnaName, String dnaDescription) {
        this.businessType = businessType;
        this.dnaCode = dnaCode;
        this.dnaName = dnaName;
        this.dnaDescription = dnaDescription;
    }

    public void addVd(Vd vd) {
        vds.add(vd);
    }

    public void addChild(Dna partyAccountDna) {
        children.add(partyAccountDna);
    }
}
