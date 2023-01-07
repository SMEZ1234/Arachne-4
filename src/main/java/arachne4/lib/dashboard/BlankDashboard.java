package arachne4.lib.dashboard;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Set;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.util.sendable.Sendable;

public class BlankDashboard extends Dashboard
{
	@Override public void putData(String key, Sendable data) {}
	@Override public void putData(Sendable value) {}
	@Override public Sendable getData(String key) { return null; }
	
	@Override public NetworkTableEntry getEntry(String key) { return null; }
	
	@Override public boolean containsKey(String key) { return false; }
	@Override public Set<String> getKeys(int types) { return Collections.emptySet(); }
	@Override public Set<String> getKeys() { return Collections.emptySet(); }
	
	@Override public void setPersistent(String key) {}
	@Override public void clearPersistent(String key) {}
	@Override public boolean isPersistent(String key) { return false; }
	
	@Override public void setFlags(String key, int flags) {}
	@Override public void clearFlags(String key, int flags) {}
	@Override public int getFlags(String key) { return 0; }
	
	@Override public void delete(String key) {}
	
	@Override public boolean putBoolean(String key, boolean value) { return false; }
	@Override public boolean setDefaultBoolean(String key, boolean defaultValue) { return false; }
	@Override public boolean getBoolean(String key, boolean defaultValue) { return defaultValue; }
	
	@Override public boolean putNumber(String key, double value) { return false; }
	@Override public boolean setDefaultNumber(String key, double defaultValue) { return false; }
	@Override public double getNumber(String key, double defaultValue) { return defaultValue; }
	
	@Override public boolean putString(String key, String value) { return false; }
	@Override public boolean setDefaultString(String key, String defaultValue) { return false; }
	@Override public String getString(String key, String defaultValue) { return defaultValue; }
	
	@Override public boolean putBooleanArray(String key, boolean[] value) { return false; }
	@Override public boolean putBooleanArray(String key, Boolean[] value) { return false; }
	@Override public boolean setDefaultBooleanArray(String key, boolean[] defaultValue) { return false; }
	@Override public boolean setDefaultBooleanArray(String key, Boolean[] defaultValue) { return false; }
	@Override public boolean[] getBooleanArray(String key, boolean[] defaultValue) { return defaultValue; }
	@Override public Boolean[] getBooleanArray(String key, Boolean[] defaultValue) { return defaultValue; }
	
	@Override public boolean putNumberArray(String key, double[] value) { return false; }
	@Override public boolean putNumberArray(String key, Double[] value) { return false; }
	@Override public boolean setDefaultNumberArray(String key, double[] defaultValue) { return false; }
	@Override public boolean setDefaultNumberArray(String key, Double[] defaultValue) { return false; }
	@Override public double[] getNumberArray(String key, double[] defaultValue) { return defaultValue; }
	@Override public Double[] getNumberArray(String key, Double[] defaultValue) { return defaultValue; }
	
	@Override public boolean putStringArray(String key, String[] value) { return false; }
	@Override public boolean setDefaultStringArray(String key, String[] defaultValue) { return false; }
	@Override public String[] getStringArray(String key, String[] defaultValue) { return defaultValue; }
	
	@Override public boolean putRaw(String key, byte[] value) { return false; }
	@Override public boolean putRaw(String key, ByteBuffer value, int len) { return false; }
	@Override public boolean setDefaultRaw(String key, byte[] defaultValue) { return false; }
	@Override public byte[] getRaw(String key, byte[] defaultValue) { return defaultValue; }
	
	@Override public void postListenerTask(Runnable task) {}
	@Override public void updateValues() {}
}
