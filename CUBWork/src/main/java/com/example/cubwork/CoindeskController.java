package com.example.cubwork;

import com.example.cubwork.coindeskVo.ApiReturn;
import com.example.cubwork.entity.CoinSetting;
import com.example.cubwork.service.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author light
 */
@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    @Autowired
    CoindeskService coinDeskService;
    @RequestMapping(value = "/getcoindesk", produces = "application/json; charset=utf-8")
    public List<ApiReturn> getCoinDesk() throws Exception {
        return coinDeskService.getCoindeskOrginal();
    }

    @RequestMapping(value = "/getCoinSetting", produces = "application/json; charset=utf-8")
    public List<CoinSetting> getAllCoinSetting() throws Exception {
        return coinDeskService.findAll();
    }
    @RequestMapping(value = "/getCoinSetting/{code}", produces = "application/json; charset=utf-8")
    public Optional<CoinSetting> getCoinSettingByCode(@PathVariable String code) throws Exception {
        return coinDeskService.getCoinSettingByCode(code);
    }
    @RequestMapping(value = "/createCoinSetting",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCoinSetting(@Valid @RequestBody CoinSetting coinSetting) throws Exception {
        coinDeskService.createCoinSetting(coinSetting);
    }
    @RequestMapping(value = "/updateCoinSetting", produces = "application/json; charset=utf-8")
    public void updateCoinSetting(@PathVariable CoinSetting coinSetting) throws Exception {
        coinDeskService.updateCoinSetting(coinSetting);
    }
    @RequestMapping(value = "/delCoinSetting/{code}", produces = "application/json; charset=utf-8")
    public void delCoinSetting(@PathVariable String code) throws Exception {
        coinDeskService.delCoinSetting(code);
    }
}
