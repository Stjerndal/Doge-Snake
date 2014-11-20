package com.stjerndal.androidgames;

import android.graphics.Typeface;

import com.stjerndal.androidgames.framework.Music;
import com.stjerndal.androidgames.framework.Pixmap;
import com.stjerndal.androidgames.framework.Sound;

public class Assets {
	public static Pixmap background;
	public static Pixmap logo;
	public static Pixmap mainMenu;
	public static Pixmap buttons;
	public static Pixmap help1;
	public static Pixmap help2;
	public static Pixmap help3;
	public static Pixmap numbers;
	public static Pixmap ready;
	public static Pixmap pause;
	public static Pixmap gameOver;
	public static Pixmap head;
	public static Pixmap headUp;
	public static Pixmap headLeft;
	public static Pixmap headDown;
	public static Pixmap headRight;
	public static Pixmap tail;
	public static Pixmap meatball1;
	public static Pixmap meatball2;
	public static Pixmap meatball3;
	public static Pixmap signInGplus;
	public static Pixmap signOut;
	
	public static Sound click;
	public static Sound eat;
	public static Sound bitten;
	
	public static Music music;
	
	public static Typeface fontComicSans;
	
	public static void dispose() {
		background.dispose();
		logo.dispose();
		mainMenu.dispose();
		buttons.dispose();
		help1.dispose();
		help2.dispose();
		help3.dispose();
		numbers.dispose();
		ready.dispose();
		pause.dispose();
		gameOver.dispose();
		head.dispose();
		headUp.dispose();
		headLeft.dispose();
		headDown.dispose();
		headRight.dispose();
		tail.dispose();
		meatball1.dispose();
		meatball2.dispose();
		meatball3.dispose();
		signInGplus.dispose();
		signOut.dispose();
		
		click.dispose();
		eat.dispose();
		bitten.dispose();
		
		music.dispose();
	}
}
