package arachne4.lib.dashboard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.util.sendable.Sendable;

public class LocalDashboard extends Dashboard
{
	protected final Map<String, Object> map = new HashMap<String, Object>();
	protected final Map<String, Integer> flagMap = new HashMap<String, Integer>();
	
	@Override
	public void putData(String key, Sendable data) {
		throw new UnsupportedOperationException("putData not implemented for local dashboards");
	}

	@Override
	public void putData(Sendable value) {
		throw new UnsupportedOperationException("putData not implemented for local dashboards");
	}

	@Override
	public Sendable getData(String key) {
		throw new UnsupportedOperationException("getData not implemented for local dashboards");
	}

	@Override
	public NetworkTableEntry getEntry(String key) {
		throw new UnsupportedOperationException("getEntry not implemented for local dashboards");
	}

	@Override
	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	@Override
	public Set<String> getKeys(int types) {
		if(types == 0) return getKeys();
		
		return getKeys()
			.stream()
			.filter((key) -> (flagMap.get(key) & types) != 0)
			.collect(Collectors.toSet());
	}

	@Override
	public Set<String> getKeys() {
		return map.keySet();
	}

	@Override
	public void setPersistent(String key) {
		throw new UnsupportedOperationException("setPersistent not implemented for local dashboards");
	}

	@Override
	public void clearPersistent(String key) {
		throw new UnsupportedOperationException("clearPersistent not implemented for local dashboards");
	}

	@Override
	public boolean isPersistent(String key) {
		throw new UnsupportedOperationException("isPersistent not implemented for local dashboards");
	}

	@Override
	public void setFlags(String key, int flags) {
		flagMap.put(key, flagMap.get(key) | flags);
	}

	@Override
	public void clearFlags(String key, int flags) {
		flagMap.put(key, flagMap.get(key) & ~flags);
	}

	@Override
	public int getFlags(String key) {
		return flagMap.get(key);
	}

	@Override
	public void delete(String key) {
		map.remove(key);
		flagMap.remove(key);
	}
	
	@Override
	public boolean putBoolean(String key, boolean value) {
		if(map.containsKey(key) && !(map.get(key) instanceof Boolean)) return false;
		
		map.put(key, value);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultBoolean(String key, boolean defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof Boolean)) return false;
		
		map.putIfAbsent(key, defaultValue);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		Object value = map.get(key);
		
		if(map.containsKey(key) && value instanceof Boolean) return (boolean) value;
		else return defaultValue;
	}

	@Override
	public boolean putNumber(String key, double value) {
		if(map.containsKey(key) && !(map.get(key) instanceof Double)) return false;
		
		map.put(key, value);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultNumber(String key, double defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof Double)) return false;
		
		map.putIfAbsent(key, defaultValue);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public double getNumber(String key, double defaultValue) {
		Object value = map.get(key);
		
		if(map.containsKey(key) && value instanceof Double) return (double) value;
		else return defaultValue;
	}

	@Override
	public boolean putString(String key, String value) {
		if(map.containsKey(key) && !(map.get(key) instanceof String)) return false;
		
		map.put(key, value);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultString(String key, String defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof String)) return false;
		
		map.putIfAbsent(key, defaultValue);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public String getString(String key, String defaultValue) {
		Object value = map.get(key);
		
		if(map.containsKey(key) && value instanceof String) return (String) value;
		else return defaultValue;
	}

	@Override
	public boolean putBooleanArray(String key, boolean[] value) {
		if(map.containsKey(key) && !(map.get(key) instanceof boolean[])) return false;
		
		map.put(key, value.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean putBooleanArray(String key, Boolean[] value) {
		if(map.containsKey(key) && !(map.get(key) instanceof boolean[])) return false;
		
		boolean[] primitives = new boolean[value.length];
		for(int i = 0; i < primitives.length; i++) primitives[i] = value[i];
		
		map.put(key, primitives);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultBooleanArray(String key, boolean[] defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof boolean[])) return false;
		
		map.putIfAbsent(key, defaultValue.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultBooleanArray(String key, Boolean[] defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof boolean[])) return false;
		
		boolean[] primitives = new boolean[defaultValue.length];
		for(int i = 0; i < primitives.length; i++) primitives[i] = defaultValue[i];
		
		map.putIfAbsent(key, primitives);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
		Object value = map.get(key);

		if(map.containsKey(key) && value instanceof boolean[]) return ((boolean[]) value).clone();
		else return defaultValue.clone();
	}

	@Override
	public Boolean[] getBooleanArray(String key, Boolean[] defaultValue) {
		Object value = map.get(key);

		if(map.containsKey(key) && value instanceof boolean[]) {
			boolean[] primitives = (boolean[]) value;
			Boolean[] complex = new Boolean[primitives.length];
			for(int i = 0; i < complex.length; i++) complex[i] = primitives[i];
			
			return complex;
		}
		else return defaultValue.clone();
	}

	@Override
	public boolean putNumberArray(String key, double[] value) {
		if(map.containsKey(key) && !(map.get(key) instanceof double[])) return false;
		
		map.put(key, value.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean putNumberArray(String key, Double[] value) {
		if(map.containsKey(key) && !(map.get(key) instanceof double[])) return false;
		
		double[] primitives = new double[value.length];
		for(int i = 0; i < primitives.length; i++) primitives[i] = value[i];
		
		map.put(key, primitives);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultNumberArray(String key, double[] defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof double[])) return false;
		
		map.putIfAbsent(key, defaultValue.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultNumberArray(String key, Double[] defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof double[])) return false;
		
		double[] primitives = new double[defaultValue.length];
		for(int i = 0; i < primitives.length; i++) primitives[i] = defaultValue[i];
		
		map.putIfAbsent(key, primitives);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public double[] getNumberArray(String key, double[] defaultValue) {
		Object value = map.get(key);

		if(map.containsKey(key) && value instanceof double[]) return ((double[]) value).clone();
		else return defaultValue.clone();
	}

	@Override
	public Double[] getNumberArray(String key, Double[] defaultValue) {
		Object value = map.get(key);

		if(map.containsKey(key) && value instanceof double[]) {
			double[] primitives = (double[]) value;
			Double[] complex = new Double[primitives.length];
			for(int i = 0; i < complex.length; i++) complex[i] = primitives[i];
			
			return complex;
		}
		else return defaultValue.clone();
	}

	@Override
	public boolean putStringArray(String key, String[] value) {
		if(map.containsKey(key) && !(map.get(key) instanceof String[])) return false;
		
		map.put(key, value.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultStringArray(String key, String[] defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof String[])) return false;
		
		map.putIfAbsent(key, defaultValue.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public String[] getStringArray(String key, String[] defaultValue) {
		Object value = map.get(key);

		if(map.containsKey(key) && value instanceof String[]) return ((String[]) value).clone();
		else return defaultValue.clone();
	}

	@Override
	public boolean putRaw(String key, byte[] value) {
		if(map.containsKey(key) && !(map.get(key) instanceof byte[])) return false;
		
		map.put(key, value.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean putRaw(String key, ByteBuffer value, int len) {
		if(!value.isDirect()) throw new IllegalArgumentException("must be a direct buffer");
		if(value.capacity() < len) throw new IllegalArgumentException("buffer is too small, must be at least " + len);
		
		if(map.containsKey(key) && !(map.get(key) instanceof byte[])) return false;
		
		byte[] toPut = new byte[len];
		value.get(toPut);
		
		map.put(key, toPut);
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public boolean setDefaultRaw(String key, byte[] defaultValue) {
		if(map.containsKey(key) && !(map.get(key) instanceof byte[])) return false;
		
		map.putIfAbsent(key, defaultValue.clone());
		flagMap.putIfAbsent(key, 0);
		
		return true;
	}

	@Override
	public byte[] getRaw(String key, byte[] defaultValue) {
		Object value = map.get(key);

		if(map.containsKey(key) && value instanceof byte[]) return ((byte[]) value).clone();
		else return defaultValue.clone();
	}

	@Override
	public void postListenerTask(Runnable task) {
		throw new UnsupportedOperationException("postListenerTask not implemented for local dashboards");
	}

	@Override
	public void updateValues() {
		// TODO Unimplemented support for Sendables
	}
}
