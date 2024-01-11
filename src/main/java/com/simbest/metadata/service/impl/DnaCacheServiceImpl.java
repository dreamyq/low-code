package com.simbest.metadata.service.impl;

import com.simbest.metadata.model.Dna;
import com.simbest.metadata.model.DnaCacheKey;
import com.simbest.metadata.service.DnaCacheService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanqi
 * @description
 * @date 2024/01/10 18:51
 **/
@Service
public class DnaCacheServiceImpl implements DnaCacheService {
    private ConcurrentHashMap<DnaCacheKey, Dna> dnaMap = new ConcurrentHashMap<>(512);
    private ConcurrentHashMap<DnaCacheKey, Dna> childDnaMap = new ConcurrentHashMap<>(512);
    private ConcurrentHashMap<DnaCacheKey, InstanceDnaCache> otherDnaCacheServiceMap = new ConcurrentHashMap<>(512);

    @Override
    public Dna getDna(String businessType, String dnaCode) {
        if (businessType == null || dnaCode == null) {
            throw new RuntimeException("参数不能为空！dna,businessType:" + businessType + "dnaCode:" + dnaCode);
        }
        Dna dna = dnaMap.get(new DnaCacheKey(businessType, dnaCode));
        if (dna != null) {
            return dna;
        }
        dna = childDnaMap.get(new DnaCacheKey(businessType, dnaCode));
        if (dna != null) {
            return dna;
        }
        for (InstanceDnaCache dnaCacheService : otherDnaCacheServiceMap.values()) {
            dna = dnaCacheService.getDna(businessType, dnaCode);
            if (dna != null) {
                return dna;
            }
        }
        throw new RuntimeException("Dna not exists!,businessType:" + businessType + "dnaCode:" + dnaCode);
    }

}
