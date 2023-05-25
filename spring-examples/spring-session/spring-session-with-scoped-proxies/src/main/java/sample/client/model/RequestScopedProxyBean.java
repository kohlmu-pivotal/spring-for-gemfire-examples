package sample.client.model;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedProxyBean {

    private static final AtomicInteger INSTANCE_COUNTER = new AtomicInteger(0);

    private final int count;

    public RequestScopedProxyBean() {
        this.count = INSTANCE_COUNTER.incrementAndGet();
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("{ @type = '%s', count = %d }", getClass().getName(), getCount());
    }
}
