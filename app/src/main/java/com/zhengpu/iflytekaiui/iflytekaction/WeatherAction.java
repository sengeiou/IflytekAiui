package com.zhengpu.iflytekaiui.iflytekaction;

import com.zhengpu.iflytekaiui.iflytekbean.WeatherBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class WeatherAction {


    private String service;
    private  String request;
    private WeatherBean weatherBean;

    public WeatherAction(String service, WeatherBean weatherBean ,String request) {
        this.service = service;
        this.request = request;
        this.weatherBean = weatherBean;
    }

    public void start() {

        StringBuilder stringBuffer = new StringBuilder();
                        String humidity = weatherBean.getData().getResult().get(0).getHumidity();  //湿度
                        String tempRange = weatherBean.getData().getResult().get(0).getTempRange();   // 温度范围
                        String weather = weatherBean.getData().getResult().get(0).getWeather(); //天气情况
                        String wind = weatherBean.getData().getResult().get(0).getWind();
                        String prompt = weatherBean.getData().getResult().get(0).getExp().getCt().getPrompt();

                        String airQuality = weatherBean.getData().getResult().get(0).getAirQuality();

                        stringBuffer.append("空气质量为").append(airQuality)
                                .append("湿度为").append(humidity).append("温度范围为").append(tempRange)
                                .append("天气情况为").append(weather).append("风向以及风力情况为").append(wind)
                                .append("穿衣指数为").append(prompt);

        SpeechRecognizerService.startSpeech(service, stringBuffer.toString(),request);
    }
}