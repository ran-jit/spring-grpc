package spring.grpc.service.grpc;

import com.noticeboard.audit.service.grpc.Request;
import com.noticeboard.audit.service.grpc.Response;
import com.noticeboard.audit.service.grpc.TestServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import spring.grpc.entry.Entry;
import spring.grpc.exception.CustomException;
import spring.grpc.manager.Manager;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@net.devh.springboot.autoconfigure.grpc.server.GrpcService(GrpcService.class)
public class GrpcService extends TestServiceGrpc.TestServiceImplBase {

    private static final Logger LOGGER = LogManager.getLogger(GrpcService.class);

    private Manager manager;

    @Autowired
    public GrpcService(@Qualifier(value = "managerImpl") Manager manager) {
        this.manager = manager;
    }

    @Override
    public void getInactiveMembers(Request request, StreamObserver<Response> responseObserver) {
        try {
            manager.test(Entry.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .build());

            Response response = Response.newBuilder().setSuccess(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (CustomException ex) {
            LOGGER.error("Failed, request: {} :: message: {}", request, ex.getMessage());
            responseObserver.onError(Status.INTERNAL.withDescription(ex.getMessage()).asRuntimeException());
        } catch (Exception ex) {
            LOGGER.error("Failed, request: {}", request, ex);
            responseObserver.onError(ex);
        }
    }

}
