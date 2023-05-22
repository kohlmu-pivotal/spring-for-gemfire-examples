# Function Invocation Example

In this com a GemFire client will invoke remote functions registered on a GemFire server.

To run the com:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console. You may need to change the path to the jar file, depending on where you're running Gfsh from.
2) run the FunctionInvocationClient application.

# What the example does

The com is broken up into multiple steps:
1. Insert (Put) three Customer entries into the `Customers` region using the repositories `save` method.
2. Execute the Customer function using `CustomerFunctionExecutions`.
3. Insert (Put) three Product entries into the `Products` region using the repositories `save` method.
4. Execute the Product function using `ProductFunctionExecutions`.
5. Insert (Put) 100 Order entries into the `Orders` region using the repositories `save` method.
6. Execute the Order function using `OrderFunctionExecutions`.

# Steps to run

1. `cd` into the function-invocation directory
2. Run `gradle build`
3. Run `run --file=start.gfsh` in Gfsh and press [Enter] when prompted
4. Run the function with `gradle run`

# Expected output

```
All customers for emailAddresses:3@3.com,2@2.com using function invocation: 
	 [Customer{id=1, emailAddress=EmailAddress{value='2@2.com'}, firstName='John', lastName='Smith'}, Customer{id=2, emailAddress=EmailAddress{value='3@3.com'}, firstName='Frank', lastName='Lamport'}]
Running function to sum up all product prices: 1499.97
Running function to sum up all order lineItems prices for order 1: 4599.94
For order: 
	 Order{id=1, customerId=3, billingAddress=Address{street='it', city='doesn't', country='matter'}, shippingAddress=Address{street='it', city='doesn't', country='matter'}, lineItems=[LineItem{product=Product{id=3, name='Apple macBook', price=899.99, description='An Apple notebook computer', attributes={}}, amount=2}, LineItem{product=Product{id=2, name='Apple iPad', price=499.99, description='An Apple tablet device', attributes={}}, amount=2}, LineItem{product=Product{id=3, name='Apple macBook', price=899.99, description='An Apple notebook computer', attributes={}}, amount=2}]}
```