package se.ixdcth.qdnetworking;

import org.json.JSONObject;

/**
 * This interface contains callback methods for the QDNetworking manager.
 * The class initializing the QDNetworking manager must implement this interface
 * and take care of the callback's.
 * @author Peter Börjesson
 *
 */
public interface NetworkingEventHandler {

	/**
	 * Callback method for server response from saveValueForKeyOfUser.
	 * @param json
	 * @param key
	 * @param user
	 */
	public void savedValueForKeyOfUser(JSONObject json, String key, String user);
	
	/**
	 * Callback method for server response from loadValueForKeyOfUser.
	 * @param json
	 * @param key
	 * @param user
	 */
	public void loadedValueForKeyOfUser(JSONObject json, String key, String user);
	
	/**
	 * Callback method for server response from deleteKeyOfUser.
	 * @param json
	 * @param key
	 * @param user
	 */
	public void deletedKeyOfUser(JSONObject json, String key, String user);
	
	/**
	 * Callback method for server response from monitorKeyOfUser.
	 * This should inform you whether the setup of the monitoring was successful.
	 * Any updated values will be communicated by callback to the method 
	 * valueChangedForKeyOfUser.
	 * @param json
	 * @param key
	 * @param user
	 */
	public void monitoringKeyOfUser(JSONObject json, String key, String user);

	/**
	 * Callback method for server response from ignoreKeyOfUser. 
	 * @param json
	 * @param key
	 * @param user
	 */
	public void ignoringKeyOfUser(JSONObject json, String key, String user);
	
	/**
	 * Callback method from the server when a value has changed for a given
	 * key of a user.Note that this method will be continuously called when 
	 * the value is changed under until entry is ignored.
	 * @param json
	 * @param key
	 * @param user
	 */
	public void valueChangedForKeyOfUser(JSONObject json, String key, String user);
	
	/**
	 * Callback method for server response from lockKeyOfUser.
	 * @param json
	 * @param key
	 * @param user
	 */
	public void lockedKeyofUser(JSONObject json, String key, String user);
	
	/**
	 * Callback method for server response from unlockKeyOfUser.
	 * @param json
	 * @param key
	 * @param user
	 */
	public void unlockedKeyOfUser(JSONObject json, String key, String user);
}
