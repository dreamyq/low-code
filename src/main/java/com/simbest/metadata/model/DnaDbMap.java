package com.simbest.metadata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbest.metadata.constants.CellFixedName;
import com.simbest.metadata.constants.KeyTypeConst;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author yanqi
 * @description
 * @date 2024/01/11 10:30
 **/
@Data
public class DnaDbMap {
    private String code;
    private String cellTableName;
    private String vaTableName;
    private Set<String> cellVdNames = new HashSet<>();
    private Map<String, String> dbVdNameMap = new HashMap<>();
    private Set<String> vaVdNames = new HashSet<>();
    private boolean persistRemain = false;
    private String keyVdNameType = KeyTypeConst.ID;
    private String dnaMapType;
    private boolean requireRootId = false;
    @JsonIgnore
    private LocalDateTime lastTime = LocalDateTime.now();
    private Map<String, FixedVd> fixedVds = new HashMap<>();
    private String selectSql;
    private String selectSqlByKey;
    private String selectSqlByParentKey;
    private String updateSql;
    private String updateSqlByKey;
    private String insertSql;

    //    private String keyVdName;
    private String keyVdName = "id";

    private DnaDbMap(String code, String cellTableName, String vaTableName, String[] cellVdNames,
                     String[] cellDbVdNames, String[] vaVdNames, boolean persistRemain) {
        this.code = code;
        this.cellTableName = cellTableName;
        this.persistRemain = persistRemain;
        this.vaTableName = vaTableName;
        if (cellVdNames != null) {
            Collections.addAll(this.cellVdNames, cellVdNames);
        }
        if (vaVdNames != null) {
            Collections.addAll(this.vaVdNames, vaVdNames);
        }
        // TODO cellDbVdNames
        // for(int i = 0; i < cellVdNames.length; i++){
        // this.dbVdNameMap.put(key, value)
        // }
    }

    public static DnaDbMapBuilder createBuilder(String code, String cellTableName, String vaTableName,
                                                String cellVdNames[], String cellDbVdNames[], String vaVdNames[], boolean persistRemain) {
        DnaDbMap dbMap = new DnaDbMap(code, cellTableName, vaTableName, cellVdNames, cellDbVdNames, vaVdNames,
                persistRemain);
        DnaDbMapBuilder builder = new DnaDbMapBuilder(dbMap);

        return builder;
    }

    public void generateSql() {
        // TODO return null
        this.fixedVds = CellFixedName.getFixedVds(this.dnaMapType, this.requireRootId);
        for (FixedVd vd : fixedVds.values()) {
            if (!this.cellVdNames.contains(vd.getVdName())) {
                this.cellVdNames.add(vd.getVdName());
                this.getDbVdNameMap().put(vd.getVdName(), vd.getDbVdName());
            }
        }

        this.selectSql = generateSelectSql();
        this.selectSqlByKey = generateSelectSqlByKey();
        this.selectSqlByParentKey = generateSelectSqlByParentKey();
        this.updateSql = generateUpdateSql();
        this.updateSqlByKey = generateUpdateSqlByKey();
        this.insertSql = generateInsertSql();
    }

    private String generateInsertSql() {
        StringBuffer sb = new StringBuffer();
        for (String vdName : this.cellVdNames) {
            if (sb.length() == 0) {
                sb.append("insert into " + this.cellTableName);
                sb.append(" ( " + vdName);
            } else {
                sb.append(" , " + vdName);
            }
        }
        boolean insertFirst = true;
        for (String vdName : this.cellVdNames) {
            if (insertFirst) {
                sb.append(" ) values (:" + vdName);
                insertFirst = false;
            } else {
                sb.append(", :" + vdName);
            }
        }

        sb.append(")");
        return sb.toString();
    }

    private String generateUpdateSqlByKey() {
        String keyName = CellKeyType.getKeyInf(this.keyVdNameType).getKeyVdName();
        return this.updateSql + " where " + this.cellTableName + "." + keyName + "=:" + keyName;
    }

    private String generateUpdateSql() {
        StringBuffer sb = new StringBuffer();
        for (String vdName : this.cellVdNames) {
            if (this.getKeyVdName().equals(vdName)) {
                continue;
            }

            if (sb.length() == 0) {
                sb.append("update " + this.cellTableName);
                sb.append(" set " + vdName + "=:" + vdName);
            } else {
                sb.append(" set " + vdName + "=:" + vdName);
            }
        }
        return sb.toString();
    }

    private String generateSelectSqlByParentKey() {
        String keyName = CellKeyType.getKeyInf(this.keyVdNameType).getParentKeyVdName();
        return this.selectSql + " where " + this.cellTableName + "."
                // TODO + this.getDbVdNameMap() .get(keyName) + "=:" + keyName;
                + keyName + "=:" + keyName;
    }

    private String generateSelectSqlByKey() {
        String keyName = CellKeyType.getKeyInf(this.keyVdNameType).getKeyVdName();
        return this.selectSql + " where " + this.cellTableName + "."
                // TODO + this.getDbVdNameMap() .get(keyName) + "=:" + keyName;
                + keyName + "=:" + keyName;
    }

    private String generateSelectSql() {
        StringBuffer sb = new StringBuffer();
        for (String cellVdName : this.cellVdNames) {
            if (sb.length() == 0) {
                sb.append("select ");
                // TODO sb.append(this.getDbVdNameMap().get(cellVdName) + " as " + cellVdName);
                sb.append(cellVdName);
            } else {
                sb.append(",");
                // TODO sb.append(this.getDbVdNameMap().get(cellVdName) + " as " + cellVdName);
                sb.append(cellVdName);
            }
        }
        sb.append(" from ");
        sb.append(cellTableName);
        return sb.toString();
    }

}
