package com.isaproject.isaproject.service;

import com.isaproject.isaproject.psw.controller.MedicineOrdersController;
import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.service.IMedicineService;
import com.isaproject.isaproject.psw.service.IUrgentOrderService;
import com.isaproject.isaproject.psw.service.UrgentOrderService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.grpc.MessageProto;
import rs.ac.uns.ftn.grpc.MessageResponseProto;
import rs.ac.uns.ftn.grpc.SpringGrpcServiceGrpc;
import java.util.List;


@GrpcService
public class CommunicationService extends SpringGrpcServiceGrpc.SpringGrpcServiceImplBase {
    @Autowired
    private IMedicineService service;
    private IUrgentOrderService serviceUrgentOrders;

    @Override
    public void communicate(MessageProto request, StreamObserver<MessageResponseProto> responseObserver) {

        String recievedMessage = request.getMessage();
        String []parts = recievedMessage.split(":");
        if(parts.length>1) {
            System.out.println("Message  order     : " + request.getMessage());
            try {
                serviceUrgentOrders.SaveUrgentOrder(recievedMessage);
            }
            catch(Exception e) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(e);

            }
            MessageResponseProto responseMessage = MessageResponseProto.newBuilder()
                    .setResponse("ORDER IS ACCEPTED").build();
            responseObserver.onNext(responseMessage);
            responseObserver.onCompleted();
        }
        else {
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
}
