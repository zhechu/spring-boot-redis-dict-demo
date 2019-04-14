package com.wise.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典
 * @author lingyuwang
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dict implements Serializable {
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6533027738765004957L;

	/**
	 * 字典编码
	 */
	private String code;
	
	/**
	 * 字典父编码
	 */
	private String parentCode;

}