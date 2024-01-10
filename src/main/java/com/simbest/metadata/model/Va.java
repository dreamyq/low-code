package com.simbest.metadata.model;

import lombok.Data;

/**
 * @author yanqi
 * @description
 * @date 2024/01/08 10:24
 **/
@Data
public class Va {
    private Long id;
    /**
     * 对应Vd的vdCode，代表唯一的一个Vd对象
     */
    private String code;
    /**
     * 对应Vd的vdName
     */
    private String name;
    /**
     * 对应Vd的dataType
     */
    private String dataType;
    /**
     * 用于记录属性值，该值由dataType决定，例如dataType为String，则value为String类型
     */
    private Object value;
}
