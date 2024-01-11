package com.simbest.metadata.service;

import com.simbest.metadata.model.Dna;

/**
 * @author yanqi
 * @description
 * @date 2024/01/10 17:36
 **/
public interface DnaCacheService {
    Dna getDna(String businessType, String dnaCode);
}
