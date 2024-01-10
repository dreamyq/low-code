package com.simbest.metadata.controller;

import com.simbest.cloud.feign.uums.util.JsonResponse;
import com.simbest.metadata.constants.CodeDefConst;
import com.simbest.metadata.model.Cell;
import com.simbest.metadata.model.Inst;
import com.simbest.metadata.model.Va;
import com.simbest.metadata.service.InstService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanqi
 * @description
 * @date 2024/01/08 10:28
 **/
@RestController
@RequestMapping("/partyInst")
@RequiredArgsConstructor
public class PartyInstController {

    private final InstService instService;


    @RequestMapping("/init")
    public JsonResponse init() {
        Inst partyInst = instService.initInst(CodeDefConst.BUSINESS_TYPE_PARTY, CodeDefConst.DNA_CODE_PARTY);
        Cell partyCell = partyInst.getSingleCell();
        partyCell.setVaByName("partyName","张三");
        partyCell.setVaByName("certType","01");
        partyCell.setVaByName("certId","341231232323");
        partyCell.setVaByName("certMobileNo","1888888888");
        partyCell.setVaByName("contact","李四");
        Inst accountInst = instService.initInst(CodeDefConst.BUSINESS_TYPE_PARTY, CodeDefConst.DNA_CODE_PARTY);
        Cell accountCell = accountInst.getSingleCell();
        accountCell.setVaByName("accountName","张三");
        accountCell.setVaByName("accountNo","6228480402564890018");
        accountCell.setVaByName("bankName","中国银行");
        Inst anotherAccountInst = instService.initInst(CodeDefConst.BUSINESS_TYPE_PARTY, CodeDefConst.DNA_CODE_PARTY);
        Cell anotherAccountCell = anotherAccountInst.getSingleCell();
        anotherAccountCell.setVaByName("accountName","张三");
        anotherAccountCell.setVaByName("accountNo","6228480402564890018");
        anotherAccountCell.setVaByName("bankName","中国银行");
        accountInst.addCell(anotherAccountCell);
        partyCell.addChildInst(accountInst.getDnaName(), accountCell.getDnaCode(), accountInst);


        return JsonResponse.success("");
    }
}
