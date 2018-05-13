package com.posohov.baseengine;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.posohov.sidescroller.scenes.GameScene;

import course.labs.graphicslab.R;

public class GameActivity extends Activity {


	private static final String TAG = "Lab-Graphics";

	private RelativeLayout mFrame;

	//private GestureDetector mGestureDetector;
	Scene scene;

	public static final int REFRESH_RATE = 40;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		// Установка пользовательского интерфейса
		mFrame = (RelativeLayout) findViewById(R.id.frame);

		startScene();
	}

	public void restart() {
        startScene();
    }

    private void startScene() {
		scene = new GameScene(mFrame.getContext(), mFrame);
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


}