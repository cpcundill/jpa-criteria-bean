package me.cundill.chris.criteriabean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AnnotationReaderTest {

	private AnnotationReader reader;
	
	@Before
	public void setup() {
		reader = new AnnotationReader(new ExampleCriteriaBean());
	}
	
	@Test
	public void shouldAcceptValidBean() {
		assertNotNull(reader);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectInvalidBean() {
		new AnnotationReader(new Object());
	}
	
	@Test
	public void canDetermineRootEntity() {
		assertEquals(ExampleEntity.class, reader.getRootEntity());
	}
	
	@Test
	public void shouldReturnFilterFields() {
		assertFalse("Should find at least one @Filter field!", reader.getFilterFields().isEmpty());
	}
	
	@Test
	public void shouldReturnFilterDefinitions() {
		assertFalse("Should find at least one @Filter field!", reader.getFilterDefinitions().isEmpty());
	}
	

	
}
