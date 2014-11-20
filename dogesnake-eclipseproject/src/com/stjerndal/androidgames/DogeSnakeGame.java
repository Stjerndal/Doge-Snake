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
		try {
			if (Assets.music.isPlaying()) {
				Assets.music.pause();
			}
			if(isFinishing()) {
				Assets.music.stop();
			}
		} catch (NullPointerException e) {
			//Ok
		}
		
		super.onPause();
	}
	

	
	@Override
	public void onBackPressed() {
	   if(V.exitOnBack) {
		   super.onBackPressed();
	   }
	}

	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub
		
	}
}
