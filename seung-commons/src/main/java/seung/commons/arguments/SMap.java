package seung.commons.arguments;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import seung.commons.SCommonV;

@SuppressWarnings("rawtypes")
public class SMap extends HashMap {

	private static final long serialVersionUID = 1L;
	
	public SMap() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	public SMap(Map m) {
		this.putAll(m);
	}
	public SMap(Object o) {
		objectToSMap(o);
	}
	
	@SuppressWarnings("unchecked")
	public SMap putMap(Map m) {
		super.putAll(m);
		return this;
	}
	
	public SMap putObject(Object o) {
		return objectToSMap(o);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SMap put(Object key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public String getString(String key) {
		String val = "";
		if(containsKey(key)) {
			if(get(key).getClass().isArray()) {
				if(get(key) != null) {
					String[] vals = (String[]) get(key);
					val = vals[0];
				}
			} else {
				val = "" + String.valueOf(get(key));
			}
		}
		return val;
	}
	
	@SuppressWarnings("unchecked")
	public void appendString(String key, String val) {
		super.put(key, getString(key) + val);
	}
	
	public String[] getStringArray(String key) {
		String[] stringArray = null;
		if(containsKey(key)) {
			if(get(key).getClass().isArray()) {
				if(get(key) != null) {
					stringArray = (String[]) get(key);
				}
			} else {
				stringArray = new String[1];
				stringArray[0] = getString(key);
			}
		}
		return stringArray;
	}
	
	public boolean getBoolean(String key) {
		return containsKey(key) ? Boolean.valueOf(getString(key)) : null;
	}
	
	public int getInt(String key) {
		return containsKey(key) ? Integer.parseInt(getString(key)) : null;
	}
	
	public double getDouble(String key) {
		return containsKey(key) ? Double.valueOf(getString(key)) : null;
	}
	
	public BigInteger getBigInteger(String key) {
		return containsKey(key) ? new BigInteger(getString(key)) : null;
	}
	
	public long getLong(String key) {
		return containsKey(key) ? Long.valueOf(getString(key)) : null;
	}
	
	public float getFloat(String key) {
		return containsKey(key) ? Float.valueOf(getString(key)) : null;
	}
	
	public SMap getSMap(String key) {
		return (SMap) get(key);
	}
	
	public List getList(String key) {
		return (List) get(key);
	}
	
	@SuppressWarnings("unchecked")
	public List<SMap> getListSMap(String key) {
		return (List<SMap>) get(key);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SMap> getArrayListSMap(String key) {
		return (ArrayList<SMap>) get(key);
	}
	
	public SMap objectToSMap(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		try {
			for(Field field : fields) {
				field.setAccessible(true);
				this.put(field.getName(), field.get(o));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public Object convertSMapToObject(Object object) {
		
		String key = "";
		String methodName = "";
		
		Iterator<String> iterator = this.keySet().iterator();
		Method[] methods = null;
		while(iterator.hasNext()) {
			
			key = (String) iterator.next();
			methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
			
			methods = object.getClass().getDeclaredMethods();
			try {
				for(Method method : methods) {
					if(methodName.equals(method.getName())) {
						if(this.get(key) != null) method.invoke(object, this.get(key));
						else method.invoke(object, "");
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return object;
	}
	
	public String toJsonString() {
		return toJsonString(false);
	}
	public String toJsonString(boolean isPretty) {
		if(isPretty) {
			return SCommonV._S_GSON_PRETTY.toJson(this);
		} else {
			return SCommonV._S_GSON.toJson(this);
		}
	}
	
}
