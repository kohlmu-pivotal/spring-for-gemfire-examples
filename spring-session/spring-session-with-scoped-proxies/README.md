# Spring Session example with RequestScoped and SessionScoped Beans

## Description
In this example, the usage of two differently scoped session beans will be demonstrated: [Session Scoped](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/factory-scopes.html#beans-factory-scopes-session) and [Request Scoped](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/factory-scopes.html#beans-factory-scopes-request) beans.

## Prerequisites
In order to run the following example one requires the following to be set up:
* Java 17
* Latest VMware GemFire is installed locally
* Set up local `gradle` or `maven` environment by following Steps 1-6 and 8 from [Spring Boot For VMware GemFire Quickstart](https://docs.vmware.com/en/Spring-Boot-for-VMware-GemFire/index.html#spring-boot-for-vmware-gemfire-quick-start-0)

## Steps to run
1. Clone `Spring For GemFire Examples` project
2. Start GemFire server-side processes 
   1. Open new Terminal
   2. `cd` into the `Spring Session With Scoped Proxies` project directory, `spring-examples/spring-session/spring-session-with-scoped-proxies`
   3. Start `gfsh` located in the `{VMware_GemFire_installation_location}/bin` directory
   4. Launch the Locator and Server by running `start.gfsh` file located in the `Simple Spring Session Client Server` project directory in `gfsh`. <br> e.g `run --file={pathToStartGfshFile}/start.gfsh`
   5. Confirm that the locator and server are running by running `list members` in `gfsh`, which should contain two entries.
   6. Confirm the session information region has been created correctly, by running `list regions` in `gfsh` which should contain a Region entry `ClusteredSpringSessions`
3. Start Client
   1. Open a new Terminal
   2. `cd` into the `Spring Session With Scoped Proxies` project directory
   3. Run `./gradlew clean bootRun`
4. Open a Browser and navigate to `http://localhost:8080/counts`
5. On this website it shows the number of created Session scoped beans and the number of Request scoped beans. Once you've loaded the web page, wait 10 seconds and refresh the website. One will see that the number of Session scoped beans and Request scoped beans will start to differ. Due to the reasons already described here: [Session Scoped](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/factory-scopes.html#beans-factory-scopes-session) and [Request Scoped](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/factory-scopes.html#beans-factory-scopes-request)


## Expected Outcome
In this example the web app will display the number of Session scoped and Request scoped beans. This counts for the number of created beans will differ between the Session and Request scoped beans, primarily because Request scoped beans will only be created upon request the request, the number of times the web page is refreshed or the request from the web page is made. Please refer to the documentation about the difference between the two types of beans, in the documentation [Session Scoped](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/factory-scopes.html#beans-factory-scopes-session) and [Request Scoped](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/factory-scopes.html#beans-factory-scopes-request).

When running the web app for the first time, the count of Session scoped and Request scoped beans should be equal to `1` each. If one waits 10 seconds and then refreshes the web page again, one will notice that the number of Session beans and Request beans differ. The Request beans should be equal to the number of times the page was refreshed and in this specific instance would be `2`, whilst the Session scoped bean will be larger and different number than the Request scoped bean count.


## Shut down and Cleanup
Run the following commands to shut down and cleanup:
1. In the Client Terminal window
   1. `Ctrl+C` to kill the running client app
2. In the Server Terminal window (assuming `gfsh` is still active):
   1. Run `shutdown --include-locators`
   2. Exit from the `gfsh` by running `quit`
   3. Delete the created directories `sessionLocator` and `sessionServer`
