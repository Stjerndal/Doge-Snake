package com.stjerndal.androidgames;

import android.util.Log;

import com.stjerndal.androidgames.framework.impl.Values;

public class V {
	//Testcode
	public static final boolean debugging = false;
//	public static final boolean debugging = true;
	
	//Methods
	public static void log(String message) {
        Log.d("DogeSnake", message);
	}
	
	//GLOBAL STUFF
	public static final String extFileName = ".dogesnake";
//	public static final String extFileName = "dogesnakeTest";
	public static final float soundVolume = 0.3f;
	
	public static boolean exitOnBack = true;
	
	//GENERAL UI
	public static final int targetHeight = Values.targetWidth;
	public static final int targetWidth = Values.targetHeight;
	
	public static final int buttonWidth = 147;
	public static final int buttonHeight = 147;
	
	public static final int numberWidth = 100;
	public static final int numberHeight = 116;
	public static final int spaceWidth = 50;
	public static final int dotWidth = 50;
	public static final int dotInnerPositionX = 1000;
	public static final int charContraction = 13;
	
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
	
	
	public static final int squareSize = 65;
//	public static int headExtraSize = 0;
//	public static final int bobHeadSize = 135;

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
	public static final int main_logoPositionX = 3;
	public static final int main_logoPositionY = 110;
	
	public static final int main_menuWidth = 648;
	public static final int main_menuHeight = 557;
	public static final int main_menuPositionX = (targetWidth/2) - (main_menuWidth/2);
	public static final int main_menuPositionY = 510;
	
	public static final int main_playButtonWidth = 434;
	public static final int main_playButtonHeight = 146;
	public static final int main_playButtonInnerPositionX = 106;
	public static final int main_playButtonInnerPositionY = 0;
	public static final int main_playButtonPositionX = main_playButtonInnerPositionX + main_menuPositionX;
	public static final int main_playButtonPositionY = main_playButtonInnerPositionY + main_menuPositionY;
	public static final int main_highscoresButtonWidth = main_menuWidth;
	public static final int main_highscoresButtonHeight = 137;
	public static final int main_highscoresButtonInnerPositionX = 0;
	public static final int main_highscoresButtonInnerPositionY = main_playButtonHeight;
	public static final int main_highscoresButtonPositionX = main_highscoresButtonInnerPositionX + main_menuPositionX;
	public static final int main_highscoresButtonPositionY = main_highscoresButtonInnerPositionY + main_menuPositionY;
	public static final int main_achievementsButtonWidth = main_menuWidth;
	public static final int main_achievementsButtonHeight = 141;
	public static final int main_achievementsButtonInnerPositionX = 0;
	public static final int main_achievementsButtonInnerPositionY = main_highscoresButtonHeight + main_highscoresButtonInnerPositionY;
	public static final int main_achievementsButtonPositionX = main_achievementsButtonInnerPositionX + main_menuPositionX;
	public static final int main_achievementsButtonPositionY = main_achievementsButtonInnerPositionY + main_menuPositionY;
	public static final int main_helpButtonWidth = 433;
	public static final int main_helpButtonHeight = 146;
	public static final int main_helpButtonInnerPositionX = 106;
	public static final int main_helpButtonInnerPositionY = main_achievementsButtonHeight + main_achievementsButtonInnerPositionY ;
	public static final int main_helpButtonPositionX = main_helpButtonInnerPositionX + main_menuPositionX;
	public static final int main_helpButtonPositionY = main_helpButtonInnerPositionY + main_menuPositionY;
	
	public static final int main_soundButtonPositionX = 24;
	public static final int main_soundButtonPositionY = targetHeight - (buttonHeight+20);
	public static final int main_controlModeButtonPositionX = targetWidth - (buttonWidth+24);
	public static final int main_controlModeButtonPositionY = main_soundButtonPositionY;

	public static final int main_signInButtonWidth = 218;
	public static final int main_signInButtonHeight =92;
	public static final int main_signInButtonPositionX = (targetWidth/2) - (main_signInButtonWidth/2);
	public static final int main_signInButtonPositionY = targetHeight - main_signInButtonHeight - 50;
	
	//Game screen
	public static final int game_pauseButtonPositionX = 0;
	public static final int game_pauseButtonPositionY =0;
	
	
	
//	public static final int game_2btn_leftButtonPositionX = 36;
//	public static final int game_2btn_leftButtonPositionY = targetHeight - (buttonHeight+60);
//	public static final int game_2btn_rightButtonPositionX = targetWidth-(buttonWidth + 36);
//	public static final int game_2btn_rightButtonPositionY = targetHeight - (buttonHeight+60);
	
	public static final int game_borderThickness = 10;
	public static final int game_borderPositionY =  World.WORLD_HEIGHT * squareSize;
	
	public static final int game_4btn_upButtonPositionX = (targetWidth/2) - (buttonWidth/2);
	public static final int game_4btn_upButtonPositionY = targetHeight - (buttonHeight+buttonHeight+13);
	public static final int game_4btn_leftButtonPositionX = (targetWidth/2) - (buttonWidth + buttonWidth/2);
	public static final int game_4btn_leftButtonPositionY = targetHeight - (buttonHeight + 7 + buttonHeight/2);
	public static final int game_4btn_rightButtonPositionX = (targetWidth/2) + (buttonWidth/2);
	public static final int game_4btn_rightButtonPositionY = game_4btn_leftButtonPositionY;
	public static final int game_4btn_downButtonPositionX = game_4btn_upButtonPositionX;
	public static final int game_4btn_downButtonPositionY = targetHeight - buttonHeight-7;
	
	public static final int game_2btn_leftButtonPositionX = game_4btn_leftButtonPositionX;
	public static final int game_2btn_leftButtonPositionY = game_4btn_leftButtonPositionY;
	public static final int game_2btn_rightButtonPositionX = game_4btn_rightButtonPositionX;
	public static final int game_2btn_rightButtonPositionY = game_4btn_rightButtonPositionY;
	

	
//	public static final int game_4btn_borderPositionY =  game_4btn_upButtonPositionY-(targetHeight - (game_2btn_leftButtonPositionY + buttonHeight));
	
	
	public static final int game_marginX = 5;
	public static final int game_marginY = 0;
	
	public static final float game_scoreScale = 0.7f;
//	public static final int game_scorePositionY = game_2btn_leftButtonPositionY + (int) (numberHeight*game_scoreScale*0.5);
	public static final int game_scorePositionY = 37;
	
	public static final int game_paused_menuWidth = 420;
	public static final int game_paused_menuHeight = 268;
	public static final int game_paused_resumeButtonHeight = 141;
	public static final int game_paused_resumeButtonPositionX = (targetWidth/2) - (game_paused_menuWidth/2);
	public static final int game_paused_resumeButtonPositionY = targetHeight / 5;
	public static final int game_paused_quitButtonInnerPositionY = game_paused_resumeButtonHeight;
	
	public static final int game_ready_width = 640;
	public static final int game_ready_height = 160;
	public static final int game_ready_positionX = (targetWidth/2) - (game_ready_width/2);
	public static final int game_ready_positionY = game_paused_resumeButtonPositionY;
	
	public static final int game_over_width = 372;
	public static final int game_over_height = 280;
	public static final int game_over_positionX = (targetWidth/2) - (game_over_width/2);
	public static final int game_over_positionY = game_paused_resumeButtonPositionY;
	public static final int game_over_cancelButtonPositionX = (targetWidth/2) - (buttonWidth/2);
	public static final int game_over_cancelButtonPositionY = game_paused_resumeButtonPositionY+game_paused_menuHeight+buttonHeight/3;
	
	public static final int game_textSize = 60;
	public static final int game_textStrokeSize = 1;
	public static final int game_textMaxX = targetWidth - 473;
	public static final int game_textMaxY = game_borderPositionY - game_textSize - 7;
	
	
	//Highscore Screen
	public static final int highscores_numberOfHighscores = 5;
	public static final int[] highscoresDefault = new int[] { 50, 40, 25, 15, 5 };
	
	public static final int highscores_headerPositionX = (targetWidth/2) - (main_highscoresButtonWidth/2);
	public static final int highscores_headerPositionY = 67;
	//See main_highscoresButton for header width & height
	
	public static final int highscores_scoreMarginX = (targetWidth/2) - (3*numberWidth);
	public static final int highscores_scoreMarginY = (numberHeight+13); 
	
	public static final int highscores_backPositionX = main_soundButtonPositionX; 
	public static final int highscores_backPositionY = main_soundButtonPositionY; 
	
	//Help screens
	public static final int help_screenWidth = 656;
	public static final int help_screenHeight = 875;
	public static final int help_screenPositionX = (targetWidth/2) - (help_screenWidth/2);
	public static final int help_screenPositionY = 123;
	
	public static final int help_nextButtonPositionX = targetWidth-(buttonWidth + 24);
	public static final int help_nextButtonPositionY = targetHeight - (buttonHeight+40);
	
}
