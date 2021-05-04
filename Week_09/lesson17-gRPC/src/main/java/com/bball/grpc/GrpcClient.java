package com.bball.grpc;

import com.bball.protobuf.GreeterGrpc;
import com.bball.protobuf.HelloWord;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class GrpcClient {
    private ManagedChannel channel;
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    public GrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws Exception {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        GrpcClient client = new GrpcClient("127.0.0.1", 50011);
        for (int i = 0; i < 5; i++) {
            client.greet("bball" + i);
        }
    }

    public void greet(String name) {

        log.info("Will try to greet " + name + " ...");
        HelloWord.HelloRequest request = HelloWord.HelloRequest.newBuilder().setName(name).build();
        HelloWord.HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return;
        }
        log.info("Greeting: " + response.getMessage());
    }
}
