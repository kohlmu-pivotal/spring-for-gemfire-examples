package example.springdata.gemfire.eviction;

import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EvictionClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EvictionClient.class).web(WebApplicationType.NONE).build().run(args);
    }

    @Bean
    public ApplicationRunner runner(OrderRepository orderRepository, @Qualifier("Orders") Region<Long, Order> orderRegion) {
        return args -> {
            final int evictionThreshold = 10;
            System.out.println("Inserting " + (evictionThreshold + 3) + " entries");
            for (long i = 0; i < evictionThreshold + 3; i++) {
                orderRepository.save(new Order(i, i,
                        new Address("123 Main St", "New York", "Spain")));
            }

            System.out.println("Asserting that there are only " + evictionThreshold + " entries");
            assert orderRegion.size() == evictionThreshold;

            System.out.println("Entries: " + orderRegion.size());

            orderRepository.deleteAll();
        };
    }
}
