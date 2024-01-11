package com.simbest.metadata.service;

import com.simbest.metadata.model.DnaDbMap;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 09:39
 **/
public interface DbMapCacheService {
    DnaDbMap getDnaDbMap(String dbMapCode);
}
