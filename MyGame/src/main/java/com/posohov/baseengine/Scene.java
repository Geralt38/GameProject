package com.posohov.baseengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.posohov.quoridor.Prefabs;
import com.posohov.sidescroller.Components.GameControllerComponent;
import com.posohov.baseengine.Components.Transform;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Scene extends View {

    public static final int TOP_BAR_HEIGHT = 50;

    private TouchInfo touchInfo = new TouchInfo();

    private final Paint mPainter = new Paint();
    private ScheduledFuture<?> mMoverFuture;

    private RelativeLayout mFrame;

    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();

    private Camera camera;

    public Scene(Context context, RelativeLayout frame) {
        super(context);

        mFrame = frame;

        mPainter.setAntiAlias(true);

    }

    public void launch() {
        camera = new Camera(900, mFrame.getWidth(), mFrame.getHeight());
        Log.d("cam h", Integer.toString(mFrame.getHeight()));
        Log.d("cam w", Integer.toString(mFrame.getWidth()));
        setupObjects();
        startScene();
        startLoop();
    }

    public GameControllerComponent getGameController() {
        GameObject gc = getObjectByTag("gamecontroller");
        if (gc != null) {
            GameControllerComponent gcc = (GameControllerComponent) gc.getComponent(GameControllerComponent.class);
            if (gcc != null) {
                return gcc;
            }
        }
        return null;
    }

    public Camera getCamera() {return camera;}

    public float getCameraWidth() {return camera.getWidth();}

    public float getCameraHeight() {
        return camera.getHeight();
    }

    public Paint getPaint() {
        return mPainter;
    }

    public void restart() {

        if (null != mMoverFuture && !mMoverFuture.isDone()) {
            mMoverFuture.cancel(true);
        }

        mFrame.post(new Runnable() {
            @Override
            public void run() {

                ((GameActivity)getContext()).restart();
                mFrame.removeView(Scene.this);

            }
        });
    }

    public GameObject getObjectByTag(String tag) {
        for (GameObject object: objects) {
            if (object.tag.equals(tag)) {
                return object;
            }
        }
        return null;
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void addObjectDuringRuntime(GameObject object) {
        addObject(object);
        object.start();
    }

    public void deleteObject(GameObject object) {
        objects.remove(object);
    }

    protected void setupObjects() {

    }

    private void startScene() {
        for (GameObject object : objects) {
            object.start();
        }
    }

    private void updateScene() {
        for (GameObject object : objects) {
            object.update();
        }
    }

    private void detectCollisions() {
        for (int i = 0; i < objects.size() - 1; i++) {
            Transform t1 = objects.get(i).transform;
            if (t1 != null) {
                for (int j = i+1; j < objects.size(); j++) {
                    Transform t2 = objects.get(j).transform;
                    if (t2 != null) {
                        if ((t1.x < t2.x + t2.width) && (t1.x + t1.width > t2.x) && (t1.y < t2.y + t2.height) && (t1.y + t1.height > t2.y)){
                            t1.object.onCollision(t2.object);
                            t2.object.onCollision(t1.object);
                        }
                    }
                }
            }
        }
    }

    private void startLoop() {

        ScheduledExecutorService executor = Executors
                .newScheduledThreadPool(1);


        mMoverFuture = executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

                updateScene();
                detectCollisions();
                touchInfo.newTouch = false;
                Scene.this.postInvalidate();


            }
        }, 0, GameActivity.REFRESH_RATE, TimeUnit.MILLISECONDS);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        canvas.save();

        for (GameObject object : objects) {
            object.draw(canvas, camera);
        }

        canvas.restore();


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchInfo.touched = true;
                touchInfo.x = x;
                touchInfo.y = y;
                touchInfo.newTouch = true;
                break;
            case MotionEvent.ACTION_MOVE:
                touchInfo.x = x;
                touchInfo.y = y;
                break;
            case MotionEvent.ACTION_UP:
                touchInfo.touched = false;
        }

        return true;
    }

    public TouchInfo getTouchInfo() {return touchInfo;}

    public class TouchInfo {
        public float x,y;
        public boolean touched = false;
        public boolean newTouch = false;
    }
}
