# Transactions Example

In this com a GemFire client will be configured to perform the basic CRUD operations within a transaction. First, it will do a successful transaction where entries are saved to the server, and then a failed transaction where all changes are reverted.

To run the com:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console.
2) run the TransactionalClient application.

# What the example does

The com is broken up into multiple steps:
1. Insert (Put) five Customer entries into the `Customers` region using the repositories `save` method.
2. Update the Customer for id=2 in a transaction. Recording the before and after Customer detail.
3. Fail a transaction to update the Customer with id=2. Recording the before and after Customer detail.
4. Update the Customer for id=2 with a delay of 1000 ms.
5. Update the Customer for id=2 with a delay of 10 ms.

# Steps to run

1. `cd` into the transactions directory
2. Run `run --file=start.gfsh` in Gfsh
3. Run the transactional client with `gradle run`

# Expected output

```
Number of Entries stored before = 0
Number of Entries stored after = 5
Customer for ID before (transaction commit success) = Customer[id=2, emailAddress=EmailAddress[value=2@2.com], firstName=Franky, lastName=Hamilton]
Customer for ID after (transaction commit success) = Customer[id=2, emailAddress=EmailAddress[value=2@2.com], firstName=Humpty, lastName=Hamilton]
Customer for ID after (transaction commit failure) = Customer[id=2, emailAddress=EmailAddress[value=2@2.com], firstName=Humpty, lastName=Hamilton]
java.lang.IllegalArgumentException: This is an expected exception that should fail the transactions
	at dev.gemfire.client.transactions.service.CustomerService.updateCustomersFailure(CustomerService.java:71)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:343)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:391)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:702)
	at dev.gemfire.client.transactions.service.CustomerService$$SpringCGLIB$$0.updateCustomersFailure(<generated>)
	at dev.gemfire.client.transactions.TransactionalClient.lambda$runner$0(TransactionalClient.java:42)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:760)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:750)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:317)
	at dev.gemfire.client.transactions.TransactionalClient.main(TransactionalClient.java:24)
Customer for ID after 2 updates with delay = Customer[id=2, emailAddress=EmailAddress[value=2@2.com], firstName=Frumpy, lastName=Hamilton]
```
