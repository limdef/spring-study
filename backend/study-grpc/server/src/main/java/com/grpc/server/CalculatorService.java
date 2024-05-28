package com.grpc.server;

import io.grpc.stub.StreamObserver;

public class CalculatorService extends CalculatorGrpc.CalculatorImplBase {
    @Override
    public void add(CalculatorProto.AddRequest request, StreamObserver<CalculatorProto.AddResponse> responseObserver) {
        int result = request.getNum1() + request.getNum2();
        CalculatorProto.AddResponse response = CalculatorProto.AddResponse.newBuilder()
                .setResult(result)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void subtract(CalculatorProto.SubtractRequest request, StreamObserver<CalculatorProto.SubtractResponse> responseObserver) {
        int result = request.getNum1() - request.getNum2();
        CalculatorProto.SubtractResponse response = CalculatorProto.SubtractResponse.newBuilder()
                .setResult(result)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
