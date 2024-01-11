package com.simbest.metadata.model;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 10:32
 **/

public class DnaDbMapBuilder {
    DnaDbMap dnaDbMap;

    public DnaDbMapBuilder(DnaDbMap dbMap) {
        this.dnaDbMap = dbMap;
    }

    public DnaDbMapBuilder setRequireRootId(boolean requireRootId) {
        dnaDbMap.setRequireRootId(requireRootId);
        return this;
    }

    public DnaDbMapBuilder setKeyVdNameType(String keyVdNameType) {
        dnaDbMap.setKeyVdNameType(keyVdNameType);
        return this;
    }

    public DnaDbMapBuilder setDnaMapType(String dnaDbMapType) {
        dnaDbMap.setDnaMapType(dnaDbMapType);
        return this;
    }

    public DnaDbMapBuilder setCellTablename(String cellTableName) {
        dnaDbMap.setCellTableName(cellTableName);
        return this;
    }

    public DnaDbMap getDnaDbMap() {
        dnaDbMap.generateSql();
        return dnaDbMap;
    }

}

