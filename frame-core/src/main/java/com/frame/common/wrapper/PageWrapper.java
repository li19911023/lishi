
package com.frame.common.wrapper;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * [分页对象包装类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.wrapper <br>
 */
@Data
@ApiModel(value = "分页对象包装类")
public class PageWrapper<PAGE> implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "分页对象")
	private PAGE data;
	
	@ApiModelProperty(name = "当前页")
	private Integer pageNum;
	
	@ApiModelProperty(name = "每页数")
	private Integer pageSize;
	
}

