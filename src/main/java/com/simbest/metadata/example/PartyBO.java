package com.simbest.metadata.example;


import com.simbest.metadata.constants.CodeDefConst;
import com.simbest.metadata.constants.DataType;
import com.simbest.metadata.model.Dna;
import com.simbest.metadata.model.Vd;

import java.util.Date;

/**
 * @author yanqi
 */
public class PartyBO {

    public static Dna getPartyDna(){
        Dna partyDna = new Dna(CodeDefConst.BUSINESS_TYPE_PARTY, CodeDefConst.DNA_CODE_PARTY, CodeDefConst.DNA_NAME_PARTY, "party结构Dna");
        partyDna.setCategory(CodeDefConst.CATEGORY_PARTY);
        partyDna.setDbMapCode(CodeDefConst.DNA_DB_MAP_CODE_PARTY);
        partyDna.setCursive(false);
        partyDna.setLastTime(new Date());
        partyDna.addVd(new Vd("partyCode","party 代码", DataType.STRING));
        partyDna.addVd(new Vd("partyName","party 名称", DataType.STRING));
        partyDna.addVd(new Vd("birthday","出生日期", DataType.STRING));
        partyDna.addVd(new Vd("gender","性别", DataType.STRING));
        return partyDna;
    }
}
