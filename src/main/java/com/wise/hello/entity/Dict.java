package com.wise.hello.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 数据字典
 * @author lingyuwang
 *
 */
public class Dict implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3656645447055193294L;

	private String id;
	
	private List<DictDetail> dictDetail;

	/**
	 * 序列化时必须保留默认构造器
	 */
	public Dict() {
		super();
	}

	public Dict(String id, List<DictDetail> dictDetail) {
		super();
		this.id = id;
		this.dictDetail = dictDetail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DictDetail> getDictDetail() {
		return dictDetail;
	}

	public void setDictDetail(List<DictDetail> dictDetail) {
		this.dictDetail = dictDetail;
	}

	@Override
	public String toString() {
		return "Dict [id=" + id + ", dictDetail=" + dictDetail + "]";
	}
	
}
