package com.bball.grpc;

import com.bball.protobuf.GreeterGrpc;
import com.bball.protobuf.HelloWord;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcServer {
    private int port = 50011;
    private Server server;

    private void start() throws Exception {
        server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
        log.info("GrpcServer start ...");
        // jvm关闭时回调关闭gRPC服务
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();
                log.info("shutting down gRPC server since JVM is shutting down");
                GrpcServer.this.stop();
                log.info("server shut down");
            }

        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // 阻塞直到gRPC服务关闭
    private void blockUntilShutdown() throws Exception {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    }

    class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloWord.HelloRequest req, StreamObserver<HelloWord.HelloReply> responseObserver) {
            HelloWord.HelloReply reply = HelloWord.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
