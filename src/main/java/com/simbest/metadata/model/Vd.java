package com.simbest.metadata.model;

import com.simbest.metadata.model.extension.VdExtension;
import lombok.Data;

import java.util.Date;

/**
 * 元数据模型属性结构
 * @author yanqi
 */
@Data
public class Vd implements Cloneable {
    /**
     * 属性代码
     */
    private String vdCode;

    /**
     * 属性名称
     */
    private String vdName;

    /**
     * 属性描述
     */
    private String vdDescription;

    /**
     * 序号，用于排序
     */
    private int serNo;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 主数据代码
     */
    private String mdCode;

    /**
     * 控制信息
     */
    private String vdControl = null;

    /**
     * 最后修改时间
     */
    private Date lastTime;

    /**
     * 扩展信息
     */
    private VdExtension extension = null;


    public Vd(String vdCode, String vdName, String dataType) {
        this.vdCode = vdCode;
        this.vdName = vdName;
        this.dataType = dataType;
    }

    @Override
    public Vd clone() {
        try {
            Vd clone = (Vd) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
