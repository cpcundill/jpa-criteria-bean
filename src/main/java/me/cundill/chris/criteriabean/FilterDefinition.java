package me.cundill.chris.criteriabean;

import me.cundill.chris.criteriabean.annotation.Operator;

public class FilterDefinition {

	private String propertyName;
	private Operator operator;
	private Object value;
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
