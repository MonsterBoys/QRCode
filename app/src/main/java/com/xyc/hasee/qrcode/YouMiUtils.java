package com.xyc.hasee.qrcode;

import android.content.Context;

import net.youmi.android.AdManager;
import net.youmi.android.normal.spot.SpotManager;

/**
 * Created by hasee on 2016/9/4.
 */
public class YouMiUtils {
    public static void initYoumi(Context mContext){
        AdManager.getInstance(mContext).init("41cf173789944b17","5fad59c318397d1c",false,true);
        SpotManager.getInstance(mContext).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
        SpotManager.getInstance(mContext).setAnimationType(SpotManager.ANIM_ADVANCE);
        SpotManager.getInstance(mContext).showSpotAds(mContext);
       /* SpotManager.getInstance(mContext).showSpotAds(mContext, new SpotDialogListener() {
                    @Override
                    public void onShowSuccess() {

                    }

                    @Override
                    public void onShowFailed() {

                    }

                    @Override
                    public void onSpotClosed() {

                    }

                    @Override
                    public void onSpotClick(boolean b) {

                    }
                });*/
    }

}
