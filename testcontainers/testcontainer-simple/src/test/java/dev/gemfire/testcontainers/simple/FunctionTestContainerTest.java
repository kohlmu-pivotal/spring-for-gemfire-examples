package dev.gemfire.testcontainers.simple;

import com.vmware.gemfire.testcontainers.GemFireCluster;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.Pool;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnServer;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctionExecutions;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctions;
import org.springframework.data.gemfire.function.execution.GemfireOnServerFunctionTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.utility.MountableFile;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FunctionTestContainerTest.SimpleSpringGemFireClient.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FunctionTestContainerTest {

  private static GemFireCluster gemFireCluster;

  @Autowired
  ClientCache clientCache;

  @BeforeAll
  public static void startGemFireCluster() {
    gemFireCluster = new GemFireCluster("gemfire/gemfire:10.1-jdk17", 1, 1)
        .withPreStart(GemFireCluster.ALL_GLOB, container -> container.copyFileToContainer(MountableFile.forHostPath(System.getProperty("test.jar.file")), "/testJar.jar"));

    gemFireCluster.acceptLicense().start();
    gemFireCluster.gfsh(false, "deploy --jar=/testJar.jar");
    System.setProperty("spring.data.gemfire.pool.locators", String.format("localhost[%d]", gemFireCluster.getLocatorPort()));

  }

  @AfterClass
  public static void stopGemFireCluster() {
    System.setProperty("spring.data.gemfire.pool.locators","0");
    gemFireCluster.close();
  }

  @Test
  public void simpleFunctionTest() {
    GemfireOnServerFunctionTemplate gemfireOnServerFunctionTemplate = new GemfireOnServerFunctionTemplate(clientCache.getDefaultPool());
    String serverTime = gemfireOnServerFunctionTemplate.execute("ServerTimeFunction").toString();
    assertThat(serverTime).contains("Server time");
  }

  @SpringBootApplication
  @ClientCacheApplication()
  static class SimpleSpringGemFireClient {

  }
}
