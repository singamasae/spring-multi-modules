package com.guestpro.iot.emoney.controller;


import com.guestpro.iot.emoney.model.Account;
import com.guestpro.iot.emoney.model.Transaction;
import com.guestpro.iot.emoney.pojo.GenericResponse;
import com.guestpro.iot.emoney.pojo.ReqPayment;
import com.guestpro.iot.emoney.pojo.ReqTopup;
import com.guestpro.iot.emoney.pojo.ReqWithdrawal;
import com.guestpro.iot.emoney.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // behavior
    // check balance
    @GetMapping("/inquiryBalance/{id}")
    public GenericResponse inquiryBalance(@PathVariable String id) throws Exception {
        GenericResponse response;
        Account account = transactionService.inquiryBalance(id);
        if (account == null) {
            response = new GenericResponse();
            response.setMessage("invalid id");
            response.setSuccess(false);
            return response;
        }

        response = new GenericResponse();
        response.setData("account", account);
        response.setMessage("inquiry balance success");
        response.setSuccess(true);
        return response;
    }

    //topup balance
    @PostMapping("/topup")
    public GenericResponse topup(@Valid @RequestBody ReqTopup reqTopup) {
        GenericResponse response;
        Transaction transaction = transactionService.topup(reqTopup);
        if (transaction == null) {
            response = new GenericResponse();
            response.setMessage("transaction topup failed");
            response.setSuccess(false);
            return response;
        }

        response = new GenericResponse();
        response.setData("transaction", transaction);
        response.setMessage("inquiry balance success");
        response.setSuccess(true);
        return response;
    }

    //payment
    @PostMapping("/payment")
    public GenericResponse payment(@Valid @RequestBody ReqPayment reqPayment) {
        GenericResponse response;
        Transaction transaction = transactionService.payment(reqPayment);
        if (transaction == null) {
            response = new GenericResponse();
            response.setMessage("transaction payment failed");
            response.setSuccess(false);
            return response;
        }

        response = new GenericResponse();
        response.setData("transaction", transaction);
        response.setMessage("inquiry payment success");
        response.setSuccess(true);
        return response;
    }

    //topup balance
    @PostMapping("/withdrawal")
    public GenericResponse withdrawal(@Valid @RequestBody ReqWithdrawal reqWithdrawal) {
        GenericResponse response;
        Transaction transaction = transactionService.withdrawal(reqWithdrawal);
        if (transaction == null) {
            response = new GenericResponse();
            response.setMessage("transaction withdrawal failed");
            response.setSuccess(false);
            return response;
        }

        response = new GenericResponse();
        response.setData("transaction", transaction);
        response.setMessage("inquiry withdrawal success");
        response.setSuccess(true);
        return response;
    }
}
