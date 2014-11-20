package com.stjerndal.androidgames;

import com.stjerndal.androidgames.framework.Screen;
import com.stjerndal.androidgames.framework.impl.AndroidGame;

public class DogeSnakeGame extends AndroidGameWithAds {
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}

	@Override
	public void onPause() {
		if (Assets.music.isPlaying()) {
			Assets.music.pause();
		}
		if(isFinishing()) {
			Assets.music.stop();
		}
		super.onPause();
	}
	

	
	@Override
	public void onBackPressed() {
	   if(V.exitOnBack) {
		   super.onBackPressed();
	   }
	}
}
