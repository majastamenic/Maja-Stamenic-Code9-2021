package com.code9.paymicroservice.pay.service;

import com.code9.paymicroservice.communication.TennisCourtCommunication;
import com.code9.paymicroservice.exception.BadRequestException;
import com.code9.paymicroservice.pay.domain.CreditCard;
import com.code9.paymicroservice.pay.service.interfaces.IPayService;
import org.springframework.stereotype.Service;

@Service
public class PayService implements IPayService {

    private TennisCourtCommunication tennisCourtCommunication;

    public PayService(TennisCourtCommunication tennisCourtCommunication) {
        this.tennisCourtCommunication = tennisCourtCommunication;
    }

    @Override
    public String payReservation(Long reservationId, CreditCard creditCard) {

        if (tennisCourtCommunication.getById(reservationId).getBody() == null)
            throw new BadRequestException("There is no reservation with id: " + reservationId);

        tennisCourtCommunication.paid(reservationId);

        return "Reservation with id: " + reservationId + " is paid";
    }
}
