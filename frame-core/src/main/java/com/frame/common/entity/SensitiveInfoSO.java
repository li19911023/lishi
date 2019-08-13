
package com.frame.common.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * [敏感字] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月25日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.entity <br>
 */
@Data
public class SensitiveInfoSO implements Serializable {
	 
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "敏感字")
    private String sensitiveText;

	@ApiModelProperty(value = "替换文字")
    private String replaceText;
	
	@ApiModelProperty(value = "文字类型(1:汉字  2:拼音  3:英文)")
	private Integer textType;
}

