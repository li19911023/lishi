package com.frame.common.wrapper;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * [过滤条件包装类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.wrapper <br>
 */
@Data
@ApiModel(value = "FilterWrapper.过滤条件包装类",description = "过滤条件包装类")
public class FilterWrapper <FO> implements Serializable{

    private static final long serialVersionUID = 2027397318109680732L;

    public static final String ORDER_TYPE_ASC = "asc";//升序

    public static final String ORDER_TYPE_DESC = "desc";//降序

    public FO filter;

    @ApiModelProperty(value = "排序设置")
    public List<OrderProp> orders;

    @Data
    @ApiModel(value = "OrderProp.需要排序的字段集合", description = "需要排序的字段集合")
    public static class OrderProp {

        @ApiModelProperty(value = "排序字段")
        public String orderName;

        @ApiModelProperty(value = "排序方式，asc：升序，desc：降序")
        public String orderType;

    }

}
