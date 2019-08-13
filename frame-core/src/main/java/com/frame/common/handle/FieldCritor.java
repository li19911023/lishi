
package com.frame.common.handle;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * [字段信息集合,内部使用] <br> 
 *  	这个拼接的sql的条件只有equals
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月25日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.handle <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldCritor implements Serializable {
	 
	private static final long serialVersionUID = 1L;

	private String fieldKey;
	
	private Object fieldValue;
}

