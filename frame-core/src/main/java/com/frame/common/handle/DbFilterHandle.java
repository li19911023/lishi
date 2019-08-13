
package com.frame.common.handle;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.frame.common.annotation.DbFilter;
import com.frame.common.annotation.DbPropertyType;
import com.frame.common.enums.db.FilterPropertyTypeEnum;
import com.frame.common.enums.exception.ErrorCodeEnum;
import com.frame.common.exception.InsideException;
import com.frame.common.util.common.CollectionListUtils;
import com.frame.common.util.common.DateUtils;
import com.frame.common.wrapper.FilterWrapper;
import com.frame.common.wrapper.PageWrapper;
import com.frame.common.wrapper.PagingInfo;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * [过滤处理器工具类，采用通用MAPPER的example方式过滤；只需要定义过滤实体类(FO)，实体类添加过滤注解@DbFilter作为区分，
 *  		 实体类的属性默认与对应的PO对应上，用@DbPropertyType用在FO的属性上，（默认为相等类型）TPYE属性表明过滤条件。
 * 			入参：FO对象、PO类CLASS
 *  		注：目前定义FO的属性，不支持数组的形式，原因很奇怪，mybatis类型处理通过不了。--- 建议用List类型替代] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.handle <br>
 */
@Slf4j
public class DbFilterHandle {
	
	private static final String PRE_LIKE= "LIKE:";
	
	private static final int PAGE_SIZE_OPERATION = 100;

	/**
	 * 
	 * [ @Description: 
	 *           	 定制查询列表，排序，分页器
	 *            	过滤条件处理类 --- 一般 service 调用该接口，就可以实现过滤了
	 * @Param: 泛型为过滤实体类FO; pageWrapper： 包装的过滤类] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param pageWrapper 分页对象
	 * @param mapper DB mapper 对象
	 * @param clazz po对象
	 * @param isOtherPage 是否分页 true 分页   false不分页
	 * @return <br>
	 */
	public static <FO, PO> List<PO> getFilterInfoPageList(
			PageWrapper<FilterWrapper<FO>> pageWrapper, Mapper<PO> mapper,
			Class<PO> poClazz, Boolean isOtherPage) {

		Optional.ofNullable(pageWrapper).orElseThrow(()->new InsideException(ErrorCodeEnum.IL99990300));

		FilterWrapper<FO> filterWrapper = Optional.ofNullable(pageWrapper.getData()).orElse(new FilterWrapper<>());

		//采用example方式过滤
		Example example = getFilterExample(filterWrapper,poClazz);

		//其他逻辑分页器，查询所有数据，
		if(isOtherPage){
			PageHelper.startPage(pageWrapper.getPageNum(), pageWrapper.getPageSize());
		}else {
			PageHelper.startPage(0, 0);
		}

		//查询数据
		List<PO> list = mapper.selectByExample(example);
		return list;
	}
	
	/**
	 * @Description: 定制查询列表，排序，分页器
	 *		过滤条件处理类 --- 一般 service 调用该接口，就可以实现过滤了
	 * @Param: 泛型为过滤实体类FO; pageWrapper： 包装的过滤类；mappper:为对应的DB mapper ；clazz为对应表的PO<br> 
	 * @Param:
	 * @return:
	 */
	public static <FO, PO> List<PO> getFilterInfoList(FO fo, Mapper<PO> mapper,Class<PO> poClazz) {

		//创建example
		Example example = new Example(poClazz);
		
		try {
			setDynaSql(example, fo, poClazz);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		}

		//查询数据
		List<PO> list = mapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 
	 * [获取Example对象信息包含动态sql] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	private static <FO, PO> Example getFilterExample(FilterWrapper<FO> filterWrapper, Class<PO> clazz) {
		//创建example
		Example example = new Example(clazz);
		
		//坑，排它锁（for update）与 limit不兼容，此处也不需要排他锁
		//example.setForUpdate(true);
		Optional.of(filterWrapper);
		
		//设置动态sql
		FO fo = filterWrapper.getFilter();
		try {
			setDynaSql(example, fo, clazz);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		}
		
		//获取排序条件列表
		List<FilterWrapper.OrderProp> orders = filterWrapper.getOrders();
		if(CollectionListUtils.isNotEmpty(orders)) {
			setOrderExample(example, filterWrapper, clazz);
		}
		return example;

	}
	
	/**
	 * 
	 * [定制查询列表，包含自定义动态sql，排序，分页器] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <Filter, PO> List<PO> getFilterInfoListSql(
			PageWrapper<FilterWrapper<Filter>> filter, Mapper<PO> mapper,
			Class<PO> poClazz, CustCritor<Filter> critor, Boolean isOtherPager) {

		FilterWrapper<Filter> filterWrapper = Optional.ofNullable(filter.getData()).orElse(null);

		//采用example方式过滤
		Example example = DbFilterHandle.getFilterExample(filterWrapper,poClazz,critor);

		//其他逻辑分页器，查询所有数据，
		if(isOtherPager){
			PageHelper.startPage(filter.getPageNum(), filter.getPageSize());
		} else {
			PageHelper.startPage(0, 0);
		}
		//查询数据
		List<PO> list = mapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 
	 * [可以添加动态sql] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	private static <Filter,PO> Example getFilterExample(FilterWrapper<Filter> filterWrapper, 
			Class<PO> poClass, CustCritor<Filter> critor) {
		//创建example
		Example example = new Example(poClass);
		
		Optional.of(filterWrapper);
		//设置动态sql
		Filter fo = filterWrapper.getFilter();
		try {
			setDynaSql(example, fo, poClass);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		}

		//加入自定义部分的动态sql条件
		Optional.ofNullable(critor).ifPresent(v->v.CreCritor(example,fo));
		return example;

	}
	
	/**
	 * 
	 * [设置排序example] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param poClass <br>
	 */
	private static <FO, PO> void setOrderExample(Example example, FilterWrapper<FO> filterWrapper, Class<PO> poClass) {

		Optional.of(example);

		//获取排序条件列表
		List<FilterWrapper.OrderProp> orders = filterWrapper.getOrders();

		if(CollectionListUtils.isNotEmpty(orders)) {
			Example.OrderBy orderBy = null;
			for (FilterWrapper.OrderProp order : orders) {
				String orderName = order.getOrderName();
				if (StringUtils.isEmpty(orderName)) {
					break;
				}
				//注此处传入通用Mapper属性名与实体类对应，不是与数据库对应
				//根据升降序，设置order，第一次通过example设置，后续接着自身
				try {
					if (poClass.getDeclaredField(orderName) == null) {
						log.warn("排序的字段必须和PO上的属性一致");
						break;
					}
					if (order.getOrderType().equalsIgnoreCase(FilterWrapper.ORDER_TYPE_ASC)) {
						if (Optional.ofNullable(orderBy).isPresent()) {
							orderBy = orderBy.orderBy(orderName).asc();
						} else {
							orderBy = example.orderBy(orderName).asc();
						}
					} else if (order.getOrderType().equalsIgnoreCase(FilterWrapper.ORDER_TYPE_DESC)) {
						if (Optional.ofNullable(orderBy).isPresent()) {
							orderBy = orderBy.orderBy(orderName).desc();
						} else {
							orderBy = example.orderBy(orderName).desc();
						}
					}
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}

	}
	
	/**
	 * 
	 * [@Description: 处理过滤条件映射,生成dongtaisql语句
	 * 此处过滤对象的类的各个字段需要与po对象一致，如果不一致，需要用注解名称说明，如下：
	 * @DbPropertyType（type=EQURE,name="houseNo"）] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param <PO>
	 * @param example Example查询对象
	 * @param filter FO的过滤条件类
	 * @param poClass PO对应表的实体类
	 * @throws NoSuchFieldException <br>
	 */
	@SuppressWarnings("unchecked")
	private static <PO,FO> void setDynaSql(Example example, FO filter, Class<PO> poClass) throws NoSuchFieldException {

		Optional.of(example);
		Example.Criteria criteria = example.createCriteria();

		if (!Optional.ofNullable(filter).isPresent()) {
			log.info("FO的过滤条件类不能为null");
			return;
		}
		Class<? extends Object> clazz = filter.getClass();
		
		//如果不是过滤类，直接抛出异常
		if (!clazz.isAnnotationPresent(DbFilter.class)) {
			log.error("该FO类缺少类注解@DbFilter");
			throw new InsideException(ErrorCodeEnum.IL99990400);
		}

		//获取过滤类中的所有字段信息
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {

			//获取过滤类的属性值
			Object proValue = null;
			//private属性需要先设置访问权限
			f.setAccessible(true);
			try {
				proValue = f.get(filter);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			//获取值为空，查找下一个字段
			if (!Optional.ofNullable(proValue).isPresent()) {
				continue;
			}

			//字符串为空的话，忽略
			if (proValue instanceof String) {
				if (StringUtils.isEmpty((String) proValue)) {
					continue;
				}
			}

			//获取过滤类字段对应注解
			DbPropertyType ann = f.getAnnotation(DbPropertyType.class);
			//获取表的字段名称，为空，或者没有name，使用属性名
			String columnName = Optional.ofNullable(ann).map(va -> {
				String name = va.name();
				return StringUtils.isEmpty(name) ? null : name;
			}).orElse(f.getName());

			FilterPropertyTypeEnum type;
			Optional<FilterPropertyTypeEnum> tyOp = Optional.ofNullable(ann).map(t -> t.type());

			if (tyOp.isPresent()) {
				type = tyOp.get();
			} else if (proValue.getClass().isArray() || proValue instanceof List) {
				type = FilterPropertyTypeEnum.LIST;
			} else {
				type = FilterPropertyTypeEnum.EQURE;
			}


			try {
				//注：此处有坑，传入通用Mapper的过滤属性名与实体类对应，不是与数据库字段对应，所以不需要@Column注解
				//获取 实体类的 Column字段，保证和数据库的字段对应
				//拼接example，动态sql
				switch (type) {
					case NOCONDITION : //该枚举是过滤掉不需要的字段
						break;
						
 					case EQURE:
						criteria.andEqualTo(columnName, proValue);
						break;
						
					case NOTEQURE:
						criteria.andNotEqualTo(columnName, proValue);
						break;
						
					case LIKE:
						if (proValue instanceof String) {
							criteria.andLike(columnName, "%" + proValue + "%");
						}
						break;
						
					case RANGE://此处包含边界值
						if (proValue instanceof String) {
							String[] s = ((String) proValue).split(",");
							if (StringUtils.isEmpty(s[0]) && StringUtils.isNotEmpty(s[1])) {
								criteria.andLessThanOrEqualTo(columnName, s[1]);
							} else if (StringUtils.isNotEmpty(s[0]) && s.length == 1) {
								criteria.andGreaterThanOrEqualTo(columnName, s[0]);
							} else {
								criteria.andBetween(columnName, s[0], s[1]);
							}
						}
						break;
						
					case IGRANGE://不包含边界值
						if (proValue instanceof String) {
							String[] s = ((String) proValue).split(",");
							if (StringUtils.isEmpty(s[0]) && StringUtils.isNotEmpty(s[1])) {
								criteria.andLessThan(columnName, s[1]);
							} else if (StringUtils.isNotEmpty(s[0]) && s.length == 1) {
								criteria.andGreaterThan(columnName, s[0]);
							} else {
								criteria.andBetween(columnName, s[0], s[1]);
							}
						}
						break;
						
					case LIST:
						if (proValue.getClass().isArray()) {
							criteria.andIn(columnName, Arrays.asList(proValue));
						} else if (proValue instanceof List) {
							criteria.andIn(columnName, (List<Object>) proValue);
						}
						break;
						
					case HOUSEAGE://房屋年代  例如: 0,15 代表 15年以内  15, 代表15年以上
						if (proValue instanceof String) {
							String[] s = ((String) proValue).split(",");
							Integer yearNum = DateUtils.getCurrentIntegerYear();
							Integer maxYear = yearNum - Integer.parseInt(s[0]) ;
							if (StringUtils.isNotEmpty(s[0]) && s.length == 1) {
								criteria.andGreaterThanOrEqualTo(columnName, maxYear);
							} else {
								Integer minYear = yearNum - Integer.parseInt(s[1]) ;
								criteria.andBetween(columnName, minYear, maxYear);
							}
						}
						break;
						
					case LISTANDRANGE:
						//此处要求数组里的元素对应的数据库字段是 数值 类型
						String list_cn = getDbPropStr(poClass, columnName);
						//过滤条件即包含数值 和 范围（，分隔）如：房源过滤1房，2房，3房以上为{"1"，"2"，"3，"}，数据库存的是房间数目
						if(proValue instanceof List){
						StringBuilder stringBuilder=((List<String>)proValue).stream().map(va->{

							StringBuilder sb = new StringBuilder(list_cn);
							if(va.contains(",")) {
								setStrBdrForSql(va, sb);
							} else{
								sb.append("=").append(va);
							}
							return sb;
							}).reduce((str1, str2) -> str1.append(" or ").append(str2)).get();
							criteria.andCondition("("+stringBuilder.toString()+")");
						}

						break;
						
					case ORPROPERTY:  // @DbPropertyType(type = FilterPropertyTypeEnum.ORPROPERTY,orProperty = {"LIKE: communityName","houseNo"})
						//此处要求数组里的元素对应的数据库字段是String类型
						final Object selectValue = proValue;
						StringBuilder strSql = Arrays.stream(ann.orProperty()).map(dbName -> {
							StringBuilder sb = new StringBuilder();
							if(dbName.contains(PRE_LIKE)){
								String actName = StringUtils.substringAfter(dbName,PRE_LIKE).trim();
								String dbProp = getDbPropStr(poClass, actName);
								sb.append(dbProp).append(" like '%").append(selectValue).append("%'");
							} else {
								String dbProp = getDbPropStr(poClass, dbName);
								sb.append(dbProp).append("= '").append(selectValue).append("'");
							}
							return sb;
						}).reduce((stringBuilder1, stringBuilder2) -> stringBuilder1.append(" or ").append(stringBuilder2)).get();
						criteria.andCondition("("+ strSql.toString() +")");
						break;
						
					case EACHORLIKE:
						String each_cn = getDbPropStr(poClass, columnName);

						//此处List每一个元素匹配，任1条件符合就可以，如：客户期望房型。数据库存 "1"，"2"，"3，"；过滤条件也是List "1","2"
						if(proValue instanceof List){
							StringBuilder stringBuilder=((List<String>)proValue).stream().map(va->{
								StringBuilder sb = new StringBuilder(each_cn);
								sb.append(" like '%").append(va).append("%'");
								return sb;
							}).reduce((str1,str2)->str1.append(" or ").append(str2)).get();
							criteria.andCondition("("+stringBuilder.toString()+")");
						}
						break;
						
					case ISNULL:
						criteria.andIsNull(columnName);
						break;
						
					case ISNOTNULL:
						criteria.andIsNotNull(columnName);
						break;
						
					default:
						break;
					}
			} catch (MapperException e) {
				log.error(e.getMessage());
			}

		}

	}
	
	/**
	 * 
	 * [根据字符串范围(,分隔)设置sql ，设置为stringbuilder] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param va
	 * @param sb <br>
	 */
	private static void setStrBdrForSql(String va, StringBuilder sb) {
		String[] value = va.split(",");
		if (StringUtils.isEmpty(value[0]) && (StringUtils.isNotEmpty(value[1]))) {
			sb.append("<=").append(value[1]);
		} else if (StringUtils.isNotEmpty(value[0]) && value.length == 1) {
			sb.append(">=").append(value[0]);
		} else {
			sb.append(" between ").append(value[0]).append(" and ").append(value[1]);
		}
	}
	
   /**
    * 
    * [分页执行操作，操作对象为每一个元素] <br> 
    *  
    * @author [li.qiong]<br>
    * @param consumer 这是需要操作的PO对象<br>
    */
	public static <Fiter,PO> void pageEachOperation(PageWrapper<FilterWrapper<Fiter>> filter, Mapper<PO> mapper,
	                                       Class<PO> poClazz, Consumer<PO> consumer){
		filter.setPageSize(PAGE_SIZE_OPERATION);
		Fiter filter2 = filter.getData().getFilter();
		for (int i = 0; ; i++) {
			filter.setPageNum(i);
			List<PO> list = DbFilterHandle.getFilterInfoList(filter2, mapper, poClazz);
			if(CollectionUtils.isEmpty(list)) {
				break;
			}
			//对每一个进行处理
			list.stream().forEach(consumer);
			PagingInfo<PO> listPages = new PagingInfo<>(list);
			if (listPages.isLastPage()) {
				break;
			}
		}
	}

	/**
	 * 
	 * [根据实体字段名获取数据库字段名] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param poClass
	 * @param columnName
	 * @return <br>
	 */
	@SuppressWarnings("rawtypes")
	private static String getDbPropStr(Class poClass, String columnName){

		Column ca = null;
		try {
			ca = (Column) poClass.getDeclaredField(columnName).getAnnotation(Column.class);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
			throw new InsideException(ErrorCodeEnum.IL90021034);
		} catch (SecurityException e) {
			log.error(e.getMessage());
			throw new InsideException(ErrorCodeEnum.IL90021020);
		}
		//获取表字段名
		return Optional.ofNullable(ca).map(va -> va.name()).orElse(columnName);
	}
	
	
	/**
	 * TODO   以上是按照规定格式对sql语句的基本拼接
	 */
	
	
	
	
	
	
	
	
	
	
	/**
	 * TODO  这是对于一个FO对象的删改查
	 * 
	 */
	
	/**
	 * 
	 * [通过对象FO来动态修改sql] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param po 和表对应的实体类
	 * @param fo where后面的条件
	 * @param mapper 
	 * @param clazz
	 * @return <br>
	 */
	public static <FO, PO> int updateByExampleSelective (PO po, FO fo, Mapper<PO> mapper, Class<PO> poClazz) {
		//创建example
		Example example = new Example(poClazz);
		try {
			setDynaSql(example, fo, poClazz);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		}
		int row = mapper.updateByExampleSelective(po, example);
		return row;
	}
	
	/**
	 * 
	 * [通过对象FO来动态删除sql] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <FO, PO> int deleteByExampleSelective(FO fo, Mapper<PO> mapper, Class<PO> clazz) {
		//创建example
		Example example = new Example(clazz);
		try {
			setDynaSql(example, fo, clazz);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		}
		int row = mapper.deleteByExample(example);
		return row;
	}
	
	
	/**
	 * 
	 * [通过对象FO来获取唯一的数据数据] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <FO, PO> PO getFilterInfo(FO fo, Mapper<PO> mapper,Class<PO> poClazz) {
		//创建example
		Example example = new Example(poClazz);
		try {
			setDynaSql(example, fo, poClazz);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		}
		//查询数据
		PO po = mapper.selectOneByExample(example);
		return po;
	}
	
	/**
	 * 
	 * [通过对象FO来查询符合条件的个数] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <PO,FO> int getFilterInfoNum(FO fo, Mapper<PO> mapper,Class<PO> poClazz) {
		//创建example
		Example example = new Example(poClazz);
		try {
			setDynaSql(example, fo, poClazz);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		}
		//查询数据
		int num = mapper.selectCountByExample(example);
		return num;
	}
	
	
	
	
	
	/**
	 * TODO 这是根据一个键值对的信息来增删改
	 */
	
	/**
	 * 
	 * [通过键值对信息修改数据] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <PO> int updateIdentInfo (String key, Object value, Mapper<PO> mapper, PO po) {
		//创建example
		Example example = new Example(po.getClass());
		Example.Criteria criteria =  example.createCriteria();
		criteria.andEqualTo(key,value);
		int row = mapper.updateByExampleSelective(po, example);
		return row;
	}
	
	/**
	 * 
	 * [通过键值信息,例如id,或者唯一编号查询唯一数据] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <PO> PO getIdentInfo(String key, Object value, Mapper<PO> mapper,Class<PO> poClazz) {
		//创建example
		Example example = new Example(poClazz);
		Example.Criteria criteria =  example.createCriteria();
		criteria.andEqualTo(key,value);
		//查询数据
		PO po = mapper.selectOneByExample(example);
		return po;
	}
	
	/**
	 * 
	 * [通过键值信息,查询多条数据] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <PO> List<PO> getIdentInfoList(String key, Object value, Mapper<PO> mapper,Class<PO> poClazz) {
		//创建example
		Example example = new Example(poClazz);
		Example.Criteria criteria =  example.createCriteria();
		criteria.andEqualTo(key,value);
		//查询数据
		List<PO> poList = mapper.selectByExample(example);
		return poList;
	}
	
	/**
	 * 
	 * [通过键值对查询符合条件的记录数] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param <PO>
	 * @return <br>
	 */
	public static <PO> int getIdentInfoNum(String key, Object value, Mapper<PO> mapper,Class<PO> poClazz) {
		//创建example
		Example example = new Example(poClazz);
		Example.Criteria criteria =  example.createCriteria();
		criteria.andEqualTo(key,value);
		//查询数据
		int num = mapper.selectCountByExample(example);
		return num;
	}
	
	
	
	
	
	
	/**
	 * TODO  通过多个键值对信息,组装成List<FieldCritor>,但是条件都是equals,如果有其他的条件,可以使用fo对象进行组装
	 */
	
	/**
	 * 
	 * [通过多个键值对信息获取信息] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param fieldCritor
	 * @return <br>
	 */
	public static <PO> List<PO> getManyIdentInfoList(List<FieldCritor> fieldCritorList , Mapper<PO> mapper,Class<PO> poClazz) {
		//创建example
		Example example = new Example(poClazz);
		Example.Criteria criteria =  example.createCriteria();
		if(CollectionListUtils.isNotEmpty(fieldCritorList)) {
			for (FieldCritor fieldCritor : fieldCritorList) {
				String fieldKey = fieldCritor.getFieldKey();
				if(StringUtils.isEmpty(fieldKey)) {
					throw new InsideException(ErrorCodeEnum.IL90021034);
				}
				criteria.andEqualTo(fieldCritor.getFieldKey(),fieldCritor.getFieldValue());
			}
			//查询数据
			List<PO> poList = mapper.selectByExample(example);
			return poList;
		} else {
			return mapper.selectAll();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * [自定义生成动态sql条件，注主要是设置criteria条件，不包含排序] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @version 2.0.0<br>
	 * @CreateDate 2019年4月12日 <br>
	 * @since v2.0.0<br>
	 * @see com.burylovehome.common.handle <br>
	 */
	public interface CustCritor<T>{
		public void CreCritor(Example example,T fo);
	}
}



