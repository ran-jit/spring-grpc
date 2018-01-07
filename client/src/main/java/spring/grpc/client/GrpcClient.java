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

    private String hostAndPort;

    public GrpcClient(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    private ManagedChannel getGrpcChannel() {
        HostAndPort hp = HostAndPort.fromString(this.hostAndPort);
        return ManagedChannelBuilder.forAddress(hp.getHost(), hp.getPort())
                .usePlaintext(true)
                .build();
    }

    private void shutdown(ManagedChannel channel) {
        if (channel != null) {
            try {
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                // ignore
            }
        }
    }

    public void test(Entry entry) {
        ManagedChannel channel = null;
        try {
            channel = getGrpcChannel();
            TestServiceGrpc.newBlockingStub(channel)
                    .getInactiveMembers(Request.newBuilder()
                            .setId((entry.getId() == null) ? -1 : entry.getId())
                            .setName(entry.getName())
                            .build());

        } catch (StatusRuntimeException ex) {
            //LOGGER.error("Error, entry: {} :: message: {}", entry, ex.getMessage());
        } catch (Exception ex) {
            //LOGGER.error("Error, entry: {}", entry, ex);
        } finally {
            shutdown(channel);
        }
    }

}
