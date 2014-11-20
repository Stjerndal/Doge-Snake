package com.stjerndal.androidgames.framework.impl;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.example.games.basegameutils.BaseGameActivity;
import com.stjerndal.androidgames.R;
import com.stjerndal.androidgames.V;
import com.stjerndal.androidgames.framework.Audio;
import com.stjerndal.androidgames.framework.FileIO;
import com.stjerndal.androidgames.framework.Game;
import com.stjerndal.androidgames.framework.Graphics;
import com.stjerndal.androidgames.framework.Input;
import com.stjerndal.androidgames.framework.Screen;

public abstract class AndroidGame extends BaseGameActivity implements Game {
    protected AndroidFastRenderView renderView;
    protected Graphics graphics;
    protected Audio audio;
    protected Input input;
    protected FileIO fileIO;
    protected Screen screen;
    protected WakeLock wakeLock;
    
    final int RC_RESOLVE = 5000, RC_UNUSED = 5001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? Values.targetWidth : Values.targetHeight;
        int frameBufferHeight = isLandscape ? Values.targetHeight : Values.targetWidth;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        View view = setupView();
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, view, scaleX, scaleY);
        screen = getStartScreen();
        
        setContentView(view);
        
        
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }
    
    public View setupView() {
    	return renderView;
    }

    public Input getInput() {
        return input;
    }

    public FileIO getFileIO() {
        return fileIO;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    public Screen getCurrentScreen() {
        return screen;
    }
    
    public AndroidFastRenderView getRenderView() {
    	return renderView;
    }
    
    public void signIn() {
    	beginUserInitiatedSignIn();
    }
    
    public void signOut() {
    	super.signOut();
    }
    
    public boolean isSignedIn() {
    	return super.isSignedIn();
    }
    
    public void submitNormalHighscore(int score) {
    	getGamesClient().submitScore(getString(R.string.leaderboard_much_highscore), score);
    }
    
    public void submitHighscore(String leaderboardID, int score) {
    	getGamesClient().submitScore(leaderboardID, score);
    }
    
    public boolean showLeaderboards() {
        if (isSignedIn()) {
            startActivityForResult(getGamesClient().getAllLeaderboardsIntent(), RC_UNUSED);
            return true;
        } else {
            showAlert(getString(R.string.leaderboards_not_signed_in));
            return false;
            //TODO change this, causes error
        }
    }
    
    public boolean showAchievements() {
        if (isSignedIn()) {
            startActivityForResult(getGamesClient().getAchievementsIntent(), RC_UNUSED);
            return true;
        } else {
        	runOnUiThread(new Runnable() {
    			public void run() {
    				showAlert(getString(R.string.achievements_not_signed_in));
    			}
    		});
            
            return false;
          //TODO change this, causes error
        }
    }
    
    public void incrementAchievement(String achievementID) {
    	getGamesClient().incrementAchievement(achievementID, 1);
    }
    
    public void incrementAchievement(String achievementID, int amount) {
    	getGamesClient().incrementAchievement(achievementID, amount);
    }
    
    public void setAchievementSteps(String achievementID, int steps) {
    	getGamesClient().setAchievementSteps(achievementID, steps);
    }
    
    public void unlockAchievement(String achievementID) {
    	getGamesClient().unlockAchievement(achievementID);
    }
    
    
}
