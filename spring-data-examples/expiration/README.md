# Expiration Example

In this example we will show you ways to automatically expire data from your region.

1. Expiration removes data after it has existed for a certain amount of time or after it has been unused for a certain amount of time. There are multiple ways to configure Expiration that will be shown in this example.
    1. Configure an eviction policy on the region using the `@EnableExpiration` and `@ExpirationPolicy` annotations.
    2. Configure a custom eviction policy by extending `CustomExpiry`.
    3. Entity defined expiration using the `@IdleTimeoutExpiration` and `@TimeToLiveExpiration` annotations.
    
Entity defined expiration has its own client because it has a lower priority and is trumped by the expiration policy defined on the `@EnableExpiration` annotation. The polices defined by the `@EnableExpiration` annotation can be found in ExpirationPolicyConfig.

To run the example:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console.
2) run the ExpirationClient application or the EntityDefinedExpirationClient application.

# Running the examples

The example is broken up into multiple steps, for each form of expiration, the steps are the same:
1. Insert (Put) one entry into the region using the repositories `save` method.
2. Keep the entry active by constantly polling and wait for the TTL to expire.
3. Insert (Put) one entry into the region using the repositories `save` method.
4. Don't poll as often and let the idle timeout expire.