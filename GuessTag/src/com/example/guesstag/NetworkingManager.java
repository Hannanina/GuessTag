package com.example.guesstag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Implementation of QDNetworkingManager
 * The should be no need to change anything in this file.
 * 
 * NOTE: Remember to setup the permission in the manifest:
 * <uses-permission android:name="android.permission.INTERNET"/>
 * 
 * Calls to server are logged with the tag: EVENT
 * Responses from the server are logged with the tag: EVENT_COMPLETE
 * Errors are logged with the tag: ERROR
 * 
 * @author Peter Börjesson
 *
 */
public class NetworkingManager implements QDNetworkingManager {

	private static int POLL_INTERVAL = 1000;
	
	private final static String BASE_URL = "http://www.yousies.com/ciu196/";
	private final static String ACTION_SAVE = "save.php";
	private final static String ACTION_LOAD = "load.php";
	private final static String ACTION_DELETE = "delete.php";
	private final static String ACTION_MONITOR = "monitor.php";
	private final static String ACTION_IGNORE = "ignore.php";
	private final static String ACTION_POLL = "poll.php";
	private final static String ACTION_LOCK = "lock.php";
	private final static String ACTION_UNLOCK = "unlock.php";
	
	private final static String PARAM_GROUP = "group";
	private final static String PARAM_USER = "user";
	private final static String PARAM_KEY = "key";
	private final static String PARAM_VALUE = "value";
	private final static String PARAM_REQUESTER = "req";
	
	private final static String RESPONSE_RECORDS = "records";
	
	public final static String TAG_ERROR = "ERROR";
	public final static String TAG_EVENT = "EVENT";
	public final static String TAG_EVENT_COMPLETE = "EVENT_COMPLETE";
	
    private String group;
    private String me;
    private boolean isMonitoring;
    private String monitorUser;
    private String monitorKey;
    private NetworkingEventHandler eventHandler;
	
    public NetworkingManager(NetworkingEventHandler eventHandler, String groupId, String userId){
        this.eventHandler = eventHandler;
    	this.group = groupId;
        this.me = userId;
        isMonitoring = false;
    }
    
	@Override
	public void saveValueForKeyOfUser(String key, String user, String value) {
		
		Log.d(TAG_EVENT, "saveValueForKeyOfUser(" + key + ", " + user + ", " + value + ")");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, this.group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, user));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, key));
        nameValuePairs.add(new BasicNameValuePair(PARAM_VALUE, value));
		
        DoPostTask task = new DoPostTask();
        task.execute(new Object[] {ACTION_SAVE, nameValuePairs });  
	}

	@Override
	public void loadValueForKeyOfUser(String key, String user) {
		
		Log.d(TAG_EVENT, "loadValueForKeyOfUser(" + key + ", " + user + ")");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, this.group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, user));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, key));
		
        DoPostTask task = new DoPostTask();
        task.execute(new Object[] {ACTION_LOAD, nameValuePairs });     
	}

	@Override
	public void deleteKeyOfUser(String key, String user) {
		
		Log.d(TAG_EVENT, "deleteKeyOfUser(" + key + ", " + user + ")");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, this.group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, user));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, key));
		
        DoPostTask task = new DoPostTask();
        task.execute(new Object[] {ACTION_DELETE, nameValuePairs });
	}
	
	@Override
	public void monitorKeyOfUser(String key, String user) {
		
		Log.d(TAG_EVENT, "monitorKeyOfUser(" + key + ", " + user + ")");
		
		isMonitoring = true;
        monitorKey = key;
        monitorUser = user;
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, this.group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, user));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, key));
        nameValuePairs.add(new BasicNameValuePair(PARAM_REQUESTER, this.me));
		
        DoPostTask task = new DoPostTask();
        task.execute(new Object[] {ACTION_MONITOR, nameValuePairs });
	    
	    pollKey();
	}

	@Override
	public void ignoreKeyOfUser(String key, String user) {
		
		Log.d(TAG_EVENT, "ignoreKeyOfUser(" + key + ", " + user + ")");
		
		isMonitoring = false;
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, this.group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, user));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, key));
        nameValuePairs.add(new BasicNameValuePair(PARAM_REQUESTER, this.me));
		
        DoPostTask task = new DoPostTask();
        task.execute(new Object[] {ACTION_IGNORE, nameValuePairs });
	}

	@Override
	public void lockKeyOfUser(String key, String user) {
		
		Log.d(TAG_EVENT, "lockKeyOfUser(" + key + ", " + user + ")");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, this.group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, user));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, key));
        nameValuePairs.add(new BasicNameValuePair(PARAM_REQUESTER, this.me));
		
		DoPostTask task = new DoPostTask();
		task.execute(new Object[] {ACTION_LOCK, nameValuePairs });	
	}

	@Override
	public void unlockKeyOfUser(String key, String user) {
		
		Log.d(TAG_EVENT, "unlockKeyOfUser(" + key + ", " + user + ")");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, this.group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, user));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, key));
        nameValuePairs.add(new BasicNameValuePair(PARAM_REQUESTER, this.me));
		
		DoPostTask task = new DoPostTask();
		task.execute(new Object[] {ACTION_UNLOCK, nameValuePairs });
	}
	
	private void pollKey(){
		
		Log.d(TAG_EVENT, "pollKeyOfUser(" + monitorKey + ", " + monitorUser + ")");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(PARAM_GROUP, group));
        nameValuePairs.add(new BasicNameValuePair(PARAM_USER, monitorUser));
        nameValuePairs.add(new BasicNameValuePair(PARAM_KEY, monitorKey));
        nameValuePairs.add(new BasicNameValuePair(PARAM_REQUESTER, me));
		
		DoPostTask task = new DoPostTask();
		task.execute(new Object[] {ACTION_POLL, nameValuePairs });	
	}
	
	private void alertEventHander(String action, JSONObject json, String key, String user){
		
		if (action.equals(ACTION_POLL)){
			
			JSONArray records;
			
			try {
				records = json.getJSONArray(RESPONSE_RECORDS);
				
				if(records != null && !records.isNull(0)){
					
					Log.d(TAG_EVENT_COMPLETE, "valueChangedForKeyOfUser(" + key + ", " +  user + "), sending info to handler");				
					eventHandler.valueChangedForKeyOfUser(json, key, user);					
				}
				
				else if(records != null && records.isNull(0)){
					Log.d(TAG_EVENT_COMPLETE, "valueDidNotChangeForKeyOfUser(" + key + ", " +  user + "), polling again");
				}
				
				if (isMonitoring) {
					pollKey();
				}
				
			} catch (JSONException e) {
				Log.e(TAG_ERROR, e.getMessage());
			}
			
		}
		else if(action.equals(ACTION_SAVE)) {
			Log.d(TAG_EVENT_COMPLETE, "savedValueForKeyOfUser(" + key + ", " +  user + "), sending info to handler");
			eventHandler.savedValueForKeyOfUser(json, key, user);
		}
		else if(action.equals(ACTION_LOAD)) {
			Log.d(TAG_EVENT_COMPLETE, "loadedValueForKeyOfUser(" + key + ", " +  user + "), sending info to handler");
			eventHandler.loadedValueForKeyOfUser(json, key, user);
		}
		else if(action.equals(ACTION_DELETE)) {
			Log.d(TAG_EVENT_COMPLETE, "deletedKeyOfUser(" + key + ", " +  user + "), sending info to handler");
			eventHandler.deletedKeyOfUser(json, key, user);
		}
		else if(action.equals(ACTION_MONITOR)) {
			Log.d(TAG_EVENT_COMPLETE, "monitoringKeyOfUser(" + key + ", " +  user + "), sending info to handler");
			eventHandler.monitoringKeyOfUser(json, key, user);
		}
		else if(action.equals(ACTION_IGNORE)) {
			Log.d(TAG_EVENT_COMPLETE, "ignoringKeyOfUser(" + key + ", " +  user + "), sending info to handler");
			eventHandler.ignoringKeyOfUser(json, key, user);
		}
		else if(action.equals(ACTION_LOCK)) {
			Log.d(TAG_EVENT_COMPLETE, "lockedKeyOfUser(" + key + ", " +  user + "), sending info to handler");
			eventHandler.lockedKeyofUser(json, key, user);
		}
		else if(action.equals(ACTION_UNLOCK)) {
			Log.d(TAG_EVENT_COMPLETE, "unlockedKeyOfUser(" + key + ", " +  user + "), sending info to handler");
			eventHandler.unlockedKeyOfUser(json, key, user);
		}
	}
	
	private class DoPostTask extends AsyncTask<Object,Void,JSONObject>{

		private String action;
		private String userParam;
		private String keyParam;
		
	    @SuppressWarnings("unchecked")
		@Override
	    protected JSONObject doInBackground(Object... params) {
	    
	    	//Extract action from first parameter
	    	action = (String) params[0];
	    	
	    	//Create the parameters from the second parameter
	    	List<NameValuePair> nameValuePairs = (List<NameValuePair>) params[1];
	    	
	    	// Create a new HttpClient and Post Header
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost(BASE_URL + action);
		    
		    //Create a Handler to Manage Responses
		    ResponseHandler <String> responseHandler = new BasicResponseHandler(); 
		    
		    for(NameValuePair pair: nameValuePairs){
		    	if(pair.getName().equals(PARAM_USER)) userParam = pair.getValue();
		    	if(pair.getName().equals(PARAM_KEY)) keyParam = pair.getValue();
		    }
		    
		    JSONObject json = new JSONObject();

		    try {

		    	//Pause thread if polling
		    	if(action.equals(ACTION_POLL)){
		    		Thread.sleep(POLL_INTERVAL);
		    	}
		    	
		    	//Set Parameters
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        String response = httpclient.execute(httppost, responseHandler);
		        
		        //Construct JSON
		        json = new JSONObject(response);
		        
		    } catch (ClientProtocolException e) {
		        Log.e(TAG_ERROR, e.getMessage());
		    } catch (IOException e) {
		    	Log.e(TAG_ERROR, e.getMessage());
		    } catch (JSONException e) {
		    	Log.e(TAG_ERROR, e.getMessage());
			} catch (InterruptedException e) {
				Log.e(TAG_ERROR, e.getMessage());
			}
	    	return json;  	
	    }
	    
	    @Override
	    protected void onPostExecute(JSONObject json) {   	
	    	alertEventHander(action, json, keyParam, userParam);
	    }
	}
}
