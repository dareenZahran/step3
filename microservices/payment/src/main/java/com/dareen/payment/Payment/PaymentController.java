package com.dareen.payment.Payment;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.project.demo.Payment.PaymentModelAssembler;
//import com.project.demo.Payment.PaymentRepository;

// import com.project.demo.SupplierSupplierNotFoundException;
@CrossOrigin
@RestController
public class PaymentController {

    private final SpuServiceClient spuServiceClient;
    private final PaymentRepository repository;
    private final PaymentModelAssembler assembler;

    PaymentController(PaymentRepository repository, PaymentModelAssembler assembler ,spuServiceClient spuServiceClient) {
        this.repository = repository;
        this.assembler = assembler;
         this.spuServiceClient = spuServiceClient;
    }
    // @CrossOrigin
    @GetMapping("/Payment")
    CollectionModel<EntityModel<Payment>> all() {
        List<EntityModel<Payment>> Supplier = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(Supplier, linkTo(methodOn(PaymentController.class).all()).withSelfRel());
    }

    // @CrossOrigin
    @PostMapping("/Payment")
    public ResponseEntity<Object> newPayment(@Valid @RequestBody Payment newPayment, BindingResult result) {
        if (result.hasErrors()) {
            // Return an error response with a custom message indicating the validation errors
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        } else if (newPayment.getAmount() <= 0) {
            // Return an error response if the input is empty
            String errorMessage = "Enter Amount ";
            return ResponseEntity.badRequest().body(errorMessage);
        }  else if (newPayment.getPaymentDate()== null) {
            // Return an error response if the input is empty
            String errorMessage = "Payment Date cannot be empty ";
            return ResponseEntity.badRequest().body(errorMessage);
        } else if (newPayment.getPaymentMethod()== null||newPayment.getPaymentMethod().isEmpty()) {
            // Return an error response if the input is empty
            String errorMessage = "Payment Method() cannot be empty ";
            return ResponseEntity.badRequest().body(errorMessage);
        } 
        else {
            // Save the category to the repository
            Payment savedPayment = repository.save(newPayment);
            // Return a response with the saved category and a link to view all categories
            EntityModel<Payment> PaymentModel = assembler.toModel(savedPayment);
            return ResponseEntity.created(PaymentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(PaymentModel);
        }
    }
    @GetMapping("/Payment/{id}")
    EntityModel<Payment> one(@PathVariable Long id) {
        Payment Supplier = repository.findById(id) //
                .orElseThrow(() -> new PaymentNotFoundException(id));
        return assembler.toModel(Supplier);
    }

    @PutMapping("/Payment/{id}")
    Payment replacePayment(@Valid@RequestBody Payment newPayment, @PathVariable Long id) {
        return repository.findById(id)
                .map(Payment -> {
                    Payment.setPaymentDate(newPayment.getPaymentDate());

                   Payment.setPaymentMethod(newPayment.getPaymentMethod());
                    Payment.setAmount(newPayment.getAmount());

                    
                    return repository.save(Payment);
                })
                .orElseGet(() -> {
                    newPayment.setPaymentId(id);
                    return repository.save(newPayment);
                });
    }

    @DeleteMapping("/Payment/{id}")
    void deletePayment(@PathVariable Long id) {
        repository.deleteById(id);
    }
}