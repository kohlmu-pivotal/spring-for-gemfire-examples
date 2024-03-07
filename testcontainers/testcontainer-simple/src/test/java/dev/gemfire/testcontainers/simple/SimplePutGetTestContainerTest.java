package dev.gemfire.testcontainers.simple;

import com.vmware.gemfire.testcontainers.GemFireCluster;
import dev.gemfire.testcontainers.function.User;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimplePutGetTestContainerTest.SimpleSpringGemFireClient.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SimplePutGetTestContainerTest {

  private static GemFireCluster gemFireCluster;

  @Autowired
  GemfireTemplate gemfireTemplate;

  @BeforeAll
  public static void startGemFireCluster() {
    gemFireCluster = new GemFireCluster("gemfire/gemfire:10.1-jdk17", 1, 1)
        .withGfsh(false, "create region --name=User --type=PARTITION")
        .withPdx("dev\\.gemfire\\.testcontainer\\.simple\\.User", true);

    gemFireCluster.acceptLicense().start();

    System.setProperty("spring.data.gemfire.pool.locators", String.format("localhost[%d]", gemFireCluster.getLocatorPort()));

  }

  @AfterClass
  public static void stopGemFireCluster() {
    System.setProperty("spring.data.gemfire.pool.locators","0");
    gemFireCluster.close();
  }

  @Test
  public void simplePutTest() {
    gemfireTemplate.put("user1", new User("Bob Flinn", 33));
    assertThat(Optional.ofNullable(gemfireTemplate.get("unknownUser"))).isEmpty();
    Optional<User> user = Optional.ofNullable(gemfireTemplate.get("user1"));
    assertThat(user).isNotEmpty();
    assertThat(user.get().name()).isEqualTo("Bob Flinn");
    assertThat(user.get().age()).isEqualTo(33);
  }

  @SpringBootApplication
  @ClientCacheApplication()
  @EnableEntityDefinedRegions(basePackageClasses = User.class,clientRegionShortcut = ClientRegionShortcut.PROXY)
  @EnablePdx
  static class SimpleSpringGemFireClient {

  }
}
