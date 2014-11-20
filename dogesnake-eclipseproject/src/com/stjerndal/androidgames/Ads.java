package com.stjerndal.androidgames;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.stjerndal.androidgames.framework.Game;
import com.stjerndal.androidgames.framework.impl.AndroidGame;

public class Ads {
	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-5464445636613782/1509756764";
	private static final String INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-5464445636613782/3507756762";
	
	public static FrameLayout layout;
	public static FrameLayout.LayoutParams gameParams;
	public static FrameLayout.LayoutParams adsParams;
	
	
	public static AdView bannerAdView;
	public static InterstitialAd interstitial;
	public static InterstitialAd interstitialDispOnLoad;
	
	public static void prepareLayout(AndroidGame game) {
		layout = new FrameLayout(game);
		gameParams = new FrameLayout.LayoutParams(
					FrameLayout.LayoutParams.FILL_PARENT,
					FrameLayout.LayoutParams.FILL_PARENT);
		 adsParams = new FrameLayout.LayoutParams(
					FrameLayout.LayoutParams.FILL_PARENT,
					FrameLayout.LayoutParams.WRAP_CONTENT, android.view.Gravity.TOP
							| android.view.Gravity.CENTER_HORIZONTAL);
	}
	
	public static void prepareBanner(Activity activity) {
		bannerAdView = new AdView(activity);
		bannerAdView.setAdSize(AdSize.SMART_BANNER);
		bannerAdView.setAdUnitId(BANNER_AD_UNIT_ID);
	}
	
    //a banner at the bottom displays, but it overlaps my game screen
	public static void loadBanner() {
		AdRequest adRequest;
		if(V.debugging) {
		adRequest = new AdRequest.Builder()
		 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		 .addTestDevice("F823A250C86902118094540C40DC0DC6")
				.build();
		} else {
			adRequest = new AdRequest.Builder()
//			.setGender(AdRequest.GENDER_MALE)
//		    .setBirthday(new GregorianCalendar(1994, 1, 1).getTime())
			.build();
		}
		bannerAdView.loadAd(adRequest);
		bannerAdView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				layout.requestLayout();
				layout.invalidate();
			}
		});
	}
	
	public static void hideBanner(Game game) {
		((AndroidGame)game).runOnUiThread(new Runnable() {
			public void run() {
				if (layout.indexOfChild(bannerAdView) >= 0) {
//					bannerAdView.destroy();
					layout.removeView(bannerAdView);
//					layout.bringChildToFront(layout.getChildAt(0));
					layout.requestLayout();
					layout.invalidate();
				}
			}
		});
	}

	public static void displayBanner(Game game) {

		((AndroidGame)game).runOnUiThread(new Runnable() {
			public void run() {
				
				if (layout.indexOfChild(bannerAdView) < 0) {
					loadBanner();
//					bannerAdView.prepare
//					layout.bringChildToFront(bannerAdView);
					layout.addView(bannerAdView);
					layout.requestLayout();
					layout.invalidate();
				}
			}
		});
	}
	
	
	public static void prepareInterstitial(Activity activity) {
		interstitial = new InterstitialAd(activity);
	    interstitial.setAdUnitId(INTERSTITIAL_AD_UNIT_ID);
	}
	
	public static void loadInterstitial(Activity activity) {
		
		
		activity.runOnUiThread(new Runnable(){
		    public void run(){
		    	AdRequest adRequest;
				if(V.debugging) {
				adRequest = new AdRequest.Builder()
				 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				 .addTestDevice("F823A250C86902118094540C40DC0DC6")
						.build();
				} else {
					adRequest = new AdRequest.Builder()
//					.setGender(AdRequest.GENDER_MALE)
//				    .setBirthday(new GregorianCalendar(1994, 1, 1).getTime())
					.build();
				}
				
		    	interstitial.loadAd(adRequest);
//				interstitial.setAdListener(new AdListener() {
//					@Override
//					public void onAdLoaded() {
//						displayInterstitial();
//					}
//				});
		    }
		});
		

	}
	
	public static void displayInterstitial(Activity activity) {
		activity.runOnUiThread(new Runnable(){
		    public void run(){
		    	if (interstitial.isLoaded()) {
		    		interstitial.show();
		    	}
		    }
		    });
	}
	
	
	public static void prepareInterstitialDispOnLoad(Activity activity) {
		interstitialDispOnLoad = new InterstitialAd(activity);
	    interstitialDispOnLoad.setAdUnitId(INTERSTITIAL_AD_UNIT_ID);
	}
	
	public static void loadInterstitialDispOnLoad(Activity activity) {
		
		activity.runOnUiThread(new Runnable(){
		    public void run(){
		    	AdRequest adRequest;
				if(V.debugging) {
				adRequest = new AdRequest.Builder()
				 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				 .addTestDevice("F823A250C86902118094540C40DC0DC6")
						.build();
				} else {
					adRequest = new AdRequest.Builder()
//					.setGender(AdRequest.GENDER_MALE)
//				    .setBirthday(new GregorianCalendar(1994, 1, 1).getTime())
					.build();
				}
				
		    	interstitialDispOnLoad.loadAd(adRequest);
				interstitialDispOnLoad.setAdListener(new AdListener() {
					@Override
					public void onAdLoaded() {
						displayInterstitial();
					}
				});
		    }
		});
		

	}
	
	private static void displayInterstitial() {
//		activity.runOnUiThread(new Runnable(){
//		    public void run(){
		    	if (interstitialDispOnLoad.isLoaded()) {
		    		interstitialDispOnLoad.show();
		    	}
//		    }
//		    });
	}
	

}
