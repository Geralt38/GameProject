package com.posohov.baseengine;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.posohov.quoridor.scenes.GameScene;
import com.posohov.quoridor.scenes.MenuScene;

import course.labs.graphicslab.R;

public class GameActivity extends Activity {


	private static final String TAG = "Lab-Graphics";

	private RelativeLayout mFrame;

	//private GestureDetector mGestureDetector;
	Scene scene;

	public static final int REFRESH_RATE = 40;

	private SceneType currentSceneType;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		// Установка пользовательского интерфейса
		mFrame = (RelativeLayout) findViewById(R.id.frame);

		startScene(SceneType.MENU);
	}

	public void restart() {
        startScene(currentSceneType);
    }

    public void startScene(SceneType sceneType) {
		switch (sceneType) {
			default:
			case MENU:
				scene = new MenuScene(mFrame.getContext(), mFrame);
				break;
			case PVP:
				scene = new GameScene(mFrame.getContext(), mFrame, GameScene.GameType.PVP);
				break;
			case PVAI:
				scene = new GameScene(mFrame.getContext(), mFrame, GameScene.GameType.PVAI);
				break;
		}

		mFrame.addView(scene);

		mFrame.post(new Runnable() {
			@Override
			public void run() {
				scene.launch();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		//setupGestureDetector();
	}

	/*private void setupGestureDetector() {

		mGestureDetector = new GestureDetector(this,

				new GestureDetector.SimpleOnGestureListener() {

					@Override
					public boolean onDown(MotionEvent e) {
						scene.onDown(e);
						return true;
					}
				});
	}*/

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		//mGestureDetector.onTouchEvent(event);
		return false;

	}

	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		scene.onBackPressed();
	}

	public enum SceneType {MENU, PVP, PVAI}
}