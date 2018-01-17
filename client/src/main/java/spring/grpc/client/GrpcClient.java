package spring.grpc.client;

import com.google.common.net.HostAndPort;
import com.noticeboard.audit.service.grpc.Request;
import com.noticeboard.audit.service.grpc.TestServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import spring.grpc.entry.Entry;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
public class GrpcClient {

    private ManagedChannel channel;

    public GrpcClient(String hostAndPort) {
        this.channel = getGrpcChannel(hostAndPort);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown();
            }
        });
    }

    private ManagedChannel getGrpcChannel(String hostAndPort) {
        HostAndPort hp = HostAndPort.fromString(hostAndPort);
        return ManagedChannelBuilder.forAddress(hp.getHost(), hp.getPort())
                .usePlaintext(true)
                .build();
    }

    private void shutdown() {
        if (this.channel != null) {
            try {
                this.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                // ignore
            }
        }
    }

    public void test(Entry entry) {
        try {
            TestServiceGrpc.newBlockingStub(channel)
                    .getInactiveMembers(Request.newBuilder()
                            .setId((entry.getId() == null) ? -1 : entry.getId())
                            .setName(entry.getName())
                            .build());
        } catch (StatusRuntimeException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
