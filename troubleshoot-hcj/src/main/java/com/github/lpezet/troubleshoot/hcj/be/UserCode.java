/**
 * 
 */
package com.github.lpezet.troubleshoot.hcj.be;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luc
 *
 */
@Entity
@Table(name="user_codes")
public class UserCode implements Serializable {

	private static final long serialVersionUID = 7524978331940918252L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long mId;
	
	@Column(name="code", unique=true)
	private String mCode;	
	
	@Column(name="user_name", unique=true)
	private String mUsername;
	
	@Column(name="type_id")
	private int mTypeId;
	
	@Convert(converter=JSONConverter.class)
	@Column(name="options")
	private Map<String, Object> mOptions;
	
	public UserCode() {
		super();
	}
	

	public UserCode(long pId) {
		super();
		setId(pId);
	}
	
	public long getId() {
		return mId;
	}

	public void setId(long pId) {
		mId = pId;
	}

	public String getCode() {
		return mCode;
	}

	public void setCode(String pCode) {
		mCode = pCode;
	}
	
	public void setUsername(String pValue) {
		mUsername = pValue;
	}
	
	public String getUsername() {
		return mUsername;
	}
	
	public void setTypeId(int pValue) {
		mTypeId = pValue;
	}
	
	public int getTypeId() {
		return mTypeId;
	}

	public Map<String, Object> getOptions() {
		return mOptions;
	}


	public void setOptions(Map<String, Object> pOptions) {
		mOptions = pOptions;
	}
}