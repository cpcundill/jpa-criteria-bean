package me.cundill.chris.criteriabean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.cundill.chris.criteriabean.annotation.CriteriaBean;
import me.cundill.chris.criteriabean.annotation.Filter;

public class AnnotationReader {

	private Object bean;
	
	public AnnotationReader(Object bean) {
		if (!bean.getClass().isAnnotationPresent(CriteriaBean.class))
			throw new IllegalArgumentException("The provided bean " + bean + " is not annotated with @CriteriaBean");
		this.bean = bean;
	}
	
	public Class<?> getRootEntity() {
		CriteriaBean annotation = bean.getClass().getAnnotation(CriteriaBean.class);
		return annotation.rootEntity();
	}
	
	
	public List<FilterDefinition> getFilterDefinitions() {
		List<FilterDefinition> filters = new ArrayList<FilterDefinition>();
		for (Field field : getFilterFields()) {
			Filter filter = field.getAnnotation(Filter.class);
			FilterDefinition filterDef = new FilterDefinition();
			filterDef.setPropertyName(!filter.propertyName().isEmpty() ? filter.propertyName() : field.getName());
			filterDef.setOperator(filter.operator());
			filters.add(filterDef);
		}
		return filters;
	}
	
	protected List<Field> getFilterFields() {
		List<Field> filters = new ArrayList<Field>();
		for (Field f : bean.getClass().getDeclaredFields()) {
			if (f.isAnnotationPresent(Filter.class)) filters.add(f);
		}
		return filters;
	}
	
}
