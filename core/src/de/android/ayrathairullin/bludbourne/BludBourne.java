package de.android.ayrathairullin.bludbourne;

import com.badlogic.gdx.Game;

import de.android.ayrathairullin.bludbourne.screens.MainGameScreen;

public class BludBourne extends Game {
	public static final MainGameScreen mainGameScreen = new MainGameScreen();

	@Override
	public void create() {
		setScreen(mainGameScreen);
	}

	@Override
	public void dispose() {
		mainGameScreen.dispose();
	}
}
