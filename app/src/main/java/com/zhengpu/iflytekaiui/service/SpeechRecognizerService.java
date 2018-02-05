package com.zhengpu.iflytekaiui.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.SerialPort.OpenSerialPortListener;
import com.zhengpu.iflytekaiui.SerialPort.SerialUtils;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.iflytekaction.ReceivedSerialPortDataAction;
import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.HotspotRequest;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.WifiData;
import com.zhengpu.iflytekaiui.iflytekutils.IGetVoiceToWord;
import com.zhengpu.iflytekaiui.iflytekutils.IGetWordToVoice;
import com.zhengpu.iflytekaiui.iflytekutils.IflytekWakeUp;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.iflytekutils.MyWakeuperListener;
import com.zhengpu.iflytekaiui.iflytekutils.VoiceToWords;
import com.zhengpu.iflytekaiui.iflytekutils.WakeUpListener;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.ipc.entity.RequestMessage;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayThread;
import com.zhengpu.iflytekaiui.utils.HotsposListener;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.SpeechDialog;
import com.zhengpu.iflytekaiui.utils.UDPReceiveListenter;
import com.zhengpu.iflytekaiui.utils.UDPReceiveUtils;
import com.zhengpu.iflytekaiui.utils.UDPSendUtils;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAccessibilitySettingsOn;


/**
 * ....
 * Created by wengmf on 2017/11/21
 */

public class SpeechRecognizerService extends Service implements IGetVoiceToWord, WakeUpListener, IGetWordToVoice, KuGuoMuiscPlayListener,
        OpenSerialPortListener, HotsposListener, UDPReceiveListenter {

    private IflytekWakeUp iflytekWakeUp;
    private static VoiceToWords voiceToWords;
    private static WordsToVoice wordsToVoice;
    private static SerialUtils serialUtils;
    private KuGuoMuiscPlayThread kuGuoMuiscPlayThread;
    private String message;
    private String SpeechType = "0";
    private SpeechDialog speechDialog;
    private boolean isConnect = true;
    private static boolean FaceServiceState = false;


    @Override
    public void onCreate() {
        super.onCreate();

        if (!isAccessibilitySettingsOn(this)) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        HermesEventBus.getDefault().register(SpeechRecognizerService.this);
//    Utils.init(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a71aaeb," + SpeechConstant.FORCE_LOGIN + "=true");// 传递科大讯飞appid
//     PreferUtil.getInstance().init(this);

        //初始化讯飞语音识别
        voiceToWords = VoiceToWords.getInstance(this);
        voiceToWords.setmIGetVoiceToWord(this);
        voiceToWords.setkuGuoMuiscPlayListener(this);
        wordsToVoice = WordsToVoice.getInstance(this);
        wordsToVoice.setiGetWordToVoice(this);

        iflytekWakeUp = new IflytekWakeUp(this, new MyWakeuperListener(this, this));
        iflytekWakeUp.startWakeuper();
        kuGuoMuiscPlayThread = KuGuoMuiscPlayThread.getInstance(this);
        startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));

        //初始化串口
        serialUtils = SerialUtils.getInstance(this);
        serialUtils.setSerialPortListener(this);
        speechDialog = new SpeechDialog(this, this.getResources().getString(R.string.no_network_text));

        //  创建热点
//        HotspotUtils hotspotUtils = HotspotUtils.getHotspotUtils(this);
//        hotspotUtils.setHotsposListener(this);

//        byte[] byteAutoReset = new byte[]{0x5A,0x50,0x03,0x02,0x02,0x01,0x05,0x0D,0x0A}; //复位
//        sendSerialMessageBytes(byteAutoReset);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //获取其他进程的消息 让机器人播报其他进程消息内容
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getChildAppEvent(RequestMessage requestMessage) {

        String reqService = requestMessage.getService();
        String reqMessage = requestMessage.getMessage();
        if (reqService.equals("PlayUrl")) {
            voiceToWords.mIatDestroy();
            KuGuoMuiscPlayThread.getInstance(this).playUrl(reqMessage);
        } else if (reqService.equals("SpeechStart")) {
            if (reqMessage.equals("1")) {
                voiceToWords.startRecognizer();
                startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));
            }
        } else if (reqService.equals("AAAAA")) {

            startSpeech("AAAAA", reqMessage, reqMessage);
        } else if (reqService.equals("state_start")) {
            FaceServiceState = true;
        } else if (reqService.equals("state_end")) {
            FaceServiceState = false;
        } else {
            startSpeech(reqService, reqMessage, reqMessage);
        }
    }

    /**
     * 语音转文本回调
     */

    @Override
    public void getResult(String service, BaseBean result) {

    }

    /**
     * 用户说话声音太小回调
     */

    @Override
    public void showLowVoice(String result) {

        Long showLowVoiceTime = PreferUtil.getInstance().getShowLowVoiceTime();
        int showLowVoiceCount = PreferUtil.getInstance().getShowLowVoiceCount();

        if (TimeUtils.getTimeSpanByNow(showLowVoiceTime, ConstUtils.TimeUnit.MIN) < 2) {
            if (showLowVoiceCount == 4) {
                showLowVoiceCount = 0;
                startSpeech(AppController.SHOWLOWVOICE_TEXT, getResources().getString(R.string.showLowVoice_text), getResources().getString(R.string.showLowVoice_text));
            } else {
                voiceToWords.startRecognizer();
                showLowVoiceCount++;
                PreferUtil.getInstance().setShowLowVoiceCount(showLowVoiceCount);
            }
        } else {
            voiceToWords.startRecognizer();
            PreferUtil.getInstance().setShowLowVoiceTime(TimeUtils.getNowTimeMills());
            PreferUtil.getInstance().setShowLowVoiceCount(1);
        }
    }

    /**
     * 用户说话结束回调
     */
    @Override
    public void SpeechOver() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(AppController.SPEECH_OVER);
        sendMessage.setMessage(getResources().getString(R.string.user_Speech_Over));
        HermesEventBus.getDefault().post(sendMessage);
    }

    /**
     * 用户开始说话回调
     */
    @Override
    public void SpeechStart() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(AppController.SPEECH_START);
        sendMessage.setMessage(getResources().getString(R.string.user_Speech_Start));
        HermesEventBus.getDefault().post(sendMessage);
    }

    /**
     * 用户说话错误回调
     */
    @Override
    public void SpeechError(String error) {

    }

    /**
     * 机器人唤醒成功
     */

    @Override
    public void OnWakeUpSuccess() {
        if (kuGuoMuiscPlayThread.isPlay())
            kuGuoMuiscPlayThread.pause();
        startSpeech(AppController.WAKEUP_TEXT, getResources().getString(R.string.wakeup_text), getResources().getString(R.string.wakeup_text));
    }

    /**
     * 机器人唤醒失败
     */
    @Override
    public void OnWakeUpError() {

    }


    /**
     * 启动人脸识别服务
     */
    public static void stratFaceservice(Context context) {
        if (!FaceServiceState) {
//            Intent intent = new Intent();
//            ComponentName componentName = new ComponentName("com.zeunpro.login", "com.zeunpro.login.FaceRecognitionService");
//            intent.setComponent(componentName);
//            context.startService(intent);
            try {
                Log.e("TAG","FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");


                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.zeunpro.login", "com.zeunpro.login.FaceRecognitionActivity");
                intent.setComponent(componentName);
                context.startActivity(intent);

                Log.e("TAG","GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
            } catch (Exception e) {
                e.toString();
            }
        }
    }

    /***
     *
     * 机器人语音播报结束回调
     *
     */

    @Override
    public void SpeechEnd(String service) {
        switch (service) {
            case AppController.SHOWLOWVOICE_TEXT:
                voiceToWords.mIatDestroy();
                break;
            case AppController.JOKE:
                voiceToWords.startRecognizer();
                break;
            case AppController.MUSICX:
                voiceToWords.mIatDestroy();
//             KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
            case AppController.NEWS:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
            case AppController.STORY:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayStoryUrl());
                break;
            case AppController.VIDEO:
                voiceToWords.mIatDestroy();
                break;
            default:
                voiceToWords.startRecognizer();
                break;
        }
    }

    /***
     *
     * 机器人语音播报错误回调
     */
    @Override
    public void SpeechError(SpeechError error) {
        int code = error.getErrorCode();
        if (code == 20001) {
            if (speechDialog != null && !speechDialog.isShowing()) {
                speechDialog.show();
            }
        }
    }

    /***
     * 设置机器人语音播报内容
     * @param service    语义场景
     * @param text         播报内容
     * @param request    发送给其他线程的内容
     */

    public static void startSpeech(String service, String text, String request) {

        voiceToWords.mIatDestroy();
        wordsToVoice.startSynthesizer(service, text);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(service);
        sendMessage.setMessage(request);
        HermesEventBus.getDefault().post(sendMessage);

    }

    @Override
    public void KuGuoMuiscPlayPause() {
        setKuGuoMuiscPlayStart(0);
    }

    @Override
    public void KuGuoMuiscPlayReplay() {
        setKuGuoMuiscPlayStart(1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // 播放音乐暂停、继续和停止
    private void setKuGuoMuiscPlayStart(int Type) {
        if (Type == 0) {     //暂停
            if (kuGuoMuiscPlayThread.isPlay())
                kuGuoMuiscPlayThread.pause();
        } else {                 // 继续
            kuGuoMuiscPlayThread.start();
        }
    }

    /**
     * 发送串口消息
     */
    public static void sendSerialMessageBytes(byte[] bytes) {
        serialUtils.sendContentBytes(bytes);
    }

    /**
     * 串口打开成功
     */
    @Override
    public void onOPenSerialSuccess() {

    }

    /**
     * 串口打开失败
     */
    @Override
    public void onOPenSerialFail() {

    }

    /**
     * 发送串口消息成功
     */
    @Override
    public void onDataSentSuccess() {

    }

    /**
     * 发送串口消息失败
     */

    @Override
    public void onDataSentFail() {

    }

    /**
     * 接收串口消息成功
     */

    @Override
    public void onDataReceivedSuccess(byte[] bytes) {

        String value = ValueUtil.getInstance().bytesToHexStr(bytes);
        ReceivedSerialPortDataAction receivedSerialPortDataAction = new ReceivedSerialPortDataAction(bytes, value.split(" "), this);
        receivedSerialPortDataAction.start();

    }


    /***
     * 打开热点成功
     */
    @Override
    public void openHotspotSuccess() {

    }

    /***
     * 打开热点失败
     */
    @Override
    public void openHotspotError() {

    }

    /***
     * 接收到从机器人表情UDP消息
     */
    @Override
    public void UDPReceiveSuccess(String content) {
        final HotspotRequest hotspotRequest = JsonParser.parseHotspotRequest(content);
        if (hotspotRequest.getStatus() == 1) {   // 收到表情命令确认


        } else if (hotspotRequest.getMsg().equals("pad_connect_success")) {   //双方连接成功
            PreferUtil.getInstance().setEmojiConnectState(false);
        }
    }

    /***
     *   关闭热点重连Wifi 成功
     */

    @Override
    public void connectWifiSuccess() {

        PreferUtil.getInstance().setEmojiConnectState(true);

        UDPReceiveUtils udpReceiveUtils = new UDPReceiveUtils();
        udpReceiveUtils.setUdpReceiveListenter(SpeechRecognizerService.this);
        udpReceiveUtils.start();

        BaseApplication.MAIN_EXECUTOR.scheduleWithFixedDelay(SendMessage(), 1, 2, TimeUnit.SECONDS);


    }

    /***
     *   关闭热点重连Wifi 失败
     */
    @Override
    public void connectWifiError() {

    }

    private Runnable SendMessage() {
        return new Runnable() {
            @Override
            public void run() {

                if (PreferUtil.getInstance().getEmojiConnectState()) {
                    WifiData wifiInfo = new WifiData();
                    wifiInfo.setMsg("pad_connect_success");
                    wifiInfo.setPassword("ZP1");
                    wifiInfo.setSsid("zeunpro123");
                    wifiInfo.setCode(1002);

                    UDPSendUtils.getInstance().sendMessage(WifiData.toJsonStr(wifiInfo));

                }
            }
        };
    }
}
