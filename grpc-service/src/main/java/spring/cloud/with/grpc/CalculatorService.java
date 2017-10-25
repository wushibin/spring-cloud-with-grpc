package spring.cloud.with.grpc;

import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

/**
 * @author wushibin on 17/10/18.
 */
@GrpcService(value = CalculatorService.class, interceptors = LogInterceptor.class)
public class CalculatorService extends CalculatorGrpc.CalculatorImplBase {

    @Override
    public void calculate(spring.cloud.with.grpc.CalculatorOuterClass.CalculatorRequest request,
                          io.grpc.stub.StreamObserver<spring.cloud.with.grpc.CalculatorOuterClass.CalculatorResponse> responseObserver) {

        CalculatorOuterClass.CalculatorResponse.Builder resultBuilder = CalculatorOuterClass.CalculatorResponse.newBuilder();
        switch (request.getOperation()){
            case ADD:
                resultBuilder.setResult(request.getNumber1()+request.getNumber2());
                break;
            case SUBTRACT:
                resultBuilder.setResult(request.getNumber1()-request.getNumber2());
                break;
            case MULTIPLY:
                resultBuilder.setResult(request.getNumber1()*request.getNumber2());
                break;
            case DIVIDE:
                resultBuilder.setResult(request.getNumber1()/request.getNumber2());
                break;
            case UNRECOGNIZED:
                break;
        }
        responseObserver.onNext(resultBuilder.build());
        responseObserver.onCompleted();
    }

}
