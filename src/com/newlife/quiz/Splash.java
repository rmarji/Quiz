package com.newlife.quiz;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class Splash extends Activity {

	/** Called when the activity is first created. */

	ViewGroup picCon;
	Button btn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		btn = (Button) findViewById(R.id.button1);
		picCon = (ViewGroup) findViewById(R.id.picCon);
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int[] order = { 0, 0, 0, 0, 0 };
				order[0] = getorder(R.id.one);
				order[1] = getorder(R.id.two);
				order[2] = getorder(R.id.three);
				order[3] = getorder(R.id.four);
				order[4] = getorder(R.id.five);

				Log.d("order",order[0] + " " + order[1] + " " + order[2] + " " + order[3] + " " + order[4]);
				Intent i = new Intent(getApplicationContext(), ResultActivity.class);
				i.putExtra("order", order);
				startActivity(i);

			}

			public int getorder(int vg) {
				ViewGroup vgs = (ViewGroup) findViewById(vg);
				int idOfView = vgs.getChildAt(0).getId();

				int num = 0;
				switch (idOfView) {
				case R.id.pig:
					num = 1;
					break;
				case R.id.cow:
					num = 2;
					break;
				case R.id.dog:
					num = 3;
					break;
				case R.id.sheep:
					num = 4;
					break;
				case R.id.tiger:
					num = 5;
					break;
				}
				return num;
			}
		});

		findViewById(R.id.sheep).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.cow).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.dog).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.pig).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.tiger).setOnTouchListener(new MyTouchListener());

		findViewById(R.id.one).setOnDragListener(new MyDragListener());
		findViewById(R.id.two).setOnDragListener(new MyDragListener());
		findViewById(R.id.three).setOnDragListener(new MyDragListener());
		findViewById(R.id.four).setOnDragListener(new MyDragListener());
		findViewById(R.id.five).setOnDragListener(new MyDragListener());
		findViewById(R.id.main).setOnDragListener(new MyDragListener());

	}

	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
				view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);
				return true;
			} else {
				return false;
			}
		}
	}

	class MyDragListener implements OnDragListener {
		Drawable enterShape = getResources().getDrawable(
				R.drawable.shape_droptarget);
		Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// Do nothing
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				if (v.getId() != R.id.main) {
				v.setBackgroundDrawable(enterShape);
				}
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				v.setBackgroundDrawable(normalShape);
				break;
			case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup
				View view = (View) event.getLocalState();
				if (v.getId() != R.id.main) {
					ViewGroup owner = (ViewGroup) view.getParent();
					owner.removeView(view);
					ViewGroup container = (ViewGroup) v;

					View current = new View(getApplicationContext());
					if (container.getChildCount() > 0) {
						current = container.getChildAt(0);
						container.removeView(current);
						owner.addView(current);

					}
					view.setMinimumHeight(container.getHeight());
					view.setMinimumWidth(container.getWidth());
					container.addView(view);
					
					if (picCon.getChildCount() == 0)
						{
							btn.setVisibility(0);
							btn.animate().alpha(0);
							btn.animate().alphaBy(1).setDuration(1500);
						}
					
				}
				view.setVisibility(View.VISIBLE);

				break;
			case DragEvent.ACTION_DRAG_ENDED:
				v.setBackgroundDrawable(normalShape);
			default:
				break;
			}
			return true;
		}
	}
}