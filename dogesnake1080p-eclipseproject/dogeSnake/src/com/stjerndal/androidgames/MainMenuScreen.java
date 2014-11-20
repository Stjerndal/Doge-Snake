package com.stjerndal.androidgames;

import java.util.List;

import com.stjerndal.androidgames.framework.Game;
import com.stjerndal.androidgames.framework.Graphics;
import com.stjerndal.androidgames.framework.Graphics.PixmapFormat;
import com.stjerndal.androidgames.framework.Input.TouchEvent;
import com.stjerndal.androidgames.framework.Screen;
import com.stjerndal.androidgames.framework.impl.AndroidGame;


public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
        V.exitOnBack = true;
    }   

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, V.main_soundButtonPositionX, V.main_soundButtonPositionY, V.buttonWidth, V.buttonHeight)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    if(Assets.music.isPlaying()) {
                    	Assets.music.pause();
                    } else {
                    	Assets.music.play();
                    	Assets.music.setVolume(V.soundVolume);
                    	Assets.music.setLooping(true);
                    }
                }
                if(event.x > V.main_controlModeButtonPositionX && event.y > V.main_controlModeButtonPositionY) {
                	if(Settings.soundEnabled)
                        Assets.click.play(1);
                    Settings.switchControlMode();
//                    LoadingScreen.loadDog(game);
                }
                if(inBounds(event, V.main_playButtonPositionX, V.main_playButtonPositionY, V.main_playButtonWidth, V.main_playButtonHeight) ) {
                	if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new GameScreen(game));
                    return;
                }
                if(inBounds(event, V.main_highscoresButtonPositionX, V.main_highscoresButtonPositionY, V.main_highscoresButtonWidth, V.main_highscoresButtonHeight) ) {
                	if(Settings.soundEnabled)
                        Assets.click.play(1);
                	game.setScreen(new HighscoreScreen(game));
                    return;
                }
                if(inBounds(event, V.main_helpButtonPositionX, V.main_helpButtonPositionY, V.main_helpButtonWidth, V.main_helpButtonHeight) ) {
                	if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new HelpScreen(game));
                    return;
                }
            }
        }
    }
    
    public static boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    public void present(float deltaTime) {
    	Ads.showBanner(game);
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, V.main_logoPositionX, V.main_logoPositionY);
        g.drawPixmap(Assets.mainMenu, V.main_menuPositionX, V.main_menuPositionY);
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.buttons, V.main_soundButtonPositionX, V.main_soundButtonPositionY, V.soundButtonInnerPositionX, V.soundButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        else
            g.drawPixmap(Assets.buttons, V.main_soundButtonPositionX, V.main_soundButtonPositionY, V.muteButtonInnerPositionX, V.muteButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        
        if(Settings.controlMode == Settings.FOUR_BUTTONS_MODE) {
        	g.drawPixmap(Assets.buttons, V.main_controlModeButtonPositionX, V.main_controlModeButtonPositionY, V.control4ButtonInnerPositionX, V.control4ButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        } else if(Settings.controlMode == Settings.TWO_BUTTONS_MODE) {
        	g.drawPixmap(Assets.buttons, V.main_controlModeButtonPositionX, V.main_controlModeButtonPositionY, V.control2ButtonInnerPositionX, V.control2ButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        }
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {
    	 if(!Assets.music.isPlaying() && Settings.soundEnabled){
    		 Assets.music.play();
    		 Assets.music.setVolume(V.soundVolume);
    		 if(!Assets.music.isLooping()) {
    			 Assets.music.setLooping(true);
    		 }
    	 }
    }

	@Override
	public void dispose() {
	}

}

