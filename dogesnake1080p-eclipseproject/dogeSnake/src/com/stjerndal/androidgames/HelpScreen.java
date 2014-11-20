package com.stjerndal.androidgames;

import java.util.List;

import com.stjerndal.androidgames.framework.Game;
import com.stjerndal.androidgames.framework.Graphics;
import com.stjerndal.androidgames.framework.Input.KeyEvent;
import com.stjerndal.androidgames.framework.Input.TouchEvent;
import com.stjerndal.androidgames.framework.Screen;


public class HelpScreen extends Screen {      
    public HelpScreen(Game game) {
        super(game);
        V.exitOnBack = false;
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<KeyEvent> keyEvents = game.getInput().getKeyEvents();
        
        for(int i = 0; i<keyEvents.size();i++) {
        	if(keyEvents.get(i).keyCode == 4 && keyEvents.get(i).type == KeyEvent.KEY_UP) {
        		game.setScreen(new MainMenuScreen(game));
        		return;
        	}
        }
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > V.help_nextButtonPositionX && event.y > V.help_nextButtonPositionY ) {
                    game.setScreen(new HelpScreen2(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
    	Ads.showBanner(game);
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.help1, V.help_screenPositionX, V.help_screenPositionY);
        g.drawPixmap(Assets.buttons, V.help_nextButtonPositionX, V.help_nextButtonPositionY, V.rightButtonInnerPositionX, V.rightButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
    }

    @Override
    public void pause() {

    }

    @Override
	public void resume() {
		if (!Assets.music.isPlaying() && Settings.soundEnabled) {
			Assets.music.play();
			Assets.music.setVolume(V.soundVolume);
			if (!Assets.music.isLooping()) {
				Assets.music.setLooping(true);
			}
		}
	}

    @Override
    public void dispose() {

    }
}
