package com.wzhx.yilan.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Locale;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author JavoneWang
 */
public class DeviceInfo {
    /**
     * 屏幕高度
     */
    private static final String PREF_DEVICE_HEIGHT = "pref_device_height";

    /**
     * 屏幕宽度
     */
    private static final String PREF_DEVICE_WIDTH = "pref_device_width";

    /**
     * 获取手机硬件ID【IMEI（GSM手机）或者MEID、ESN（CDMA手机）】
     *
     * @param context 应用环境
     * @return IMEI（GSM）或者MEID、ESN（CDMA）
     * @throws SecurityException 没有权限 READ_PHONE_STATE时抛出此异常
     */
    public static String getIMEI(Context context) throws SecurityException {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static String getAndroidID(Context context) {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    public static String getLanguage() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage();
    }

    /**
     * 获取手机号
     *
     * @param context 应用环境
     * @return 手机号
     * @throws SecurityException 没有权限 READ_PHONE_STATE时抛出此异常
     */
    public static String getPhoneNumber(Context context) throws SecurityException {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number();
    }

    /**
     * 获取Sim卡序列号
     *
     * @param context 应用环境
     * @return sim卡序列号
     * @throws SecurityException 没有权限 READ_PHONE_STATE时抛出此异常
     */
    public static String getSimSerialNumber(Context context) throws SecurityException {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimSerialNumber();
    }

    /**
     * 获取客户ID（在gsm中是imsi号）
     *
     * @param context 应用环境
     * @return ID（imsi）
     * @throws SecurityException 没有权限 READ_PHONE_STATE时抛出此异常
     */
    public static String getSubscriberId(Context context) throws SecurityException {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSubscriberId();
    }

    /**
     * 获取用户的网络提供商
     *
     * @param context 应用环境
     * @return 网络提供商
     */
    public static String getISP(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        /*
         * 获取SIM卡的IMSI码 SIM卡唯一标识：IMSI 国际移动用户识别码（IMSI：International Mobile
         * Subscriber Identification Number）是区别移动用户的标志，
         * 储存在SIM卡中，可用于区别移动用户的有效信息。IMSI由MCC、MNC、MSIN组成，其中MCC为移动国家号码，由3位数字组成，
         * 唯一地识别移动客户所属的国家，我国为460；MNC为网络id，由2位数字组成，
         * 用于识别移动客户所归属的移动网络，中国移动为00，中国联通为01,中国电信为03；MSIN为移动客户识别码，采用等长11位数字构成。
         * 唯一地识别国内GSM移动通信网中移动客户。所以要区分是移动还是联通，只需取得SIM卡中的MNC字段即可
         */
        String imsi = telephonyManager.getSubscriberId();
        return imsi;
        // if (imsi != null) {
        // if (imsi.startsWith("46000") || imsi.startsWith("46002")) {//
        // 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
        // return ISP.移动;
        // } else if (imsi.startsWith("46001")) {
        // return ISP.联通;
        // } else if (imsi.startsWith("46003")) {
        // return ISP.电信;
        // }
        // }
        // return ISP.未知;
    }

    /**
     * 获取默认的外部存储的根路径
     *
     * @return 外部存储根路径
     */
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取屏幕信息
     *
     * @param activity Activity
     * @return 屏幕信息
     */
    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取屏幕分辨率密度（DENSITY_LOW, DENSITY_MEDIUM, or DENSITY_HIGH）
     *
     * @param activity Activity
     * @return 密度
     */
    public static int getDensity(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }

    /**
     * 获取屏幕高度(通过系统服务)
     *
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static int getDisplayHeightBySystemService(Context context) {
        int height = 0;
        try {
            WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
            height = wm.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }

    /**
     * 获取屏幕宽度(通过系统服务)
     *
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static int getDisplayWidthBySystemService(Context context) {
        int width = 0;
        try {
            WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
            width = wm.getDefaultDisplay().getWidth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return width;
    }

    /**
     * 获取CPU序列号
     *
     * @return CPU序列号(16位) 读取失败为"0000000000000000"
     */
    public static String getCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            // 读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            // 查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    // 查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        // 提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1, str.length());
                        // 去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    // 文件结尾
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }

    /**
     * 获取当前应用的版本名
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前应用的版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 测试该设备是否为使用tegra芯片的设备
     *
     * @return 返回true表示该设备使用了tegra芯片
     */
    public static Boolean isTegra() {

        Boolean isTegra = false;

        EGL10 mEGL;

        EGLDisplay mEGLDisplay;

        EGLConfig[] mEGLConfigs = new EGLConfig[1];

        EGLConfig mEGLConfig;

        EGLContext mEGLContext;

        EGLSurface mEGLSurface;

        GL10 mGL;

        int[] numConfigs = new int[1];

        int[] version = new int[2];

        int[] configAttribs = new int[]{

                EGL10.EGL_RED_SIZE, 5,

                EGL10.EGL_GREEN_SIZE, 6,

                EGL10.EGL_BLUE_SIZE, 5,

                EGL10.EGL_SURFACE_TYPE, EGL10.EGL_PBUFFER_BIT,

                EGL10.EGL_NONE

        };

        int[] bufAttribs = new int[]{

                EGL10.EGL_WIDTH, 64,

                EGL10.EGL_HEIGHT, 64,

                EGL10.EGL_NONE

        };

        boolean result = false;

        try {

            // No error checking performed, minimum required code to elucidate
            // logic

            mEGL = (EGL10) EGLContext.getEGL();

            mEGLDisplay = mEGL.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

            mEGL.eglInitialize(mEGLDisplay, version);

            result = mEGL.eglChooseConfig(mEGLDisplay, configAttribs, mEGLConfigs, 1, numConfigs);

            mEGLConfig = mEGLConfigs[0];

            mEGLConfigs = null; // since we don't need array any more.

            mEGLContext = mEGL.eglCreateContext(mEGLDisplay, mEGLConfig, EGL10.EGL_NO_CONTEXT, null);

            mEGLSurface = mEGL.eglCreatePbufferSurface(mEGLDisplay, mEGLConfig, bufAttribs);

            mEGL.eglMakeCurrent(mEGLDisplay, mEGLSurface, mEGLSurface, mEGLContext);

            mGL = (GL10) mEGLContext.getGL();

            String extensions = " " + mGL.glGetString(GL10.GL_EXTENSIONS) + " ";

            boolean hasS3TC = extensions.indexOf(" " + "GL_EXT_texture_compression_s3tc" + " ") >= 0;

            boolean isNV = mGL.glGetString(GL10.GL_VENDOR).contains("NVIDIA");

            if (!hasS3TC || !isNV)

            {

                isTegra = false;

            } else

            {

                isTegra = true;

            }

            // then clean up EGL resources.

            mGL = null; // kill reference?

            mEGL.eglMakeCurrent(mEGLDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);

            mEGL.eglDestroySurface(mEGLDisplay, mEGLSurface);

            mEGL.eglDestroyContext(mEGLDisplay, mEGLContext);

            mEGL.eglTerminate(mEGLDisplay);

            mEGL = null; // kill reference?

        } catch (Exception e) {

            // if this fails, worst case is they actually get to the market

            // and see market's error message instead of ours. not optimal but
            // better than

            // stopping a sale that could happen because our code failed

            isTegra = true;

        }

        return isTegra;

    }

    /**
     * 关闭输入法
     *
     * @param act
     * @see [类、类#方法、类#成员]
     */
    public static void hideIme(Activity act) {
        if (act == null) {
            return;
        }
        View view = act.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static enum ISP {
        未知, 移动, 联通, 电信;
    }

}
