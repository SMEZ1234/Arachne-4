package arachne4.lib.dashboard;

import java.nio.ByteBuffer;
import java.util.Set;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefaultDashboard extends Dashboard
{
	@Override
	public void putData(String key, Sendable data) {
		SmartDashboard.putData(key, data);
	}

	@Override
	public void putData(Sendable value) {
		SmartDashboard.putData(value);
	}

	@Override
	public Sendable getData(String key) {
		return SmartDashboard.getData(key);
	}

	@Override
	public NetworkTableEntry getEntry(String key) {
		return SmartDashboard.getEntry(key);
	}

	@Override
	public boolean containsKey(String key) {
		return SmartDashboard.containsKey(key);
	}

	@Override
	public Set<String> getKeys(int types) {
		return SmartDashboard.getKeys(types);
	}

	@Override
	public Set<String> getKeys() {
		return SmartDashboard.getKeys();
	}

	@Override
	public void setPersistent(String key) {
		SmartDashboard.setPersistent(key);
	}

	@Override
	public void clearPersistent(String key) {
		SmartDashboard.clearPersistent(key);
	}

	@Override
	public boolean isPersistent(String key) {
		return SmartDashboard.isPersistent(key);
	}

	@Override
	public void setFlags(String key, int flags) {
		SmartDashboard.setFlags(key, flags);
	}

	@Override
	public void clearFlags(String key, int flags) {
		SmartDashboard.clearFlags(key, flags);
	}

	@Override
	public int getFlags(String key) {
		return SmartDashboard.getFlags(key);
	}

	@Override
	public void delete(String key) {
		SmartDashboard.delete(key);
	}

	@Override
	public boolean putBoolean(String key, boolean value) {
		return SmartDashboard.putBoolean(key, value);
	}

	@Override
	public boolean setDefaultBoolean(String key, boolean defaultValue) {
		return SmartDashboard.setDefaultBoolean(key, defaultValue);
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		return SmartDashboard.getBoolean(key, defaultValue);
	}

	@Override
	public boolean putNumber(String key, double value) {
		return SmartDashboard.putNumber(key, value);
	}

	@Override
	public boolean setDefaultNumber(String key, double defaultValue) {
		return SmartDashboard.setDefaultNumber(key, defaultValue);
	}

	@Override
	public double getNumber(String key, double defaultValue) {
		return SmartDashboard.getNumber(key, defaultValue);
	}

	@Override
	public boolean putString(String key, String value) {
		return SmartDashboard.putString(key, value);
	}

	@Override
	public boolean setDefaultString(String key, String defaultValue) {
		return SmartDashboard.setDefaultString(key, defaultValue);
	}

	@Override
	public String getString(String key, String defaultValue) {
		return SmartDashboard.getString(key, defaultValue);
	}

	@Override
	public boolean putBooleanArray(String key, boolean[] value) {
		return SmartDashboard.putBooleanArray(key, value);
	}

	@Override
	public boolean putBooleanArray(String key, Boolean[] value) {
		return SmartDashboard.putBooleanArray(key, value);
	}

	@Override
	public boolean setDefaultBooleanArray(String key, boolean[] defaultValue) {
		return SmartDashboard.setDefaultBooleanArray(key, defaultValue);
	}

	@Override
	public boolean setDefaultBooleanArray(String key, Boolean[] defaultValue) {
		return SmartDashboard.setDefaultBooleanArray(key, defaultValue);
	}

	@Override
	public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
		return SmartDashboard.getBooleanArray(key, defaultValue);
	}

	@Override
	public Boolean[] getBooleanArray(String key, Boolean[] defaultValue) {
		return SmartDashboard.getBooleanArray(key, defaultValue);
	}

	@Override
	public boolean putNumberArray(String key, double[] value) {
		return SmartDashboard.putNumberArray(key, value);
	}

	@Override
	public boolean putNumberArray(String key, Double[] value) {
		return SmartDashboard.putNumberArray(key, value);
	}

	@Override
	public boolean setDefaultNumberArray(String key, double[] defaultValue) {
		return SmartDashboard.setDefaultNumberArray(key, defaultValue);
	}

	@Override
	public boolean setDefaultNumberArray(String key, Double[] defaultValue) {
		return SmartDashboard.setDefaultNumberArray(key, defaultValue);
	}

	@Override
	public double[] getNumberArray(String key, double[] defaultValue) {
		return SmartDashboard.getNumberArray(key, defaultValue);
	}

	@Override
	public Double[] getNumberArray(String key, Double[] defaultValue) {
		return SmartDashboard.getNumberArray(key, defaultValue);
	}

	@Override
	public boolean putStringArray(String key, String[] value) {
		return SmartDashboard.putStringArray(key, value);
	}

	@Override
	public boolean setDefaultStringArray(String key, String[] defaultValue) {
		return SmartDashboard.setDefaultStringArray(key, defaultValue);
	}

	@Override
	public String[] getStringArray(String key, String[] defaultValue) {
		return SmartDashboard.getStringArray(key, defaultValue);
	}

	@Override
	public boolean putRaw(String key, byte[] value) {
		return SmartDashboard.putRaw(key, value);
	}

	@Override
	public boolean putRaw(String key, ByteBuffer value, int len) {
		return SmartDashboard.putRaw(key, value, len);
	}

	@Override
	public boolean setDefaultRaw(String key, byte[] defaultValue) {
		return SmartDashboard.setDefaultRaw(key, defaultValue);
	}

	@Override
	public byte[] getRaw(String key, byte[] defaultValue) {
		return SmartDashboard.getRaw(key, defaultValue);
	}

	@Override
	public void postListenerTask(Runnable task) {
		SmartDashboard.postListenerTask(task);
	}

	@Override
	public void updateValues() {
		SmartDashboard.updateValues();
	}
}
