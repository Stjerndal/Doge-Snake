package com.stjerndal.androidgames;

import android.graphics.Typeface;

import com.stjerndal.androidgames.framework.Game;
import com.stjerndal.androidgames.framework.Graphics;
import com.stjerndal.androidgames.framework.Graphics.PixmapFormat;
import com.stjerndal.androidgames.framework.Screen;
import com.stjerndal.androidgames.framework.impl.AndroidGame;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
//		V.exitOnBack = true;
	}

	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Settings.load(game.getFileIO());
		Settings.totalGameLaunches++;
		if(Settings.totalGameLaunches > 3) {
			Ads.prepareInterstitialDispOnLoad(((AndroidGameWithAds) game));
			Ads.loadInterstitialDispOnLoad(((AndroidGameWithAds) game));
		}
		
		Assets.background = g.newPixmap("background.jpg", PixmapFormat.RGB565);
		Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
		Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
		Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
		Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
		loadDog(game);
		Assets.meatball1 = g.newPixmap("meatball1.png", PixmapFormat.ARGB4444);
		Assets.meatball2 = g.newPixmap("meatball2.png", PixmapFormat.ARGB4444);
		Assets.meatball3 = g.newPixmap("meatball3.png", PixmapFormat.ARGB4444);
		Assets.signInGplus = g.newPixmap("signInGplus.png", PixmapFormat.ARGB4444);
		Assets.signOut = g.newPixmap("signOut.png", PixmapFormat.ARGB4444);
		Assets.click = game.getAudio().newSound("click.ogg");
		Assets.eat = game.getAudio().newSound("eat2.ogg");
		Assets.bitten = game.getAudio().newSound("bitten.ogg");
		Assets.music = game.getAudio().newMusic("music.ogg");
		
		Assets.fontComicSans = Typeface.createFromAsset(((AndroidGame)game).getAssets(), "comicSans.ttf");
		
		game.setScreen(new MainMenuScreen(game));
		
	}
	
	public static void loadDog(Game game) {
		Graphics g = game.getGraphics();
//		switch (Settings.controlMode) {
//		case Settings.TWO_BUTTONS_MODE:
//			Assets.head = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
//			Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
//			Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
//			Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
//			Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
//			Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
//			break;
//		case Settings.FOUR_BUTTONS_MODE:
			Assets.head = g.newPixmap("dogeHeadUp.png", PixmapFormat.ARGB4444);
			Assets.headUp = g.newPixmap("dogeHeadUp.png", PixmapFormat.ARGB4444);
			Assets.headLeft = g.newPixmap("dogeHeadLeft.png",PixmapFormat.ARGB4444);
			Assets.headDown = g.newPixmap("dogeHeadDown.png",PixmapFormat.ARGB4444);
			Assets.headRight = g.newPixmap("dogeHeadRight.png",	PixmapFormat.ARGB4444);
//			Assets.tail = g.newPixmap("dogeTail.png", PixmapFormat.ARGB4444);
			Assets.tail = g.newPixmap("dogeTail.jpg", PixmapFormat.RGB565);
//			break;
//		}
	}

	public void present(float deltaTime) {

	}

	public void pause() {

	}

	public void resume() {

	}

	public void dispose() {

	}
}
