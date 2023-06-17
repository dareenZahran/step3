package com.dareen.payment.Payment;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PaymentModelAssembler  implements RepresentationModelAssembler<Payment, EntityModel<Payment>> {

  @Override
  public EntityModel<Payment> toModel(Payment payment) {
    return EntityModel.of(payment,
        linkTo(methodOn(PaymentController.class).one(payment.getPaymentId())).withSelfRel(),
        linkTo(methodOn(PaymentController.class).all()).withRel("payment"));
  }
}