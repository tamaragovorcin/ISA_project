package com.isaproject.isaproject.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import rs.ac.uns.ftn.grpc.MessageProto;
import rs.ac.uns.ftn.grpc.MessageResponseProto;
import rs.ac.uns.ftn.grpc.SpringGrpcServiceGrpc;

import java.util.UUID;

@GrpcService
public class CommunicationService extends SpringGrpcServiceGrpc.SpringGrpcServiceImplBase {


    @Override
    public void communicate(MessageProto request, StreamObserver<MessageResponseProto> responseObserver) {
        String desc = "";
       /* List<Medicine> medicines = service.getAll();

        for (Medicine medicine : medicines) {
            if(medicine.getName().equals(ime)) {
                 desc=medicine.get(description) ;
            }*/
            
        System.out.println("Message: " + request.getMessage());

        MessageResponseProto responseMessage = MessageResponseProto.newBuilder()
                .setResponse("Ovo je moj odgovor").build();

        responseObserver.onNext(responseMessage);
        responseObserver.onCompleted();


    }
}
