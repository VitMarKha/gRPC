package org.vitmarkha;

import io.grpc.stub.StreamObserver;
import org.vitmarkha.grpc.GreetingServiceGrpc;
import org.vitmarkha.grpc.GreetingServiceOuterClass;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {

        System.out.println("We are in greeting method, request: " + request);

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e ) {
                e.printStackTrace();
            }

            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass
                    .HelloResponse.newBuilder()
                    .setGreeting("Hello from Java server, " + request.getName())
                    .build();

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
