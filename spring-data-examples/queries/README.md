# Queries Example

In this example we will demonstrate different kinds of queries. The kinds of queries we will demonstrate are listed below.
1. OQL Queries - OQL is similar to SQl and can be used to query regions like SQl queries tables in a relational database.
2. Continuous Queries - Continuous queries are special OQl queries that run continuously and are updated when th returned result set changes (like when data is added/removed on the region)

To run the example:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console.
2) run the QueryClient application.


## Running the example

The example is broken up into multiple steps:
1. Insert (Put) three Customer entries into the `Customers` region using the repositories `save` method.
2. Check that the CQ has triggered for all three entries.
3. Query for all Customers with email `3@3.com`, which should return two customers.
4. Query for all Customers with first name `Frank`, which should return no customers.