package com.xyc.hasee.qrcode;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hasee on 2016/9/4.
 */
public class UrlUtils {
    public static final String img_header="http://www.dkmall.com/";
    public static final  String net_url="http://ewm.dkmall.com/index.php?app=qrcode&act=do_it&data=";
    public static final  String net_url_type="&border_style=1&size=250&level=L&er_type=url";
    public static boolean isUrl(String str)
    {
        boolean result = false;
        try
        {
            new URL(str);
            result = true;
        }
        catch (MalformedURLException e) {

        }
        return result;
    }
    //http://ewm.dkmall.com/index.php?app=qrcode&act=do_it&data=http%3A%2F%2Fwww.hao123.com&er_type=url
}
