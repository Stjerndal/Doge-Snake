package com.stjerndal.androidgames;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.stjerndal.androidgames.framework.impl.AndroidGame;

public abstract class AndroidGameWithAds extends AndroidGame {

   @Override
   public View setupView(){
	   Ads.prepareBanner(this);
//	   LinearLayout layout = new LinearLayout(this);
//	   layout.setOrientation(LinearLayout.VERTICAL);
//	   layout.addView(Ads.bannerAdView);
//	   layout.addView(renderView);
//	   Ads.loadBanner();
//	   return layout;
	   Ads.prepareLayout(this);
	    Ads.layout.addView(renderView, Ads.gameParams);
	    Ads.layout.addView(Ads.bannerAdView, Ads.adsParams);
	    Ads.loadBanner();
	    
	    return Ads.layout;
//	    setContentView(layout);
//	   setContentView(layout);
   }

}
