# Queries Example

In this com we will demonstrate different kinds of queries. The kinds of queries we will demonstrate are listed below.
1. OQL Queries - OQL is similar to SQl and can be used to query regions like SQl queries tables in a relational database.
2. Continuous Queries - Continuous queries are special OQl queries that run continuously and are updated when th returned result set changes (like when data is added/removed on the region)

To run the com:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console.
2) run the QueryClient application.


# What the example does

The com is broken up into multiple steps:
1. Insert (Put) three Customer entries into the `Customers` region using the repositories `save` method.
2. Check that the CQ has triggered for all three entries.
3. Query for all Customers with email `3@3.com`, which should return two customers.
4. Query for all Customers with first name `Frank`, which should return no customers.

# Steps to run

1. `cd` into the queries directory
2. Run `run --file=start.gfsh` in Gfsh
3. Run the query client with `gradle run`

# Expected output

```
--- Continuous Queries ---
Inserting 3 entries for keys: 1, 2, 3
Received message for CQ 'CustomerCQ'CqEvent [CqName=CustomerCQ; base operation=CREATE; cq operation=CREATE; key=2; value=Customer[id=2, emailAddress=EmailAddress[value=3@3.com], firstName=Frank, lastName=Lamport]]
Received message for CQ 'CustomerCQ'CqEvent [CqName=CustomerCQ; base operation=CREATE; cq operation=CREATE; key=1; value=Customer[id=1, emailAddress=EmailAddress[value=2@2.com], firstName=John, lastName=Smith]]
Received message for CQ 'CustomerCQ'CqEvent [CqName=CustomerCQ; base operation=CREATE; cq operation=CREATE; key=3; value=Customer[id=3, emailAddress=EmailAddress[value=5@5.com], firstName=Jude, lastName=Simmons]]
--- OQL Queries ---
Inserting 3 entries for keys: 1, 2, 3
Find customer with key=2 using GemFireRepository: Customer[id=2, emailAddress=EmailAddress[value=3@3.com], firstName=Frank, lastName=Lamport]
Find customer with key=2 using GemFireTemplate: [Customer[id=2, emailAddress=EmailAddress[value=3@3.com], firstName=Frank, lastName=Lamport]]
Find customers with emailAddress=3@3.com: [Customer[id=2, emailAddress=EmailAddress[value=3@3.com], firstName=Frank, lastName=Lamport], Customer[id=1, emailAddress=EmailAddress[value=3@3.com], firstName=Jude, lastName=Smith]]
Find customers with firstName=Frank: [Customer[id=2, emailAddress=EmailAddress[value=3@3.com], firstName=Frank, lastName=Lamport]]
Find customers with firstName=Jude: [Customer[id=1, emailAddress=EmailAddress[value=3@3.com], firstName=Jude, lastName=Smith], Customer[id=3, emailAddress=EmailAddress[value=5@5.com], firstName=Jude, lastName=Simmons]]
```