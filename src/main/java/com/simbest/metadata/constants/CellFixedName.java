package com.simbest.metadata.constants;

import com.simbest.metadata.model.FixedVd;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 10:32
 **/
public class CellFixedName {
    public static final String ID = "id";
    public static final String DEF_DNA_CODE = "dnaCode";

    public static Map<String, FixedVd> getFixedVds(String dnaMapType, boolean requireRootId) {

        Map<String, FixedVd> results = new HashMap<>();
        return results;
        // TODO return null;
    }
}
