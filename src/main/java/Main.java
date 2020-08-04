import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public final class Main {

    public static void main(String[] args) throws Exception {
        final var registry = new CollectorRegistry();
        new HTTPServer(new InetSocketAddress(80), registry, true);
        final var counter = new Counter.Builder()
            .name("simple_counter")
            .help("counter example")
            .create();
        registry.register(counter);
        while (true) {
            counter.inc();
            TimeUnit.SECONDS.sleep(1L);
        }
    }
}
