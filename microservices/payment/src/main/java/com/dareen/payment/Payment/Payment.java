package com.project.demo.Payment;



import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.demo.Order.Order;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "payment_id")
  
	private long paymentId;
    // @JsonBackReference(value="payment")
    // @ManyToOne
    // @JoinColumn(name = "order_id")
    // private Order order;
    // @OneToOne
    // @JoinColumn(name = "order_id")
    // private Order order;
    
    // public Order getOrder() {
    //     return order;
    // }

    // public void setOrder(Order order) {
    //     this.order = order;
    // }
	//@Column(name = "payment_date")
    @NotNull(message = "Payment Date is required")
	private LocalDate paymentDate;
	
	//@Column(name = "payment_methoe")
    @NotBlank(message = "payment Method is required")
	private String paymentMethod;
	
	//@Column(name = "amount")
    @NotNull(message = "amount is required")
	private double amount;

    // Constructor
    public Payment() {
    }

    public Payment(  LocalDate requiredDate, String paymentMethod,double d) {

        this.paymentDate = requiredDate;
        this.paymentMethod = paymentMethod;
        this.amount = d;
    }

    // Getter and Setter methods
    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

  

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
	
	// toString method
    @Override
    public String toString() {
        return "Payment{" +
                "PaymentId=" + paymentId +
             
                ",  paymentDate='" +  paymentDate + '\'' +
                ", paymentMethod=" +paymentMethod +
                ", amount='" + amount + '\'' +
                '}';
    }
    
}