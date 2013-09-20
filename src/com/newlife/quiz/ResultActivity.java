package com.newlife.quiz;

import org.w3c.dom.Text;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ResultActivity extends Activity {

	TextView title, r1, r2, r3, r4, r5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		title = (TextView) findViewById(R.id.title);
		r1 = (TextView) findViewById(R.id.rOne);
		r2 = (TextView) findViewById(R.id.rTwo);
		r3 = (TextView) findViewById(R.id.rThree);
		r4 = (TextView) findViewById(R.id.rFour);
		r5 = (TextView) findViewById(R.id.rFive);

		int[] order = getIntent().getExtras().getIntArray("order");

		if (getIntent().getExtras() != null) {
			r1.setText(getAnimalName(order[0]));

			r2.setText(getAnimalName(order[1]));

			r3.setText(getAnimalName(order[2]));

			r4.setText(getAnimalName(order[3]));

			r5.setText(getAnimalName(order[4]));
			Log.d("order", order[0] + " " + order[1] + " " + order[2] + " "
					+ order[3] + " " + order[4]);
		}
	}

	public String getAnimalName(int n) {
		switch (n) {
		case 1:
			return "خنزير : يمثل اهتمامك بالمال";
		case 2:
			return "بقرة  : يمثل اهتمامك بالمستقبل المهني";
		case 3:
			return "حصان  : يمثل اهتمامك بالعائلة";
		case 4:
			return "خاروف  : يمثل اهتمامك بالحب";
		case 5:
			return "نمر  : يمثل اهتمامك بعزة نفسك";
		default:
			return "";
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
