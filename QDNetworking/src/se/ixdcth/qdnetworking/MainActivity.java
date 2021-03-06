package se.ixdcth.qdnetworking;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Example activity implementing NetworkingEventHandler and its methods.
 * Test the communication with the server.
<<<<<<< Updated upstream
 * @author Peter 
=======
 * @author Peter
>>>>>>> Stashed changes
 *
 */
public class MainActivity extends ActionBarActivity implements NetworkingEventHandler{

	private NetworkingManager manager;
	private EditText msgField;
	private TextView serverMsg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		manager = new NetworkingManager(this, "group", "me");
		msgField = (EditText) findViewById(R.id.sendMsgEditText);
		serverMsg = (TextView) findViewById(R.id.receiveMsgTextView);
		
		manager.monitorKeyOfUser("key", "user");
		
		//Below are other possible methods calls that can be used for testing and monitoring logs.
		/*
		manager.saveValueForKeyOfUser("key", "user", "value1");
		manager.loadValueForKeyOfUser("key", "user");
		manager.monitorKeyOfUser("key", "user");
		manager.saveValueForKeyOfUser("key", "user", "value2"); 
		manager.deleteKeyOfUser("key", "user");
		manager.loadValueForKeyOfUser("key", "user");
		manager.saveValueForKeyOfUser("key", "user", "value3");
		manager.lockKeyOfUser("key", "user");
		manager.saveValueForKeyOfUser("key", "user", "value4");
		manager.unlockKeyOfUser("key", "user");
		manager.saveValueForKeyOfUser("key", "user", "value5");
		manager.ignoreKeyOfUser("key", "user");
		*/
	}
	
	public void sendMsg(View view){
		if(view.getId() == R.id.sendMsgButton && msgField != null && 
				msgField.getText() != null && !msgField.getText().equals("")){
			
			manager.saveValueForKeyOfUser("key", "user", msgField.getText().toString());
		}
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		
		manager.ignoreKeyOfUser("key", "user");
	}

	@Override
	public void savedValueForKeyOfUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
	}
	
	@Override
	public void loadedValueForKeyOfUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
	}
	
	@Override
	public void deletedKeyOfUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
	}
	
	@Override
	public void monitoringKeyOfUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
	}
	
	@Override
	public void ignoringKeyOfUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
	}
	
	@Override
	public void lockedKeyofUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
	}
	
	@Override
	public void unlockedKeyOfUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
	}

	@Override
	public void valueChangedForKeyOfUser(JSONObject json, String key, String user) {
		//TODO Do something with returned values?
		
		Log.d(NetworkingManager.TAG_EVENT_COMPLETE, "JSONOBject retreived in method valueChanged + " +
				"forKeyOfUser: " +  json.toString());
		try {
			serverMsg.setText(json.getJSONArray("records").getJSONObject(0).getString("value"));
		} catch (JSONException e) {
			Log.e(NetworkingManager.TAG_ERROR, e.getMessage());
		}
	}
}
