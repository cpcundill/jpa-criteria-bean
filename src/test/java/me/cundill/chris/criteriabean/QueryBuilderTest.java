package me.cundill.chris.criteriabean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class QueryBuilderTest {

	private QueryBuilder builder;
	
	@Before
	public void setup() {
		builder = new QueryBuilder(new ExampleCriteriaBean());
	}
	
	@Test
	public void shouldReturnQueryString() {
		String queryString = builder.buildQueryString();
		assertFalse(queryString.isEmpty());
		assertEquals("FROM ExampleEntity alias1 WHERE alias1.status = :param1", queryString); 
	}
	

}
