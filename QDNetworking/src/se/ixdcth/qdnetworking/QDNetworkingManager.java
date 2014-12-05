package se.ixdcth.qdnetworking;

/**
 * This interface contains the public methods of QDNetworking
 * @author Peter Börjesson
 *
 */
public interface QDNetworkingManager {
    
	/**
	 * Save the value for a given value for a key of a user
	 * @param key
	 * @param user
	 * @param value
	 */
	public void saveValueForKeyOfUser(String key, String user, String value);
    
	/**
	 * Load the value for a given key of a user
	 * @param key
	 * @param user
	 */
    public void loadValueForKeyOfUser(String key, String user);
    
    /**
     * Delete the entry for a given key of a user
     * @param key
     * @param user
     */
    public void deleteKeyOfUser(String key, String user);
    
    /**
     * This will start monitoring value of a given key of a user.
     * Any changes will be reported by the server to a callback method
     * until monitoring is stopped by calling the method ignoreKeyOfUser.
     * @param key
     * @param user
     */
    public void monitorKeyOfUser(String key, String user);
    
    /**
     * This method will stop the monitoring of a value of a given key of a user.
     * @param key
     * @param user
     */
    public void ignoreKeyOfUser(String key, String user);
    
    /**
     * This method will lock the entry for a given key of a user, giving the holder
     * of the lock exclusive rights to change the value until the lock is released.
     * @param key
     * @param user
     */
    public void lockKeyOfUser(String key, String user);
    
    /**
     * This method will release the lock of an entry for a given key of a user. 
     * @param key
     * @param user
     */
    public void unlockKeyOfUser(String key,String user);
}
