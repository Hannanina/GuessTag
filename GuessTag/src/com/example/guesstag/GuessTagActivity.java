package com.example.guesstag;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * This class is invoked during a game session, when players try to guess the
 * correct hashtag by submitting their guessing words. Images are also displayed
 * here for players who suppose to guess.
 * 
 * @author group 6
 * 
 */

public class GuessTagActivity extends Activity {

	URL url;
	Bitmap BitmapImage;
	Bitmap imageOne;
	Bitmap imageTwo;
	Bitmap imageThree;
	Bitmap imageFour;
	Bitmap imageFive;
	Bitmap imageSix;
	ProgressDialog pd;
	ImageView one;
	ImageView two;
	ImageView three;
	ImageView four;
	ImageView five;
	ImageView six;

	InstaAPIManager im = InstaAPIManager.getInstaAPIManager();
	SessionManager sm = SessionManager.getSessionManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_guess_tag);
		one = (ImageView) findViewById(R.id.image_one);
		two = (ImageView) findViewById(R.id.image_two);
		three = (ImageView) findViewById(R.id.image_three);
		four = (ImageView) findViewById(R.id.image_four);
		five = (ImageView) findViewById(R.id.image_five);
		six = (ImageView) findViewById(R.id.image_six);
	

		new Thread() {
			public void run() {
				im.initiateConnection();
				System.out
						.println("GUESSTAGACT:  " + im.getBitmap().toString());
				imageOne = im.getBitmap().get(0);
				imageTwo = im.getBitmap().get(1);

			}
		}.start();

		System.out.println("GUESSTAGACT IMAGE ONE:  " + imageOne.toString());
		one.setImageBitmap(imageOne);
		two.setImageBitmap(imageTwo);
		// three.setImageBitmap(im.getBitmap().get(2));
		// four.setImageBitmap(im.getBitmap().get(3));
		// five.setImageBitmap(im.getBitmap().get(4));
		// six.setImageBitmap(im.getBitmap().get(5));

		TextView wrongAnswer = (TextView) findViewById(R.id.wrong_answer);
		wrongAnswer.setVisibility(View.INVISIBLE);

		
	}

	public void onClickSubmit(View view) {

		EditText guessEdit = (EditText) findViewById(R.id.input_guess);
	

		if (guessEdit.getText().toString().equals("snow")) {
			sm.checkGuessTag(guessEdit.getText().toString());
			TextView wrongAnswer = (TextView) findViewById(R.id.wrong_answer);
			wrongAnswer.setVisibility(View.INVISIBLE);

			Intent intent = new Intent(this, ResultFinalActivity.class);
			startActivity(intent);
		} else {
			TextView wrongAnswer = (TextView) findViewById(R.id.wrong_answer);
			wrongAnswer.setVisibility(View.VISIBLE);

		
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guess_tag, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
