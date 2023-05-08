# Eviction Example

In this example we will show you ways to automatically evict data from your region if certain entry count or memory thresholds are reached.

To run the example:
1) first start a GemFire cluster by running the commands in `start.gfsh` in your Gfsh console.
2) run the EvictionClient application.

# Running the examples

The example is broken up into multiple steps:
1. Insert (Put) thirteen Order entries into the `Orders` region using the repositories `save` method.
2. Assert that there are only 10 (expiration threshold) entries.