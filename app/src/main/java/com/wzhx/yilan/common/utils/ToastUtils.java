package com.wzhx.yilan.common.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {  
	  
    private static CharSequence oldMsg;
    
    protected static Toast toast = null;
    
    private static long oneTime = 0;  
    
    private static long twoTime = 0;
    
    //android系统设定的值
    private static final int LONG_DELAY = 3500; // 3.5 seconds
    
    private static final int SHORT_DELAY = 2000; // 2 seconds
    
    public static void showToast(Context context, CharSequence s, int duration){
        if(toast == null){   
            toast = Toast.makeText(context, s, duration);
            toast.show();  
            oneTime = System.currentTimeMillis();
        }else{  
            twoTime = System.currentTimeMillis();
            if(s.equals(oldMsg)){  
                if(twoTime - oneTime > (duration == Toast.LENGTH_SHORT ? SHORT_DELAY : LONG_DELAY)) {
                    toast.show();  
                }  
            }else {  
                oldMsg = s;  
                toast.setText(s);  
                toast.show();  
            }         
        }  
        oneTime = twoTime;  
    }  
      
      
    public static void showToast(Context context, int resId, int duration){
        showToast(context, context.getString(resId), duration);  
    }  
  
}  