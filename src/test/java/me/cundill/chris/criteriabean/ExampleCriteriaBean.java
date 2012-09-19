package me.cundill.chris.criteriabean;

import me.cundill.chris.criteriabean.annotation.CriteriaBean;
import me.cundill.chris.criteriabean.annotation.Filter;

@CriteriaBean(rootEntity = ExampleEntity.class)
public class ExampleCriteriaBean {

	@Filter
	private String status = "COMPLETE";
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
