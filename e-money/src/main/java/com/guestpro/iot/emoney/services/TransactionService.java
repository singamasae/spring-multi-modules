package com.guestpro.iot.emoney.services;


import com.guestpro.iot.emoney.model.Account;
import com.guestpro.iot.emoney.model.Transaction;
import com.guestpro.iot.emoney.pojo.ReqPayment;
import com.guestpro.iot.emoney.pojo.ReqTopup;
import com.guestpro.iot.emoney.pojo.ReqWithdrawal;

public interface TransactionService {
    Account inquiryBalance(String id);
    Transaction topup(ReqTopup topup);
    Transaction payment(ReqPayment payment);
    Transaction withdrawal(ReqWithdrawal withdrawal);
}
