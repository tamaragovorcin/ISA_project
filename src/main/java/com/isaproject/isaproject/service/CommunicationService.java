package com.isaproject.isaproject.service;

import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.service.IMedicineService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.grpc.MessageProto;
import rs.ac.uns.ftn.grpc.MessageResponseProto;
import rs.ac.uns.ftn.grpc.SpringGrpcServiceGrpc;

import java.util.List;
import java.util.UUID;

@GrpcService
public class CommunicationService extends SpringGrpcServiceGrpc.SpringGrpcServiceImplBase {
    @Autowired
    private IMedicineService service;

    @Override
    public void communicate(MessageProto request, StreamObserver<MessageResponseProto> responseObserver) {
        String desc = "";
       List<Medicine> medicines = service.getAll();

        for (Medicine medicine : medicines) {
            if (medicine.getName().equals(request.getMessage())) {
                desc = medicine.getDescription();
            }
        }
        System.out.println("Message: " + request.getMessage());

        MessageResponseProto responseMessage = MessageResponseProto.newBuilder()
                .setResponse(desc).build();

        responseObserver.onNext(responseMessage);
        responseObserver.onCompleted();


    }
}
