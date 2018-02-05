package com.zhengpu.iflytekaiui.base;

import android.app.Application;

import com.blankj.utilcode.utils.ThreadPoolUtils;
import com.blankj.utilcode.utils.Utils;
import com.bugtags.library.Bugtags;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;


public class BaseApplication extends Application {



    public static BaseApplication baseApplication;
    /**
     * 主线程池
     */
//    public static Executor MAIN_EXECUTOR = Executors.newFixedThreadPool(5);

    public  static ThreadPoolUtils MAIN_EXECUTOR =   new ThreadPoolUtils(ThreadPoolUtils.Type.FixedThread,5);


    @Override
    public void onCreate() {
        super.onCreate();
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5a73f4ccf43e480e110001a0");
        Bugtags.start("a2c7a5fe7d501248b07b48400f6f5210", this, BTGInvocationEventNone);
        HermesEventBus.getDefault().init(this);
        Utils.init(this);
        PreferUtil.getInstance().init(this);

    }


    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
