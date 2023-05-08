package example.springdata.gemfire.client.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionalClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TransactionalClient.class).web(WebApplicationType.NONE).build().run(args);
    }

    @Autowired
    private CustomerService customerService;

    @Bean
    ApplicationRunner runner() {
        return args -> {
            System.out.println("Number of Entries stored before = " + customerService.numberEntriesStoredOnServer());
            customerService.createFiveCustomers();
            assert customerService.numberEntriesStoredOnServer() ==  5;
            System.out.println("Number of Entries stored after = " + customerService.numberEntriesStoredOnServer());
            System.out.println("Customer for ID before (transaction commit success) = " + customerService.findById(2L).get());
            customerService.updateCustomersSuccess();
            assert customerService.numberEntriesStoredOnServer() ==  5;
            Customer customer = customerService.findById(2L).get();
            assert customer.firstName().equals("Humpty");
            System.out.println("Customer for ID after (transaction commit success) = " + customer);

            try {
                customerService.updateCustomersFailure();
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }

            customer = customerService.findById(2L).get();
            assert customer.firstName().equals("Humpty");
            System.out.println("Customer for ID after (transaction commit failure) = " + customerService.findById(2L).get());

            Customer numpty = new Customer(2L, new EmailAddress("2@2.com"), "Numpty", "Hamilton");
            Customer frumpy = new Customer(2L, new EmailAddress("2@2.com"), "Frumpy", "Hamilton");
            customerService.updateCustomersWithDelay(1000, numpty);
            customerService.updateCustomersWithDelay(10, frumpy);
            customer = customerService.findById(2L).get();
            assert customer.equals(frumpy);
            System.out.println("Customer for ID after 2 updates with delay = " + customer);
        };
    }
}
