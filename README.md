jpa-criteria-bean
=================

A library to make search queries with JPA simple by using an annotated bean holding filter criteria to instruct a query builder.  Uses convention over configuration.  Should allow for queries which join to other entities.

Criteria Bean Notes
----------------------

### Objective

Make a search query with JPA simple using a bean holding filter criteria defined via annotations to instruct a query builder.  
Use convention over configuration.  Allow for queries which join to other entities.
	

### Examples

#### Example 1

To generate a query like the following:

		SELECT u FROM User u 
		INNER JOIN u.roles r
		WHERE u.active = true
		ORDER BY u.username;	

...you just need a bean like so:

		@CriteriaBean(rootEntity = User.class)
		public class UserFilter {

			@Filter
			private boolean active = true;
	
			@Order
			private String orderByProperty;
	
			// getters and setters...
	
		}

#### Example 2

A more complex example:

		SELECT o FROM Order o 
		INNER JOIN o.customer c
		WHERE o.placed >= {month ago} 
		AND o.status = 'SHIPPED'
		AND c.country = 'GB'
		ORDER BY c.lastname, c.firstname;

...achieved like so:
Consructed with a @CriteriaBean and an EntityManager 
getCountQuery() - returns a Query which can be executed to return the count of all records matching the filter criteria
getQuery() - returns a Query which can be executed to return records (in object form) matching the filter criteria 
		@CriteriaBean(type = Order.class)
		public class CustomerFilter {
	
			@Filter(operator = ComparisonOperator.GTE)
			private Calendar placed;
	
			@Filter 
			private String status;
	
			@Filter(joinFrom = "customer")
			private String country;
	
			@Order
			private List<OrderCriteria> orderByProperties;
			
	
			// getters and setters...

		}
		
		
#### Example 3

And one more with pagination this time:

		SELECT o FROM Order o 
		INNER JOIN o.customer c
		WHERE o.placed >= {month ago} 
		AND o.status = 'SHIPPED'
		AND c.country = 'GB'
		ORDER BY c.lastname, c.firstname
		LIMIT 20, 10;

...achieved like so:

		@CriteriaBean(type = Order.class)
		public class CustomerFilter {
	
			@Filter(operator = ComparisonOperator.GTE)
			private Calendar placed;
	
			@Filter 
			private String status;
	
			@Filter(joinFrom = "customer")
			private String country;
	
			@Order
			private List<OrderCriteria> orderByProperties; 
			
			@PageNumber 
			private int resultPageNumber = 2; 
			
			@PageSize
			privage int recordsPerPage = 10;
	
			// getters and setters...

		}
	
### Components

####	Annotations
		
@CriteriaBean
Target - TYPE
Params
	Class<T> rootEntity - the starting entity from which joins, if any, will originate

@Filter
Target - FIELD
Params
	String propertyName (Optional) - defaults to name of field on criteria bean
	ComparisonOperator operator (Option) - defaults to EQ
		enum ComparisonOperator { EQ, NEQ, GT, GTE, LT, LTE, NULL, NOT_NULL}
	String joinFrom (Optional) - defaults to rootEntity; ignored if no join specified
	JoinType joinType (Optional) - defaults to INNER, ignored if no join specified
	
@PaginationValues (Optional)
Target - FIELD
	Understands PaginationParams class in order to extract firstResult and maxResults for JPA query

@PageNumber @PageSize (Optional)
Target - FIELD of types int, long, Integer, Long
	Used to derive firstResult and maxResults for JPA query
	
@Order (Optional)
Target - FIELD of types String, List<String>, OrderCriteria or List<OrderCriteria>
	Used to indicate order criteria
Params
	boolean ascending (Optional) - defaults to true, ignored if OrderCriteria / List<OrderCriteria> is used
		class OrderCriteria { String propertyName; boolean ascending;  }
						
####	Query builder
		
Consructed with a @CriteriaBean and an EntityManager 
getCountQuery() - returns a Query which can be executed to return the count of all records matching the filter criteria
getQuery() - returns a Query which can be executed to return records (in object form) matching the filter criteria 

