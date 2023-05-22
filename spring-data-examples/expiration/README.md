# Expiration Example

In this com we will show you ways to automatically expire data from your region.

1. Expiration removes data after it has existed for a certain amount of time or after it has been unused for a certain amount of time. There are multiple ways to configure Expiration that will be shown in this com.
    1. Configure an eviction policy on the region using the `@EnableExpiration` and `@ExpirationPolicy` annotations.
    2. Configure a custom eviction policy by extending `CustomExpiry`.
    3. Entity defined expiration using the `@IdleTimeoutExpiration` and `@TimeToLiveExpiration` annotations.
    
Entity defined expiration has its own client because it has a lower priority and is trumped by the expiration policy defined on the `@EnableExpiration` annotation. The polices defined by the `@EnableExpiration` annotation can be found in ExpirationPolicyConfig.

To run the com:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console.
2) run the ExpirationClient application or the EntityDefinedExpirationClient application.

# What the example does

The com is broken up into multiple steps, for each form of expiration, the steps are the same:
1. Insert (Put) one entry into the region using the repositories `save` method.
2. Keep the entry active by constantly polling and wait for the TTL to expire.
3. Insert (Put) one entry into the region using the repositories `save` method.
4. Don't poll as often and let the idle timeout expire.

# Steps to run

1. `cd` into the expiration directory
2. Run `run --file=start.gfsh` in Gfsh
3. Run the expiration client with `gradle run`
4. Run the entity defined expiration client with `gradle runEntity`

# Expected output

```
--- {expiration type} ---
Starting TTL wait period: 2023-05-22T19:51:21.994873Z
Ending TTL wait period: 2023-05-22T19:51:23.030213Z
Starting Idle wait period: 2023-05-22T19:51:23.037180Z
Ending Idle wait period: 2023-05-22T19:51:25.045327Z
```