# Function Invocation Example

In this example a GemFire client will invoke remote functions registered on a GemFire server.

To run the example:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console. You may need to change the path to the jar file, depending on where you're running Gfsh from.
2) run the FunctionInvocationClient application.

# Running the examples

The example is broken up into multiple steps:
1. Insert (Put) three Customer entries into the `Customers` region using the repositories `save` method.
2. Execute the Customer function using `CustomerFunctionExecutions`.
3. Insert (Put) three Product entries into the `Products` region using the repositories `save` method.
4. Execute the Product function using `ProductFunctionExecutions`.
5. Insert (Put) 100 Order entries into the `Orders` region using the repositories `save` method.
6. Execute the Order function using `OrderFunctionExecutions`.
