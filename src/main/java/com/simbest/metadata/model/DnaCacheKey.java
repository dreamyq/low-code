package com.simbest.metadata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanqi
 * @description
 * @date 2024/01/10 19:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaCacheKey {
    private String businessType;
    private String dnaCode;
}
