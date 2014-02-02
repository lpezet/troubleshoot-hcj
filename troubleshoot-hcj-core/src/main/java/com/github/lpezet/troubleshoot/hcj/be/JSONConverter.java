/**
 * 
 */
package com.github.lpezet.troubleshoot.hcj.be;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luc
 *
 */
@Converter
public class JSONConverter implements AttributeConverter<Map, String> {
	
	private static final String DEFAULT_EMPTY_JSON = "{}";
	private Logger mLogger = LoggerFactory.getLogger(JSONConverter.class);
	private ObjectMapper mMapper = new ObjectMapper();
	
	public JSONConverter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String convertToDatabaseColumn(Map pSource) {
		try {
			return mMapper.writeValueAsString(pSource);
		} catch (Exception e) {
			mLogger.error("Could not serialize map : " + pSource, e);
		}
		return DEFAULT_EMPTY_JSON;
	}
	
	@Override
	public Map convertToEntityAttribute(String pSource) {
		try {
			return mMapper.readValue(pSource, Map.class);
		} catch (Exception e) {
			mLogger.error("Could not decode JSON for : " + pSource, e);
		}
		return new HashMap<String, Object>();
	}

}
