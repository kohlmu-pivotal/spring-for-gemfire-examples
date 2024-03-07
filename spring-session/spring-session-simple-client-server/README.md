# Simple Spring Session Client-Server Example

## Description
This example is a simple Spring Session example, where there is a GemFire client initialized within a Spring Boot web app, that will support session information management. This example is set up to expire session information within 30 second of it being committed. This 30 second expiration is for demonstration purposes only.

## Prerequisites
In order to run the following example one requires the following to be set up:
* Java 17
* Latest VMware GemFire is installed locally
* Set up local `gradle` or `maven` environment by following Steps 1-6 and 8 from [Spring Boot For VMware GemFire Quickstart](https://docs.vmware.com/en/Spring-Boot-for-VMware-GemFire/index.html#spring-boot-for-vmware-gemfire-quick-start-0)

## Steps to run
1. Clone `Spring For GemFire Examples` project
2. Start GemFire server-side processes 
   1. Open new Terminal
   2. `cd` into the `Simple Spring Session Client Server` project directory, `spring-examples/spring-session/simple-spring-session-client-server`
   3. Start `gfsh` located in the `{VMware_GemFire_installation_location}/bin` directory
   4. Launch the Locator and Server by running `start.gfsh` file located in the `Simple Spring Session Client Server` project directory in `gfsh`. <br> e.g `run --file={pathToStartGfshFile}/start.gfsh`
   5. Confirm that the locator and server are running by running `list members` in `gfsh`, which should contain two entries.
   6. Confirm the session information region has been created correctly, by running `list regions` in `gfsh` which should contain a Region entry `ClusteredSpringSessions`
3. Start Client
   1. Open a new Terminal
   2. `cd` into the `Simple Spring Session Client Server` project directory
   3. Run `./gradlew clean tomcatRun`
4. Open a Browser and navigate to `http://localhost:8080`
5. One can now enter some Session Attributes, confirming that they reflect in the table labelled `Submitted Attributes`
6. Wait 30 seconds and refresh the page. The table should now be empty, as the session has been expired and the session, with populated attributes, has been discarded.


## Expected Outcome
In this example the web app writes some attributes into the session. This information will be replicated from the web app, through the VMware GemFire client to the VMware GemFire server cluster. 

After a while, in this case 30 seconds, the session will expire on the server-side, thus causing the session, with all information to be discarded.


## Shut down and Cleanup
Run the following commands to shut down and cleanup:
1. In the Client Terminal window
   1. `Ctrl+C` to kill the running client app
2. In the Server Terminal window (assuming `gfsh` is still active):
   1. Run `shutdown --include-locators`
   2. Exit from the `gfsh` by running `quit`
   3. Delete the created directories `sessionLocator` and `sessionServer`
