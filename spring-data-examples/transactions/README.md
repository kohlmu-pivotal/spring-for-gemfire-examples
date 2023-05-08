# Transactions Example

In this example a GemFire client will be configured to perform the basic CRUD operations within a transaction. First, it will do a successful transaction where entries are saved to the server, and then a failed transaction where all changes are reverted.

To run the example:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console.
2) run the TransactionalClient application.

## Running the example

The example is broken up into multiple steps:
1. Insert (Put) five Customer entries into the `Customers` region using the repositories `save` method.
2. Update the Customer for id=2 in a transaction. Recording the before and after Customer detail.
3. Fail a transaction to update the Customer with id=2. Recording the before and after Customer detail.
4. Update the Customer for id=2 with a delay of 1000 ms.
5. Update the Customer for id=2 with a delay of 10 ms.
