package com.zhengpu.iflytekaiui.iflytekutils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhengpu.iflytekaiui.iflytekbean.BaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.CalcBean;
import com.zhengpu.iflytekaiui.iflytekbean.CmdBean;
import com.zhengpu.iflytekaiui.iflytekbean.CustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.DanceBean;
import com.zhengpu.iflytekaiui.iflytekbean.DatetimeBean;
import com.zhengpu.iflytekaiui.iflytekbean.FaceserviceBean;
import com.zhengpu.iflytekaiui.iflytekbean.FlightBean;
import com.zhengpu.iflytekaiui.iflytekbean.HotelSearchBean;
import com.zhengpu.iflytekaiui.iflytekbean.IflyJokeBean;
import com.zhengpu.iflytekaiui.iflytekbean.JokeBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.NewsBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenAppBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenCameraBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenQABean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenVideoBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenimBean;
import com.zhengpu.iflytekaiui.iflytekbean.PoetryBean;
import com.zhengpu.iflytekaiui.iflytekbean.R4Bean;
import com.zhengpu.iflytekaiui.iflytekbean.RadioBean;
import com.zhengpu.iflytekaiui.iflytekbean.RobotCommandBean;
import com.zhengpu.iflytekaiui.iflytekbean.ShootBean;
import com.zhengpu.iflytekaiui.iflytekbean.StockBean;
import com.zhengpu.iflytekaiui.iflytekbean.StoryBean;
import com.zhengpu.iflytekaiui.iflytekbean.TelephoneBean;
import com.zhengpu.iflytekaiui.iflytekbean.TranslationBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoCammandBean;
import com.zhengpu.iflytekaiui.iflytekbean.WeatherBean;
import com.zhengpu.iflytekaiui.iflytekbean.WebSearchBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.CustomMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.HotspotRequest;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfCustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicResBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.TianJokeBean;

import java.lang.reflect.Type;

public class JsonParser {


    static CalcBean parseResultCalc(String json) {

        CalcBean calcBean = new CalcBean();
        try {
            Type type = new TypeToken<CalcBean>() {
            }.getType();
            calcBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calcBean;
    }


    static BaikeBean parseResultBaikeBean(String json) {
        BaikeBean baikeBean = new BaikeBean();
        try {
            Type type = new TypeToken<BaikeBean>() {
            }.getType();
            baikeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baikeBean;
    }


    static DatetimeBean parseResultDatetimeBean(String json) {
        DatetimeBean datetimeBean = new DatetimeBean();
        try {
            Type type = new TypeToken<DatetimeBean>() {
            }.getType();
            datetimeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datetimeBean;
    }


    static WeatherBean parseResultWeatherBean(String json) {
        WeatherBean weatherBean = new WeatherBean();
        try {
            Type type = new TypeToken<WeatherBean>() {
            }.getType();
            weatherBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherBean;
    }


    static OpenAppBean parseResultOpenAppBean(String json) {
        OpenAppBean openAppBean = new OpenAppBean();
        try {
            Type type = new TypeToken<OpenAppBean>() {
            }.getType();
            openAppBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openAppBean;
    }


    public static MusicXBean parseResultMusicXBean(String json) {
        MusicXBean musicXBean = new MusicXBean();
        try {
            Type type = new TypeToken<MusicXBean>() {
            }.getType();
            musicXBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicXBean;
    }


    static OpenQABean parseResultOpenQABean(String json) {
        OpenQABean openQABean = new OpenQABean();
        try {
            Type type = new TypeToken<OpenQABean>() {
            }.getType();
            openQABean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openQABean;
    }

    static NewsBean parseResultNewsBean(String json) {
        NewsBean newsBean = new NewsBean();
        try {
            Type type = new TypeToken<NewsBean>() {
            }.getType();
            newsBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsBean;
    }


    static StoryBean parseResultStoryBean(String json) {
        StoryBean storyBean = new StoryBean();
        try {
            Type type = new TypeToken<StoryBean>() {
            }.getType();
            storyBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storyBean;
    }

    static JokeBean parseResultJokeBean(String json) {
        JokeBean jokeBean = new JokeBean();
        try {
            Type type = new TypeToken<JokeBean>() {
            }.getType();
            jokeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jokeBean;
    }


    static PoetryBean parseResultPoetryBean(String json) {
        PoetryBean poetryBean = new PoetryBean();
        try {
            Type type = new TypeToken<PoetryBean>() {
            }.getType();
            poetryBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return poetryBean;
    }


    public static VideoBean parseResultVideoBean(String json) {
        VideoBean videoBean = new VideoBean();
        try {
            Type type = new TypeToken<VideoBean>() {
            }.getType();
            videoBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoBean;
    }


    static FlightBean parseResultFlightoBean(String json) {
        FlightBean flightBean = new FlightBean();
        try {
            Type type = new TypeToken<FlightBean>() {
            }.getType();
            flightBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightBean;
    }


    static R4Bean parseResultR4Bean(String json) {
        R4Bean r4Bean = new R4Bean();
        try {
            Type type = new TypeToken<R4Bean>() {
            }.getType();
            r4Bean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r4Bean;
    }

    static CustomMusicBean parseResultCustomMusicBean(String json) {
        CustomMusicBean customMusicBean = new CustomMusicBean();
        try {
            Type type = new TypeToken<CustomMusicBean>() {
            }.getType();
            customMusicBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customMusicBean;
    }


    static CmdBean parseResultCmdBean(String json) {
        CmdBean cmdBean = new CmdBean();
        try {
            Type type = new TypeToken<CmdBean>() {
            }.getType();
            cmdBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmdBean;
    }


    static CustomBaikeBean parseResultCustomBaikeBean(String json) {
        CustomBaikeBean customBaikeBean = new CustomBaikeBean();
        try {
            Type type = new TypeToken<CustomBaikeBean>() {
            }.getType();
            customBaikeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customBaikeBean;
    }


    public static IfCustomBaikeBean parseResultIfCustomBaikeBean(String json) {
        IfCustomBaikeBean ifCustomBaikeBean = new IfCustomBaikeBean();
        try {
            Type type = new TypeToken<IfCustomBaikeBean>() {
            }.getType();
            ifCustomBaikeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifCustomBaikeBean;
    }

    static IfMusicBean parseResultIfMusicBean(String json) {
        IfMusicBean ifMusicBean = new IfMusicBean();
        try {
            Type type = new TypeToken<IfMusicBean>() {
            }.getType();
            ifMusicBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifMusicBean;
    }

    static IfMusicResBean parseResultIfMusicResBean(String json) {
        IfMusicResBean ifMusicResBean = new IfMusicResBean();
        try {
            Type type = new TypeToken<IfMusicResBean>() {
            }.getType();
            ifMusicResBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifMusicResBean;
    }

    public static TianJokeBean parseResultTianJokeBean(String json) {
        TianJokeBean tianJokeBean = new TianJokeBean();
        try {
            Type type = new TypeToken<TianJokeBean>() {
            }.getType();
            tianJokeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tianJokeBean;
    }


    static RobotCommandBean parseResultRobotCommandBean(String json) {
        RobotCommandBean robotCommandBean = new RobotCommandBean();
        try {
            Type type = new TypeToken<RobotCommandBean>() {
            }.getType();
            robotCommandBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return robotCommandBean;
    }


    public static WebSearchBean parseResultWebSearchBean(String json) {
        WebSearchBean webSearchBean = new WebSearchBean();
        try {
            Type type = new TypeToken<WebSearchBean>() {
            }.getType();
            webSearchBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webSearchBean;
    }


    public static HotspotRequest parseHotspotRequest(String json) {
        HotspotRequest hotspotRequest = new HotspotRequest();
        try {
            Type type = new TypeToken<HotspotRequest>() {
            }.getType();
            hotspotRequest = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotspotRequest;
    }


    public static TelephoneBean parseResultTelephoneBean(String json) {
        TelephoneBean telephoneBean = new TelephoneBean();
        try {
            Type type = new TypeToken<TelephoneBean>() {
            }.getType();
            telephoneBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return telephoneBean;
    }


    public static FaceserviceBean parseResultFaceserviceBean(String json) {
        FaceserviceBean faceserviceBean = new FaceserviceBean();
        try {
            Type type = new TypeToken<FaceserviceBean>() {
            }.getType();
            faceserviceBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return faceserviceBean;
    }


    public static OpenCameraBean parseResultOpenCameraBean(String json) {
        OpenCameraBean openCameraBean = new OpenCameraBean();
        try {
            Type type = new TypeToken<OpenCameraBean>() {
            }.getType();
            openCameraBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openCameraBean;
    }


    public static ShootBean parseResultShootBean(String json) {
        ShootBean shootBean = new ShootBean();
        try {
            Type type = new TypeToken<ShootBean>() {
            }.getType();
            shootBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shootBean;
    }

    public static OpenVideoBean parseResultOpenVideoBean(String json) {
        OpenVideoBean openVideoBean = new OpenVideoBean();
        try {
            Type type = new TypeToken<OpenVideoBean>() {
            }.getType();
            openVideoBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openVideoBean;
    }

    public static VideoCammandBean parseResultVideoCammandBean(String json) {
        VideoCammandBean videoBean = new VideoCammandBean();
        try {
            Type type = new TypeToken<VideoCammandBean>() {
            }.getType();
            videoBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoBean;
    }


    public static IflyJokeBean parseResultIflyJokeBean(String json) {
        IflyJokeBean iflyJokeBean = new IflyJokeBean();
        try {
            Type type = new TypeToken<IflyJokeBean>() {
            }.getType();
            iflyJokeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iflyJokeBean;
    }

    public static DanceBean parseResultIflyDanceBean(String json) {
        DanceBean danceBean = new DanceBean();
        try {
            Type type = new TypeToken<DanceBean>() {
            }.getType();
            danceBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danceBean;
    }

    public static OpenimBean parseResultIflyOpenimBean(String json) {
        OpenimBean openimBean = new OpenimBean();
        try {
            Type type = new TypeToken<OpenimBean>() {
            }.getType();
            openimBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openimBean;
    }

    public static RadioBean parseResultRadioBean(String json) {
        RadioBean radioBean = new RadioBean();
        try {
            Type type = new TypeToken<RadioBean>() {
            }.getType();
            radioBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return radioBean;
    }

    public static StockBean parseResultStockBean(String json) {
        StockBean stockBean = new StockBean();
        try {
            Type type = new TypeToken<StockBean>() {
            }.getType();
            stockBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockBean;
    }

    public static TranslationBean parseResultTranslationBean(String json) {
        TranslationBean translationBean = new TranslationBean();
        try {
            Type type = new TypeToken<TranslationBean>() {
            }.getType();
            translationBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return translationBean;
    }


    public static HotelSearchBean parseResultHotelSearchBean(String json) {
        HotelSearchBean hotelSearchBean = new HotelSearchBean();
        try {
            Type type = new TypeToken<HotelSearchBean>() {
            }.getType();
            hotelSearchBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelSearchBean;
    }





}
