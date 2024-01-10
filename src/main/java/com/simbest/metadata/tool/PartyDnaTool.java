package com.simbest.metadata.tool;

import cn.hutool.core.date.DateUtil;
import com.simbest.metadata.model.Dna;
import com.simbest.metadata.model.Vd;
import com.simbest.metadata.constants.CodeDefConst;
import com.simbest.metadata.constants.DataType;

/**
 * @author yanqi
 * @description 当事人领域模型
 * @date 2024/01/02 14:49
 **/
public class PartyDnaTool {
    public static Dna getPartyDna() {
        Dna partyDna = new Dna(CodeDefConst.BUSINESS_TYPE_PARTY, CodeDefConst.DNA_CODE_PARTY, CodeDefConst.DNA_NAME_PARTY, "party结构Dna");
        partyDna.setCategory(CodeDefConst.CATEGORY_PARTY);
        partyDna.setDbMapCode(CodeDefConst.DNA_DB_MAP_CODE_PARTY);
        partyDna.setCursive(false);
        partyDna.setLastTime(DateUtil.date());
        partyDna.addVd(new Vd("partyCode", "party 代码", DataType.STRING));
        partyDna.addVd(new Vd("partyName", "party 名称", DataType.STRING));
        partyDna.addVd(new Vd("partyType", "party 类型", DataType.STRING));
        partyDna.addVd(new Vd("partyGender", "party 性别", DataType.INTEGER));
        partyDna.addVd(new Vd("contact", "party 联系人", DataType.STRING));

        Dna partyAccountDna = getPartyAccountDna();
        partyDna.addChild(partyAccountDna);
        return partyDna;
    }

    public static Dna getPartyAccountDna() {
        Dna partyAccountDna = new Dna(CodeDefConst.BUSINESS_TYPE_PARTY_ACCOUNT, CodeDefConst.DNA_CODE_PARTY_ACCOUNT,
                CodeDefConst.DNA_NAME_PARTY_ACCOUNT, "partyAccount结构Dna");
        partyAccountDna.setCategory(CodeDefConst.CATEGORY_PARTY_ACCOUNT);
        partyAccountDna.setDbMapCode(CodeDefConst.DNA_DB_MAP_CODE_PARTY_ACCOUNT);
        partyAccountDna.setCursive(false);
        partyAccountDna.setLastTime(DateUtil.date());
        partyAccountDna.addVd(new Vd("accountAccountCode", "账户代码", DataType.STRING));
        partyAccountDna.addVd(new Vd("accountAccountName", "账户名称", DataType.STRING));
        partyAccountDna.addVd(new Vd("accountAccountType", "账户类型", DataType.STRING));
        return partyAccountDna;
    }

}