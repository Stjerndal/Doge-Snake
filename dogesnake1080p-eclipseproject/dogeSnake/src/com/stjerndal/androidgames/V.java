package com.stjerndal.androidgames;

import android.util.Log;

import com.stjerndal.androidgames.framework.impl.Values;

public class V {
	//Testcode
	public static final boolean debugging = false;
//	public static final boolean debugging = true;
	
	//Methods
	public static void log(String message) {
        Log.d("MrBob", message);
	}
	
	//GLOBAL STUFF
	public static final String extFileName = ".dogesnake";
	public static final float soundVolume = 0.3f;
	
	public static boolean exitOnBack = true;
	
	//GENERAL UI
	public static final int targetHeight = Values.targetWidth;
	public static final int targetWidth = Values.targetHeight;
	
	public static final int buttonWidth = 220;
	public static final int buttonHeight = 220;
	
	public static final int numberWidth = 150;
	public static final int numberHeight = 174;
	public static final int spaceWidth = 75;
	public static final int dotWidth = 75;
	public static final int dotInnerPositionX = 1500;
	public static final int charContraction = 20;
	
	public static final int soundButtonInnerPositionX = 0;
	public static final int soundButtonInnerPositionY = 0;
	public static final int muteButtonInnerPositionX = buttonWidth;
	public static final int muteButtonInnerPositionY = 0;
	public static final int rightButtonInnerPositionX = 0;
	public static final int rightButtonInnerPositionY = buttonHeight;
	public static final int leftButtonInnerPositionX = buttonWidth;
	public static final int leftButtonInnerPositionY = buttonHeight;
	public static final int upButtonInnerPositionX = 0;
	public static final int upButtonInnerPositionY = buttonHeight*2;
	public static final int downButtonInnerPositionX = buttonWidth;
	public static final int downButtonInnerPositionY = buttonHeight*2;
	public static final int cancelButtonInnerPositionX = 0;
	public static final int cancelButtonInnerPositionY = buttonHeight*3;
	public static final int pauseButtonInnerPositionX = buttonWidth;
	public static final int pauseButtonInnerPositionY = buttonHeight*3;
	public static final int control4ButtonInnerPositionX = 0;
	public static final int control4ButtonInnerPositionY = buttonHeight*4;
	public static final int control2ButtonInnerPositionX = buttonWidth;
	public static final int control2ButtonInnerPositionY = buttonHeight*4;
	
	public static final int shadeScreenColor = 0x44000000;
	
	
	public static final int squareSize = 97;
//	public static int headExtraSize = 0;
	public static final int bobHeadSize = 135;

	public static int  headSize() {
//		switch (Settings.controlMode) {
//		case Settings.TWO_BUTTONS_MODE:
//			return bobHeadSize;
//		case Settings.FOUR_BUTTONS_MODE:
			return squareSize;
//		}
//		return bobHeadSize;
	}
	
	public static int  headExtraSize() {
//		switch (Settings.controlMode) {
//		case Settings.TWO_BUTTONS_MODE:
//			return bobHeadSize - squareSize;
//		case Settings.FOUR_BUTTONS_MODE:
			return 0;
//		}
//		return Settings.TWO_BUTTONS_MODE;
	} 
		
	//MAIN MENU SCREEN
	public static final int main_logoPositionX = 5;
	public static final int main_logoPositionY = 185;
	
	public static final int main_menuWidth = 888;
	public static final int main_menuHeight = 696;
	public static final int main_menuPositionX = (targetWidth/2) - (main_menuWidth/2);
	public static final int main_menuPositionY = 852;
	
	public static final int main_playButtonWidth = 650;
	public static final int main_playButtonHeight = 245;
	public static final int main_playButtonInnerPositionX = 119;
	public static final int main_playButtonInnerPositionY = 0;
	public static final int main_playButtonPositionX = main_playButtonInnerPositionX + main_menuPositionX;
	public static final int main_playButtonPositionY = main_playButtonInnerPositionY + main_menuPositionY;
	public static final int main_highscoresButtonWidth = 888;
	public static final int main_highscoresButtonHeight = 232;
	public static final int main_highscoresButtonInnerPositionX = 0;
	public static final int main_highscoresButtonInnerPositionY = main_playButtonHeight;
	public static final int main_highscoresButtonPositionX = main_highscoresButtonInnerPositionX + main_menuPositionX;
	public static final int main_highscoresButtonPositionY = main_highscoresButtonInnerPositionY + main_menuPositionY;
	public static final int main_helpButtonWidth = 650;
	public static final int main_helpButtonHeight = 245;
	public static final int main_helpButtonInnerPositionX = 119;
	public static final int main_helpButtonInnerPositionY = main_playButtonHeight + main_highscoresButtonInnerPositionY;
	public static final int main_helpButtonPositionX = main_helpButtonInnerPositionX + main_menuPositionX;
	public static final int main_helpButtonPositionY = main_helpButtonInnerPositionY + main_menuPositionY;
	
	public static final int main_soundButtonPositionX = 36;
	public static final int main_soundButtonPositionY = targetHeight - (buttonHeight+60);
	public static final int main_controlModeButtonPositionX = targetWidth - (buttonWidth+36);
	public static final int main_controlModeButtonPositionY = main_soundButtonPositionY;
//	public static final int main_dogButtonPositionX = targetWidth-(headSize() + 36);;
//	public static final int main_dogButtonPositionY = targetHeight - (headSize()+60);
	
	//Game screen
	public static final int game_pauseButtonPositionX = 0;
	public static final int game_pauseButtonPositionY =0;
	
	
	
//	public static final int game_2btn_leftButtonPositionX = 36;
//	public static final int game_2btn_leftButtonPositionY = targetHeight - (buttonHeight+60);
//	public static final int game_2btn_rightButtonPositionX = targetWidth-(buttonWidth + 36);
//	public static final int game_2btn_rightButtonPositionY = targetHeight - (buttonHeight+60);
	
	public static final int game_borderThickness = 15;
	public static final int game_borderPositionY =  World.WORLD_HEIGHT * squareSize;
	
	public static final int game_4btn_upButtonPositionX = (targetWidth/2) - (buttonWidth/2);
	public static final int game_4btn_upButtonPositionY = targetHeight - (buttonHeight+buttonHeight+20);
	public static final int game_4btn_leftButtonPositionX = (targetWidth/2) - (buttonWidth + buttonWidth/2);
	public static final int game_4btn_leftButtonPositionY = targetHeight - (buttonHeight + 10 + buttonHeight/2);
	public static final int game_4btn_rightButtonPositionX = (targetWidth/2) + (buttonWidth/2);
	public static final int game_4btn_rightButtonPositionY = game_4btn_leftButtonPositionY;
	public static final int game_4btn_downButtonPositionX = game_4btn_upButtonPositionX;
	public static final int game_4btn_downButtonPositionY = targetHeight - buttonHeight-10;
	
	public static final int game_2btn_leftButtonPositionX = game_4btn_leftButtonPositionX;
	public static final int game_2btn_leftButtonPositionY = game_4btn_leftButtonPositionY;
	public static final int game_2btn_rightButtonPositionX = game_4btn_rightButtonPositionX;
	public static final int game_2btn_rightButtonPositionY = game_4btn_rightButtonPositionY;
	

	
//	public static final int game_4btn_borderPositionY =  game_4btn_upButtonPositionY-(targetHeight - (game_2btn_leftButtonPositionY + buttonHeight));
	
	
	public static final int game_marginX = 7;
	public static final int game_marginY = 0;
	
	public static final float game_scoreScale = 0.7f;
//	public static final int game_scorePositionY = game_2btn_leftButtonPositionY + (int) (numberHeight*game_scoreScale*0.5);
	public static final int game_scorePositionY = 55;
	
	public static final int game_paused_menuWidth = 630;
	public static final int game_paused_menuHeight = 402;
	public static final int game_paused_resumeButtonHeight = 211;
	public static final int game_paused_resumeButtonPositionX = (targetWidth/2) - (game_paused_menuWidth/2);
	public static final int game_paused_resumeButtonPositionY = targetHeight / 5;
	public static final int game_paused_quitButtonInnerPositionY = game_paused_resumeButtonHeight;
	
	public static final int game_ready_width = 960;
	public static final int game_ready_height = 420;
	public static final int game_ready_positionX = (targetWidth/2) - (game_ready_width/2);
	public static final int game_ready_positionY = game_paused_resumeButtonPositionY;
	
	public static final int game_over_width = 558;
	public static final int game_over_height = 420;
	public static final int game_over_positionX = (targetWidth/2) - (game_over_width/2);
	public static final int game_over_positionY = game_paused_resumeButtonPositionY;
	public static final int game_over_cancelButtonPositionX = (targetWidth/2) - (buttonWidth/2);
	public static final int game_over_cancelButtonPositionY = game_paused_resumeButtonPositionY+game_paused_menuHeight+buttonHeight/3;
	
	public static final int game_textSize = 90;
	public static final int game_textStrokeSize = 1;
	public static final int game_textMaxX = targetWidth - 710;
	public static final int game_textMaxY = game_borderPositionY - game_textSize - 10;
	
	
	//Highscore Screen
	public static final int highscores_numberOfHighscores = 5;
	public static final int[] highscoresDefault = new int[] { 50, 40, 25, 15, 5 };
	
	public static final int highscores_headerPositionX = (targetWidth/2) - (main_highscoresButtonWidth/2);
	public static final int highscores_headerPositionY = 100;
	//See main_highscoresButton for header width & height
	
	public static final int highscores_scoreMarginX = (targetWidth/2) - (3*numberWidth);
	public static final int highscores_scoreMarginY = (numberHeight+20); 
	
	public static final int highscores_backPositionX = main_soundButtonPositionX; 
	public static final int highscores_backPositionY = main_soundButtonPositionY; 
	
	//Help screens
	public static final int help_screenWidth = 984;
	public static final int help_screenHeight = 1312;
	public static final int help_screenPositionX = (targetWidth/2) - (help_screenWidth/2);
	public static final int help_screenPositionY = 185;
	
	public static final int help_nextButtonPositionX = targetWidth-(buttonWidth + 36);
	public static final int help_nextButtonPositionY = targetHeight - (buttonHeight+60);
	
}
