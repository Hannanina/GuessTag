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
	
	InstaAPIManager im =  InstaAPIManager.getInstaAPIManager();
	SessionManager sm =  SessionManager.getSessionManager();

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.activity_guess_tag);
		one = (ImageView)findViewById(R.id.image_one);
		two = (ImageView)findViewById(R.id.image_two);
		three = (ImageView)findViewById(R.id.image_three);
		four = (ImageView)findViewById(R.id.image_four);
		five = (ImageView)findViewById(R.id.image_five);
		six = (ImageView)findViewById(R.id.image_six);
		//TextView wrongAnswer = (TextView) findViewById(R.id.wrong_answer);
		//wrongAnswer.setVisibility(View.INVISIBLE);
		
		new Thread() {
			public void run() {
				im.initiateConnection();
				
			}
		}.start();

		one.setImageBitmap(im.getBitmap());
		two.setImageBitmap(im.getBitmap());
		three.setImageBitmap(im.getBitmap());
		four.setImageBitmap(im.getBitmap());
		five.setImageBitmap(im.getBitmap());
		six.setImageBitmap(im.getBitmap());

		TextView wrongAnswer = (TextView) findViewById(R.id.wrong_answer);
		wrongAnswer.setVisibility(View.INVISIBLE);
		
//		pd = new ProgressDialog(this);
//	    pd.setMessage("Loading...");
//	    new TheTask().execute();
//	    
	}
	
	public void onClickSubmit (View view){
		
		EditText guessEdit = (EditText)findViewById(R.id.input_guess);
 //     sm.checkGuessTag(guessEdit.getText().toString());
//		sm.setTagName(guessEdit.getText().toString());
		
		if(guessEdit.getText().toString().equals(sm.getTagName())){
			 sm.checkGuessTag(guessEdit.getText().toString());
			TextView wrongAnswer = (TextView) findViewById(R.id.wrong_answer);
			wrongAnswer.setVisibility(View.INVISIBLE);
			
			Intent intent = new Intent(this, ResultFinalActivity.class); 
			startActivity(intent);
		}
		else{
			TextView wrongAnswer = (TextView) findViewById(R.id.wrong_answer);
			wrongAnswer.setVisibility(View.VISIBLE);	
			
			//Intent intent = new Intent(this, NewActivity.class); 
			//startActivity(intent);
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


	
//	class TheTask extends AsyncTask<Void,Void,Void> {
//
//		@Override
//		protected void onPreExecute() {
//			// TODO Auto-generated method stub
//			super.onPreExecute();
//			pd.show();
//		}
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			// TODO Auto-generated method stub
//			try
//			{
//				imageOne = downloadBitmap("http://www.dailygarnish.com/wp-content/uploads/2012/04/IMG_20120414_205845-640x640.jpg");
//				imageTwo = downloadBitmap("http://cdn.onegreenplanet.org/wp-content/uploads/2010/10//2013/12/nelly-the-bc-PB-640x640.jpg");
//				imageThree = downloadBitmap("http://forums.auscelebs.net/acnet-images/86206/kylie-gillies-630803.jpg");
//				imageFour = downloadBitmap("http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10424508_632154030205948_1786575134_n.jpg");
//				imageFive = downloadBitmap("http://designyoutrust.com/wp-content/uploads/2014/08/1391450925_1-640x640.jpg");
//				imageSix = downloadBitmap("http://illpumpyouup.com/images/640x640-banner-2.jpg");
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			pd.dismiss();
//			
//			if(imageOne!=null)
//			{
//				one.setImageBitmap(imageOne);
//				two.setImageBitmap(imageTwo);
//				three.setImageBitmap(imageThree);
//				four.setImageBitmap(imageFour);
//				five.setImageBitmap(imageFive);
//				six.setImageBitmap(imageSix);
//			}
//		}   
//	}
//
//	private Bitmap downloadBitmap(String url) {
//
//		final DefaultHttpClient client = new DefaultHttpClient();
//
//		//forming a HttoGet request 
//		final HttpGet getRequest = new HttpGet(url);
//		try {
//			HttpResponse response = client.execute(getRequest);
//
//			//check 200 OK for success
//			final int statusCode = response.getStatusLine().getStatusCode();
//
//			if (statusCode != HttpStatus.SC_OK) {
//				Log.w("ImageDownloader", "Error " + statusCode + 
//                " while retrieving bitmap from " + url);
//				return null;
//
//			}
//
//			final HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				InputStream inputStream = null;
//				try {
//					// getting contents from the stream 
//					inputStream = entity.getContent();
//
//					// decoding stream data back into image Bitmap that android understands
//					BitmapImage = BitmapFactory.decodeStream(inputStream);
//
//
//				}
//				finally {
//					if (inputStream != null) {
//						inputStream.close();
//					}
//					entity.consumeContent();
//				}
//			}
//		} catch (Exception e) {
//			// You Could provide a more explicit error message for IOException
//			getRequest.abort();
//			Log.e("ImageDownloader", "Something went wrong while" + " retrieving bitmap from " + url + e.toString());
//		} 
//		return BitmapImage;
// 		}
	}
