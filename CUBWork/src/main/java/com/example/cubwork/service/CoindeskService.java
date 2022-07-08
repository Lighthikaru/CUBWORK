package com.example.cubwork.service;

import com.example.cubwork.coindeskVo.ApiReturn;
import com.example.cubwork.coindeskVo.Coindesk;
import com.example.cubwork.coindeskVo.CoindeskBpi;
import com.example.cubwork.entity.CoinSetting;
import com.example.cubwork.repository.CoindeskRepository;
import com.example.cubwork.util.HttpUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoindeskService {

    @Autowired
    CoindeskRepository coindeskRepository;

    public List<ApiReturn> getCoindeskOrginal() throws Exception {
        String jsonString = HttpUtil.getUrlData("https://api.coindesk.com/v1/bpi/currentprice.json", "UTF8");

        Coindesk data = new Gson().fromJson(jsonString, Coindesk.class);

        List<ApiReturn> returnData=new ArrayList<>();
        for (CoindeskBpi each :data.getBpi().values()) {
            Optional<?> result = Optional.empty();
            ApiReturn eachdata=new ApiReturn();
            eachdata.setUpdateTime(data.getTime().getUpdatedISO());
            eachdata.setCurrency(each.getCode());
            //因環境問題 使用findByCode會查無資料
            List<CoinSetting> coinList=coindeskRepository.findCoinSettingsByCodeContains(each.getCode());
            eachdata.setChineseName(coinList.get(0).getChineseName().replace(" ",""));
            eachdata.setRate(each.getRate());
            returnData.add(eachdata);
        }
        return returnData;
    }
    public List<CoinSetting> findAll() throws Exception {
        return coindeskRepository.findAll();
    }

    public void createCoinSetting(CoinSetting coinSetting) {
         coindeskRepository.saveAndFlush(coinSetting);
    }
    public void delCoinSetting(String code) {
        coindeskRepository.deleteById(code);
    }

    public void updateCoinSetting(CoinSetting coinSetting) {
        coindeskRepository.save(coinSetting);
    }
    public Optional<CoinSetting> getCoinSettingByCode(String code) {
        return coindeskRepository.findById(code);
    }
}
