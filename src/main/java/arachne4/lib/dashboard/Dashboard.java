package arachne4.lib.dashboard;

import java.nio.ByteBuffer;
import java.util.Set;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.util.sendable.Sendable;

public abstract class Dashboard
{
	private static Dashboard implementation;
	
	public static void setImplementation(Dashboard implementation) {
		Dashboard.implementation = implementation;
	}
	
	public static Dashboard getInstance() {
		return implementation;
	}
	
	public abstract void putData(String key, Sendable data);
	public abstract void putData(Sendable value);
	public abstract Sendable getData(String key);
	
	public abstract NetworkTableEntry getEntry(String key);
	
	public abstract boolean containsKey(String key);
	public abstract Set<String> getKeys(int types);
	public abstract Set<String> getKeys();
	
	public abstract void setPersistent(String key);
	public abstract void clearPersistent(String key);
	public abstract boolean isPersistent(String key);
	
	public abstract void setFlags(String key, int flags);
	public abstract void clearFlags(String key, int flags);
	public abstract int getFlags(String key);
	
	public abstract void delete(String key);
	
	public abstract boolean putBoolean(String key, boolean value);
	public abstract boolean setDefaultBoolean(String key, boolean defaultValue);
	public abstract boolean getBoolean(String key, boolean defaultValue);
	
	public abstract boolean putNumber(String key, double value);
	public abstract boolean setDefaultNumber(String key, double defaultValue);
	public abstract double getNumber(String key, double defaultValue);
	
	public abstract boolean putString(String key, String value);
	public abstract boolean setDefaultString(String key, String defaultValue);
	public abstract String getString(String key, String defaultValue);
	
	public abstract boolean putBooleanArray(String key, boolean[] value);
	public abstract boolean putBooleanArray(String key, Boolean[] value);
	public abstract boolean setDefaultBooleanArray(String key, boolean[] defaultValue);
	public abstract boolean setDefaultBooleanArray(String key, Boolean[] defaultValue);
	public abstract boolean[] getBooleanArray(String key, boolean[] defaultValue);
	public abstract Boolean[] getBooleanArray(String key, Boolean[] defaultValue);
	
	public abstract boolean putNumberArray(String key, double[] value);
	public abstract boolean putNumberArray(String key, Double[] value);
	public abstract boolean setDefaultNumberArray(String key, double[] defaultValue);
	public abstract boolean setDefaultNumberArray(String key, Double[] defaultValue);
	public abstract double[] getNumberArray(String key, double[] defaultValue);
	public abstract Double[] getNumberArray(String key, Double[] defaultValue);
	
	public abstract boolean putStringArray(String key, String[] value);
	public abstract boolean setDefaultStringArray(String key, String[] defaultValue);
	public abstract String[] getStringArray(String key, String[] defaultValue);
	
	public abstract boolean putRaw(String key, byte[] value);
	public abstract boolean putRaw(String key, ByteBuffer value, int len);
	public abstract boolean setDefaultRaw(String key, byte[] defaultValue);
	public abstract byte[] getRaw(String key, byte[] defaultValue);
	
	public abstract void postListenerTask(Runnable task);
	public abstract void updateValues();
}
