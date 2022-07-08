package com.example.cubwork;

import com.example.cubwork.entity.CoinSetting;
import com.google.gson.GsonBuilder;
import org.h2.util.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
@WebAppConfiguration
public class CoinTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mvc; // 創建MockMvc類的物件

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    /**
     * 測試呼叫查詢幣別對應表資料 API,並顯示其內容。
     */
    @Test
    public void test_GetCoinSettingByCode() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/coindesk/getCoinSetting").headers(httpHeaders)
                .content("");

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        System.out.println("data"+result.getResponse().getContentAsString());
        int status = result.getResponse().getStatus();
        System.out.println("status="+status);
    }

    /**
     * 測試呼叫新增幣別對應表資料 API。
     */
    @Test
    public void test_CeateCoinSetting() throws Exception{
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        JSONObject request = new JSONObject().put("updateTime",strDate).put("code", "IDR").put("chineseName", "印尼盾")
                .put("rate", "123");
        RequestBuilder requestBuilder =MockMvcRequestBuilders.put("/coindesk/createCoinSetting").headers(httpHeaders)
                        .content(request.toString());
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        System.out.println("data"+result.getResponse().getContentAsString());

        int status = result.getResponse().getStatus();
        System.out.println("status"+status);
    }

    /**
     * 測試呼叫更新幣別對應表資料 API,並顯示其內容。
     */
    @Test
    public void test_updateCoinSetting() throws Exception{
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        JSONObject request = new JSONObject().put("updateTime","1990-01-01 00:00:00").put("code", "EUR").put("rate", "999").put("chineseName", "歐元變更");
        RequestBuilder requestBuilder =MockMvcRequestBuilders.put("/coindesk/createCoinSetting").headers(httpHeaders)
                .content(request.toString());
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        System.out.println("data"+result.getResponse().getContentAsString());

        int status = result.getResponse().getStatus();
        System.out.println("status"+status);
    }
    /**
     * 測試呼叫刪除幣別對應表資料 API。
     */
    @Test
    public void test_delCoinSetting() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        JSONObject request = new JSONObject();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/coindesk/delCoinSetting/" + "IDR").headers(httpHeaders)
                .content(request.toString());
        MvcResult result = mvc.perform(requestBuilder).andReturn();

        int status = result.getResponse().getStatus();
        System.out.println("status="+status);
    }
    /**
     * 測試呼叫 coindesk API,並顯示其內容。
     * @throws Exception
     */
    @Test
    public void test_GetApi() throws Exception{
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/coindesk/getcoindesk").accept(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("data="+result.getResponse().getContentAsString());

    }
    /**
     * 測試呼叫資料轉換的 API,並顯示其內容。
     */
    @Test
    public void test_GetETLApi() throws Exception{
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/coindesk/getcoindesk").accept(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("data="+result.getResponse().getContentAsString());

    }
}
