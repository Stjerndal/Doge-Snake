package com.stjerndal.androidgames;

import java.util.List;

import com.stjerndal.androidgames.framework.Game;
import com.stjerndal.androidgames.framework.Graphics;
import com.stjerndal.androidgames.framework.Input.KeyEvent;
import com.stjerndal.androidgames.framework.Input.TouchEvent;
import com.stjerndal.androidgames.framework.Screen;


public class HighscoreScreen extends Screen {
    String lines[] = new String[V.highscores_numberOfHighscores];

    public HighscoreScreen(Game game) {
        super(game);

        for (int i = 0; i < V.highscores_numberOfHighscores; i++) {
            lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
        }
        
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
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < V.highscores_backPositionX+V.buttonWidth && event.y > V.highscores_backPositionY) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
    	Ads.hideBanner(game);
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        //Draw "HIGHSCORE" headline:
        g.drawPixmap(Assets.mainMenu, V.highscores_headerPositionX, V.highscores_headerPositionY, V.main_highscoresButtonInnerPositionX, V.main_highscoresButtonInnerPositionY, V.main_highscoresButtonWidth, V.main_highscoresButtonHeight);

        int y = V.highscores_scoreMarginY*2;
        for (int i = 0; i < V.highscores_numberOfHighscores; i++) {
            drawText(g, lines[i], V.highscores_scoreMarginX, y);
            y += V.highscores_scoreMarginY;
        }

        g.drawPixmap(Assets.buttons, V.highscores_backPositionX, V.highscores_backPositionY, V.leftButtonInnerPositionX, V.leftButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        int srcX = 0;
        int srcWidth = 0;
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
            
            if (character == ' ') {
                x += V.spaceWidth;
                continue;
            }
    
            srcX = 0;
            srcWidth = 0;
            if (character == '.') {
                srcX = V.dotInnerPositionX;
                srcWidth = V.dotWidth;
            } else {
                srcX = (character - '0') * V.numberWidth;
                srcWidth = V.numberWidth;
            }
    
            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, V.numberHeight);
            x += srcWidth-V.charContraction;
        }
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
