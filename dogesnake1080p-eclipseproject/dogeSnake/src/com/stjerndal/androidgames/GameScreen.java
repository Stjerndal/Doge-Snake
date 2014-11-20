package com.stjerndal.androidgames;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.stjerndal.androidgames.framework.Game;
import com.stjerndal.androidgames.framework.Graphics;
import com.stjerndal.androidgames.framework.Input.KeyEvent;
import com.stjerndal.androidgames.framework.Input.TouchEvent;
import com.stjerndal.androidgames.framework.Pixmap;
import com.stjerndal.androidgames.framework.Screen;
import com.stjerndal.androidgames.framework.impl.Values;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }
    
    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";
    
    Random random = new Random();
    Paint paint = new Paint();
    Boolean updateText = false;
    int dogeTextColor;
    String dogeText;
    int dogeTextX;
    int dogeTextY;
    
    public GameScreen(Game game) {
        super(game);
        world = new World();
        V.exitOnBack = false;
        paint.setTypeface(Assets.fontComicSans);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<KeyEvent> keyEvents = game.getInput().getKeyEvents();
        
        for(int i = 0; i<keyEvents.size();i++) {
        	if(keyEvents.get(i).keyCode == 4 && keyEvents.get(i).type == KeyEvent.KEY_UP) {
        		state = GameState.Paused;
        	}
        }
        
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);        
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {      
    	int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < V.game_pauseButtonPositionX + V.buttonWidth
						&& event.y < V.game_pauseButtonPositionY
								+ V.buttonWidth) {
					if (Settings.soundEnabled)
						Assets.click.play(1);
					state = GameState.Paused;
					return;
				}
			}
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (Settings.controlMode == Settings.TWO_BUTTONS_MODE) {
					updateRunning2btn(event);
				} else if (Settings.controlMode == Settings.FOUR_BUTTONS_MODE) {
					updateRunning4btn(event);
				}
			}
		}
        
        world.update(deltaTime);
        if(world.gameOver) {
            if(Settings.soundEnabled)
                Assets.bitten.play(1);
            state = GameState.GameOver;
        }
        updateText = false;
        if(oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            if(Settings.soundEnabled)
                Assets.eat.play(V.soundVolume);
            updateText = true;
        }
    }
    
	public void updateRunning2btn(TouchEvent event) {
		if (event.x < V.game_2btn_leftButtonPositionX + V.buttonWidth
				&& event.y > V.game_2btn_leftButtonPositionY) {
			world.snake.turnCounterClockwise();
		}
		if (event.x > V.game_2btn_rightButtonPositionX
				&& event.y > V.game_2btn_rightButtonPositionY) {
			world.snake.turnClockwise();
		}
	}

	public void updateRunning4btn(TouchEvent event) {
		if(MainMenuScreen.inBounds(event, 0, V.game_4btn_leftButtonPositionY, V.game_4btn_leftButtonPositionX+V.buttonWidth, V.buttonHeight)) {
			world.snake.turnLeft();
		} else
		if (MainMenuScreen.inBounds(event, V.game_4btn_rightButtonPositionX, V.game_4btn_rightButtonPositionY, V.game_4btn_leftButtonPositionX+V.buttonWidth, V.buttonHeight)) {
			world.snake.turnRight();
		} else
		if(MainMenuScreen.inBounds(event, V.game_4btn_upButtonPositionX-30, V.game_borderPositionY, V.buttonWidth + 60, V.buttonHeight+20)) {
			world.snake.turnUp();
		} else
		if(MainMenuScreen.inBounds(event, V.game_4btn_downButtonPositionX-30, V.game_borderPositionY+V.buttonHeight+20, V.buttonWidth + 60, V.buttonHeight+50)) {
			world.snake.turnDown();
		}
	}
	
    public void drawDogeText() {
    	Graphics g = game.getGraphics();
    	
    	if(updateText) {
    		dogeTextColor = Doge.colors[random.nextInt(Doge.colors.length)];
    		dogeText =  Doge.text[random.nextInt(Doge.text.length)];
    		dogeTextX = 20 + random.nextInt(V.game_textMaxX-20);
    		dogeTextY = V.buttonHeight + random.nextInt(V.game_textMaxY-V.buttonHeight);
    	} 
    	
    	paint.setColor(dogeTextColor);
    	paint.setStyle(Style.FILL);

    	paint.setTextSize(V.game_textSize);
    	g.drawText(dogeText, //Text
    			dogeTextX, 
    			dogeTextY,
    			paint);

    	paint.setStyle(Style.STROKE);
    	paint.setStrokeWidth(V.game_textStrokeSize);
    	paint.setColor(0xff000000);
    	g.drawText(dogeText, //Text
    			dogeTextX, 
    			dogeTextY,
    			paint);
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > V.game_paused_resumeButtonPositionX && event.x <= V.game_paused_resumeButtonPositionX+V.game_paused_menuWidth) {
                    if(event.y > V.game_paused_resumeButtonPositionY && event.y <= V.game_paused_resumeButtonPositionY+V.game_paused_resumeButtonHeight) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }                    
                    if(event.y > V.game_paused_resumeButtonPositionY+V.game_paused_quitButtonInnerPositionY && event.y < V.game_paused_resumeButtonPositionY+V.game_paused_menuHeight) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game)); 
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= V.game_over_cancelButtonPositionX && event.x <= V.game_over_cancelButtonPositionX+V.buttonWidth &&
                   event.y >= V.game_over_cancelButtonPositionY && event.y <= V.game_over_cancelButtonPositionY+V.buttonWidth) {
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
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if(state == GameState.Ready)  {
            drawReadyUI();
        }
        if(state == GameState.Running) {
            drawRunningUI();
            drawCurrentScore();
        }
        if(state == GameState.Paused) {
            drawPausedUI();
            drawCurrentScore();
        }
        if(state == GameState.GameOver){
            drawGameOverUI();
            drawCurrentScore();
        }
        
        
    }
    
    public void drawCurrentScore() {
    	Graphics g = game.getGraphics();
    	drawScore(g, score, (int) (g.getWidth() / 2 - score.length()*V.numberWidth*V.game_scoreScale / 2), V.game_scorePositionY);
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();
        Snake snake = world.snake;
        SnakePart head = snake.parts.get(0);
        Food stain = world.food;
        
        
        Pixmap meatballPixmap = null;
        if(stain.type == Food.TYPE_1)
            meatballPixmap = Assets.meatball1;
        if(stain.type == Food.TYPE_2)
            meatballPixmap = Assets.meatball2;
        if(stain.type == Food.TYPE_3)
            meatballPixmap = Assets.meatball3;
        int x = V.game_marginX +  stain.x * V.squareSize;
        int y = V.game_marginY + stain.y * V.squareSize;      
        g.drawPixmap(meatballPixmap, x, y);             
        
        int len = snake.parts.size();
        for(int i = 1; i < len; i++) {
            SnakePart part = snake.parts.get(i);
            x = V.game_marginX +  part.x * V.squareSize;
            y = V.game_marginY + part.y * V.squareSize;
            g.drawPixmap(Assets.tail, x, y);
        }
        
        Pixmap headPixmap = null;
        if(snake.direction == Snake.UP) 
            headPixmap = Assets.headUp;
        if(snake.direction == Snake.LEFT) 
            headPixmap = Assets.headLeft;
        if(snake.direction == Snake.DOWN) 
            headPixmap = Assets.headDown;
        if(snake.direction == Snake.RIGHT) 
            headPixmap = Assets.headRight;        
//        x = head.x * 32 + 16;
//        y = head.y * 32 + 16;
        x = V.game_marginX +  head.x * V.squareSize;
        y = V.game_marginY + head.y * V.squareSize;
        g.drawPixmap(headPixmap, x - V.headExtraSize()/2, y - V.headExtraSize()/2);
//        g.drawPixmap(headPixmap, x - headPixmap.getWidth()/2, y - headPixmap.getHeight()/2);
    }

    private void drawReadyUI() {
    	Ads.hideBanner(game);
        Graphics g = game.getGraphics();
        
        g.clear(V.shadeScreenColor);
        
        g.drawPixmap(Assets.ready, 47, 100);
        g.drawLine(V.game_borderThickness, 0, V.game_borderPositionY, Values.targetHeight, V.game_borderPositionY, Color.BLACK);
    }
    
    private void drawRunningUI() {
    	Ads.hideBanner(game);
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.buttons, V.game_pauseButtonPositionX, V.game_pauseButtonPositionY, V.pauseButtonInnerPositionX, V.pauseButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        
        if (Settings.controlMode == Settings.TWO_BUTTONS_MODE) {
        	drawRunning2btn(g);
		} else if (Settings.controlMode == Settings.FOUR_BUTTONS_MODE) {
			drawRunning4btn(g);
		}
        
        if(oldScore > 0) {
        	drawDogeText();
        }
    }

    public void drawRunning2btn(Graphics g) {
        g.drawLine(V.game_borderThickness, 0, V.game_borderPositionY, Values.targetHeight, V.game_borderPositionY, Color.BLACK);
        g.drawPixmap(Assets.buttons, V.game_2btn_leftButtonPositionX, V.game_2btn_leftButtonPositionY, V.leftButtonInnerPositionX, V.leftButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        g.drawPixmap(Assets.buttons, V.game_2btn_rightButtonPositionX, V.game_2btn_rightButtonPositionY, V.rightButtonInnerPositionX, V.rightButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
    }
    
    public void drawRunning4btn(Graphics g) {
        g.drawLine(V.game_borderThickness, 0, V.game_borderPositionY, Values.targetHeight, V.game_borderPositionY, Color.BLACK);
        g.drawPixmap(Assets.buttons, V.game_4btn_upButtonPositionX, V.game_4btn_upButtonPositionY, V.upButtonInnerPositionX, V.upButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        g.drawPixmap(Assets.buttons, V.game_4btn_downButtonPositionX, V.game_4btn_downButtonPositionY, V.downButtonInnerPositionX, V.downButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        g.drawPixmap(Assets.buttons, V.game_4btn_leftButtonPositionX, V.game_4btn_leftButtonPositionY, V.leftButtonInnerPositionX, V.leftButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        g.drawPixmap(Assets.buttons, V.game_4btn_rightButtonPositionX, V.game_4btn_rightButtonPositionY, V.rightButtonInnerPositionX, V.rightButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
    }
    
    private void drawPausedUI() {
    	Ads.showBanner(game);
        Graphics g = game.getGraphics();
        
        g.clear(V.shadeScreenColor);
        
        g.drawPixmap(Assets.pause, V.game_paused_resumeButtonPositionX, V.game_paused_resumeButtonPositionY);
        g.drawLine(V.game_borderThickness, 0, V.game_borderPositionY, Values.targetHeight, V.game_borderPositionY, Color.BLACK);
    }
    
    private void drawGameOverUI() {
    	Ads.hideBanner(game);
        Graphics g = game.getGraphics();
        
        g.clear(V.shadeScreenColor);
        
        g.drawPixmap(Assets.gameOver, V.game_over_positionX, V.game_over_positionY);
        g.drawPixmap(Assets.buttons, V.game_over_cancelButtonPositionX, V.game_over_cancelButtonPositionY, V.cancelButtonInnerPositionX, V.cancelButtonInnerPositionY, V.buttonWidth, V.buttonHeight);
        g.drawLine(V.game_borderThickness,0, V.game_borderPositionY, Values.targetHeight, V.game_borderPositionY, Color.BLACK);
    }
    
    public void drawScore(Graphics g, String line, int x, int y) {
        int len = line.length();
        int srcX = 0;
        int srcWidth = 0;
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
            
            if (character == ' ') {
                x += V.spaceWidth * V.game_scoreScale;
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
    
            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, V.numberHeight, V.game_scoreScale);
            x += srcWidth-(V.charContraction*(1/V.game_scoreScale));
        }
    }

    @Override
    public void pause() {
        if(state == GameState.Running) {
            state = GameState.Paused;
        }
        if(world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
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

