package com.stjerndal.androidgames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.stjerndal.androidgames.framework.FileIO;

public class Settings {
	public static final int nOControlModes = 2;
	public static final int TWO_BUTTONS_MODE = 0;
	public static final int FOUR_BUTTONS_MODE = 1;
	
	public static boolean soundEnabled = true;
	public static int[] highscores = V.highscoresDefault;
	public static int controlMode = FOUR_BUTTONS_MODE;
	public static int totalPlayedGames = 0;
	public static int totalMeatballs = 0;
	public static int totalGameLaunches = 0;

	// TODO Is highscore final now?

	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
//			if (V.debugging) {
//				V.log("LOADING... Before:");
//				V.log("Sound: " + soundEnabled);
//				V.log("Dog: " + dogMode);
//				V.log("Highscores: " + highscores[0] + ", " + highscores[1] + ", " + highscores[2]);
//			}
			
			in = new BufferedReader(new InputStreamReader(
					files.readFile(V.extFileName)));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			controlMode = Integer.parseInt(in.readLine());
			for (int i = 0; i < V.highscores_numberOfHighscores; i++) {
				highscores[i] = Integer.parseInt(in.readLine());
			}
			totalPlayedGames = Integer.parseInt(in.readLine());
			totalMeatballs = Integer.parseInt(in.readLine());
			totalGameLaunches = Integer.parseInt(in.readLine());
		} catch (IOException e) {
//			V.log("Failed to load file (IO)");
			// :( It's ok we have defaults
		} catch (NumberFormatException e) {
//			V.log("Failed to load file (NumberFormat)");
			// :/ It's ok, defaults save our day
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
		
//		if (V.debugging) {
//			V.log("... After:");
//			V.log("Sound: " + soundEnabled);
//			V.log("Dog: " + dogMode);
//			V.log("Highscores: " + highscores[0] + ", " + highscores[1] + ", " + highscores[2]);
//		}
		
	}

	public static void save(FileIO files) {
		BufferedWriter out = null;
		try {
//			if(V.debugging) V.log("Saving");
			out = new BufferedWriter(new OutputStreamWriter(
					files.writeFile(V.extFileName)));
			out.write(Boolean.toString(soundEnabled));
			out.write("\n");
			out.write(Integer.toString(controlMode));
			for (int i = 0; i < V.highscores_numberOfHighscores; i++) {
				out.write("\n");
				out.write(Integer.toString(highscores[i]));
			}
			out.write("\n");
			if(V.debugging) V.log("Played Games: " + totalPlayedGames);
			out.write(Integer.toString(totalPlayedGames));
			out.write("\n");
			if(V.debugging) V.log("Meatballs: " + totalMeatballs);
			out.write(Integer.toString(totalMeatballs));
			out.write("\n");
			if(V.debugging) V.log("Game Launces: " + totalGameLaunches);
			out.write(Integer.toString(totalGameLaunches));
			

		} catch (IOException e) {
//			V.log("Failed to write to file");
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
//				V.log("Failed to close file after saving");
			}
		}
	}

	public static void addScore(int score) {
		for (int i = 0; i < V.highscores_numberOfHighscores; i++) {
			if (highscores[i] < score) {
				for (int j = V.highscores_numberOfHighscores - 1; j > i; j--)
					highscores[j] = highscores[j - 1];
				highscores[i] = score;
				break;
			}
		}
	}


	public static void switchControlMode() {
		controlMode++;
		if(controlMode >= nOControlModes) {
			controlMode = 0;
		}
	}

}
