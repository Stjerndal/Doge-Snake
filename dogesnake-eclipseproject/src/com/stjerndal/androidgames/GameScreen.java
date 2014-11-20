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
import com.stjerndal.androidgames.framework.impl.AndroidGame;
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
    
    boolean gameHasEnded;
    
    public GameScreen(Game game) {
        super(game);
        world = new World();
        V.exitOnBack = false;
        paint.setTypeface(Assets.fontComicSans);
        gameHasEnded = false;
        Ads.loadInterstitial((AndroidGameWithAds) game);
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
    		dogeTextX = 13 + random.nextInt(V.game_textMaxX-13);
    		dogeTextY = V.buttonHeight + random.nextInt(V.game_textMaxY-V.buttonHeight);
    	} 
    	
    	paint.setColor(dogeTextColor);
    	paint.setStyle(Style.FILL);

    	paint.setTextSize(V.game_textSize);
    	g.drawText(dogeText, //Text
    			dogeTextX, 
    			dogeTextY,
    			paint);

//    	paint.setStyle(Style.STROKE);
//    	paint.setStrokeWidth(V.game_textStrokeSize);
//    	paint.setColor(0xff000000);
//    	g.drawText(dogeText, //Text
//    			dogeTextX, 
//    			dogeTextY,
//    			paint);
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
    	if(!gameHasEnded) {
//        	Ads.prepareInterstitial((AndroidGameWithAds) game);
//    	    Ads.loadInterstitial((AndroidGameWithAds) game);
    		if(V.debugging) V.log("CALLING A LOT OF GPGS API STUFF NOW");
    		gameOverUpdateStats(world.score);
    		gameHasEnded = true;
    	}
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= V.game_over_cancelButtonPositionX && event.x <= V.game_over_cancelButtonPositionX+V.buttonWidth &&
                   event.y >= V.game_over_cancelButtonPositionY && event.y <= V.game_over_cancelButtonPositionY+V.buttonWidth) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    Ads.displayInterstitial((AndroidGameWithAds) game);
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
        
        g.drawPixmap(Assets.ready, V.game_ready_positionX , V.game_ready_positionY);
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
    	Ads.displayBanner(game);
        Graphics g = game.getGraphics();
        
        g.clear(V.shadeScreenColor);
        
        g.drawPixmap(Assets.pause, V.game_paused_resumeButtonPositionX, V.game_paused_resumeButtonPositionY);
        g.drawLine(V.game_borderThickness, 0, V.game_borderPositionY, Values.targetHeight, V.game_borderPositionY, Color.BLACK);
    }
    
    private void drawGameOverUI() {
//    	Ads.hideBanner(game);
    	
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
	
	public boolean gameOverUpdateStats(int score) {
		AndroidGame aGame = ((AndroidGame) game);
		Settings.totalPlayedGames++;
		if(V.debugging) V.log(score + " / " + World.SCORE_INCREMENT + " = " + (score / World.SCORE_INCREMENT));
		Settings.totalMeatballs += score / World.SCORE_INCREMENT;
		 if(aGame.isSignedIn()) {
         	aGame.submitNormalHighscore(score);
         	aGame.submitHighscore(aGame.getString(R.string.leaderboard_most_total_meatballs), Settings.totalMeatballs);
         	if(Settings.totalPlayedGames > 0) {
         		aGame.unlockAchievement(aGame.getString(R.string.achievement_much_welcome));
         	}
         	achievementsEatenMeatballs(aGame, score / World.SCORE_INCREMENT);
         	achievementsPlayedGames(aGame);
         	achievementsTotalPlayedGame(aGame, score);
         	return true;
		 } else {
			 return false;
		 }
	}
	
	public void achievementsPlayedGames(AndroidGame aGame) {
		aGame.incrementAchievement(aGame.getString(R.string.achievement_such_fun));
		aGame.incrementAchievement(aGame.getString(R.string.achievement_ten_tiem));
		aGame.incrementAchievement(aGame.getString(R.string.achievement_so_experience));
		aGame.incrementAchievement(aGame.getString(R.string.achievement_such_gaming));
		aGame.incrementAchievement(aGame.getString(R.string.achievement_moar_gaming));
		aGame.incrementAchievement(aGame.getString(R.string.achievement_much_quarter_three));
		aGame.incrementAchievement(aGame.getString(R.string.achievement_centurion));
	}
	
	public void achievementsEatenMeatballs(AndroidGame aGame, int numMeatballs) {
		if(numMeatballs < 1) {
			return;
		}
		aGame.incrementAchievement(aGame.getString(R.string.achievement_yum), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_yum_yum), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_much_taste), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_such_delicious), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_so_love_meatball), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_balls_of_meat), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_wow__meat__balls_), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_balls), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_very_v_hundred), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_fatty_boom_boom), numMeatballs);
		aGame.incrementAchievement(aGame.getString(R.string.achievement_what_over_nine_thousand), numMeatballs);
		
		//Increment 1 every 10 meatballs.
		if(Settings.totalMeatballs > 10) {
			int tensOfBalls = Settings.totalMeatballs / 10;
			aGame.setAchievementSteps(aGame.getString(R.string.achievement_amazeballs), tensOfBalls);
			aGame.setAchievementSteps(aGame.getString(R.string.achievement_yum__so_very_yum_), tensOfBalls);
			aGame.setAchievementSteps(aGame.getString(R.string.achievement_must__eat__all__balls_), tensOfBalls);
		}
	}
	
	public void achievementsTotalPlayedGame(AndroidGame aGame, int score) {
		if(score < 50) {
			return;
		} if(score >= 50) {
			aGame.unlockAchievement(aGame.getString(R.string.achievement_some_point));
		} if(score >= 100) {
			aGame.unlockAchievement(aGame.getString(R.string.achievement_such_points));
		} if(score >= 150) {
			aGame.unlockAchievement(aGame.getString(R.string.achievement_such_speed));
		} if(score >= 200) {
			aGame.unlockAchievement(aGame.getString(R.string.achievement_wow_so_doble_hundred_));
		} if(score >= 250) {
			aGame.unlockAchievement(aGame.getString(R.string.achievement_amaze));
		} if(score >= 300) {
			aGame.unlockAchievement(aGame.getString(R.string.achievement_wat_moar_point));
		} if(score >= 400) {
			aGame.unlockAchievement(aGame.getString(R.string.achievement_wow__amaze__such_skilz_));
		} if (score >= 500){
			aGame.unlockAchievement(aGame.getString(R.string.achievement_wat_));
		}
	}

    @Override
    public void dispose() {
        
    }
}

