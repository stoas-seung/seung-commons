package seung.commons.arguments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SRequestMap {

	private SMap network;
	private SMap session;
	private SMap header;
	private SMap query;
	
	public void putNetwork(String key, Object value) {
		if(network == null) network = new SMap();
		network.put(key, value);
	}
	public SMap getNetwork() {
		return network == null ? new SMap() : network;
	}
	public void putSession(String key, Object value) {
		if(session == null) session = new SMap();
		session.put(key, value);
	}
	public SMap getSession() {
		return session == null ? new SMap() : session;
	}
	public void putHeader(String key, Object value) {
		if(header == null) header = new SMap();
		header.put(key, value);
	}
	public SMap getHeader() {
		return header == null ? new SMap() : header;
	}
	public void putQuery(String key, Object value) {
		if(query == null) query = new SMap();
		query.put(key, value);
	}
	@SuppressWarnings("unchecked")
	public void putQuery(SMap sMap) {
		if(query == null) query = new SMap();
		query.putAll(sMap);
	}
	public void putQuery(Object o) {
		if(query == null) query = new SMap();
		query.objectToSMap(o);
	}
	public SMap getQuery() {
		return query == null ? new SMap() : query;
	}
	
	public String toString(boolean isPretty) {
		try {
			if(isPretty) {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} else {
				return new ObjectMapper().writeValueAsString(this);
			}
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}
	
}
