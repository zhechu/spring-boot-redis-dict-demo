package com.wise.hello.entity;

import java.io.Serializable;

/**
 * 数据字典详情
 * @author lingyuwang
 *
 */
public class DictDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6335617327810271789L;

	private String id;
	
	private String label;
	
	private String value;

	/**
	 * 序列化时必须保留默认构造器
	 */
	public DictDetail() {
		super();
	}

	public DictDetail(String id, String label, String value) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DictDetail [id=" + id + ", label=" + label + ", value=" + value + "]";
	}
	
}
