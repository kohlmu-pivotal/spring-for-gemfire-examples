# Eviction Example

In this com we will show you ways to automatically evict data from your region if certain entry count or memory thresholds are reached.

# What the example does

The com is broken up into multiple steps:
1. Insert (Put) thirteen Order entries into the `Orders` region using the repositories `save` method.
2. Assert that there are only 10 (expiration threshold) entries.

# Steps to run

1. `cd` into the eviction directory
2. Run `run --file=start.gfsh` in Gfsh
3. Run the expiration client with `gradle run`

# Expected output

```
Inserting 13 entries
Asserting that there are only 10 entries
Entries: 10
```