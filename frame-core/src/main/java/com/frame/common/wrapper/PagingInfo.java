package com.frame.common.wrapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageSerializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * [分页查询相应] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.wrapper <br>
 */
@Setter
@Getter
@NoArgsConstructor
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PagingInfo<T> extends PageSerializable<T> {

    private static final long serialVersionUID = -6012247596380637636L;
    
    @ApiModelProperty(value = "当前页")
   	private int pageNum;
   	
   	@ApiModelProperty(value = "没页数")
   	private int pageSize;

    public PagingInfo(List<T> list,int pageNum,int pageSize,int total){
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public PagingInfo(List<T> list, PagingInfo old){
        this.list = list;
        this.pageNum = old.pageNum;
        this.pageSize = old.pageSize;
        this.total = old.total;
    }

    public boolean isLastPage(){
        int pages =0;
        if (pageSize > 0) {
            pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        }
        return  pageNum == pages || pages == 0;
    }

	public PagingInfo setNewList(List list){
        super.setList(list);
        return this;
    }

	public static <P> PagingInfo<P> getNewPage(PagingInfo page,List<P> list){
        return page.setNewList(list);
    }

    public static <O,P> PagingInfo<P> getNewPage(List<O> old,List<P> list){
		PagingInfo page = of(old);
        return page.setNewList(list);
    }

	public PagingInfo(List<T> list) {
		super(list);
		if(list instanceof Page) {
			Page<T> page = (Page<T>) list;
			pageNum = page.getPageNum();
			pageSize = page.getPageSize();
		}
	}
	
	public PagingInfo(List<T> list, int pageNum , int pageSize, long total) {
		this.list = list;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	//总页数
	public int getPages() {
		int pages = 0;
		if(pageNum <= 0) {
			pageNum = 1;
		}
		if(pageSize > 0) {
			pages = (int) (total / pageSize + (total % pageSize == 0 ? 0 : 1));
		}
		return pages;
	}
	
	public static <E> PagingInfo<E> of(List<E> list) {
		return new PagingInfo<>(list);
	}
	
	public <F> PagingInfo setNewListInfo(List<F> list){
        super.setList((List<T>) list);
        return this;
    }
	
	//数据转换
	public static <E> PagingInfo<E> getNewPageInfo(PagingInfo page, List<E> list) {
		return page.setNewListInfo(list);
	}


}
