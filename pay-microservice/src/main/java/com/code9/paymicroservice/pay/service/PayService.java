package com.code9.paymicroservice.pay.service;

import com.code9.paymicroservice.exception.BadRequestException;
import com.code9.paymicroservice.pay.domain.CreditCard;
import com.code9.paymicroservice.pay.service.interfaces.IPayService;
import com.code9.tenniscourtmicroservice.client.TennisCourtClient;
import org.springframework.stereotype.Service;

@Service
public class PayService implements IPayService {

    private TennisCourtClient tennisCourtClient;

    public PayService(TennisCourtClient tennisCourtClient) {
        this.tennisCourtClient = tennisCourtClient;
    }

    @Override
    public String payCreditCard(Long reservationId, CreditCard creditCard) {

        if (tennisCourtClient.getById(reservationId).getBody() == null)
            throw new BadRequestException("There is no reservation with id: " + reservationId);

        tennisCourtClient.paid(reservationId);

        return "Reservation with id: " + reservationId + " is paid by credit card.";
    }

    @Override
    public String payCash(Long id) {
        if (tennisCourtClient.getById(id).getBody() == null)
            throw new BadRequestException("There is no reservation with id: " + id);

        tennisCourtClient.paid(id);

        return "Reservation with id: " + id + " is paid by cash";
    }
}
