package com.guestpro.iot.emoney.services.impl;

import com.guestpro.iot.emoney.model.Account;
import com.guestpro.iot.emoney.model.Transaction;
import com.guestpro.iot.emoney.model.TransactionDetail;
import com.guestpro.iot.emoney.model.User;
import com.guestpro.iot.emoney.pojo.ReqPayment;
import com.guestpro.iot.emoney.pojo.ReqTopup;
import com.guestpro.iot.emoney.pojo.ReqWithdrawal;
import com.guestpro.iot.emoney.repository.AccountRepository;
import com.guestpro.iot.emoney.repository.TransactionRepository;
import com.guestpro.iot.emoney.repository.UserRepository;
import com.guestpro.iot.emoney.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service(value = "transactionService")
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Account inquiryBalance(String id) {
        Account account = accountRepository.findById(id).orElse(null);
        return account;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction topup(ReqTopup topup) {
        User user = userRepository.findById(topup.getUserId()).orElse(null);
        if (user == null) return null;

        Account account = accountRepository.findById(topup.getUserId()).orElse(null);
        if (account == null) return null;

        account.setBalance(account.getBalance().add(topup.getAmount()));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTotalAmount(topup.getAmount());
        transaction.setTransaction_mode("CREDIT");
        transaction.setTransaction_type("TOPUP");
        transaction.setTransactionDate(OffsetDateTime.now());
        transaction.setUser(user);

        TransactionDetail detail = new TransactionDetail();
        detail.setAmount(topup.getAmount());
        detail.setDescription(topup.getDescription());
        detail.setTransaction(transaction);
        detail.setTransactionRef(topup.getTransactionRef());
        List<TransactionDetail> details = new ArrayList<>();
        details.add(detail);

        transaction.setTransactionDetails(details);
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction payment(ReqPayment payment) {
        User user = userRepository.findById(payment.getUserId()).orElse(null);
        if (user == null) return null;

        Account account = accountRepository.findById(payment.getUserId()).orElse(null);
        if (account == null) return null;

        BigDecimal balance = account.getBalance().subtract(payment.getAmount());
        if (balance.compareTo(BigDecimal.ZERO) == -1) return null;

        account.setBalance(balance);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTotalAmount(payment.getAmount());
        transaction.setTransaction_mode("DEBIT");
        transaction.setTransaction_type("PAYMENT");
        transaction.setTransactionDate(OffsetDateTime.now());
        transaction.setUser(user);

        TransactionDetail detail = new TransactionDetail();
        detail.setAmount(payment.getAmount());
        detail.setDescription(payment.getDescription());
        detail.setTransaction(transaction);
        detail.setTransactionRef(payment.getTransactionRef());
        List<TransactionDetail> details = new ArrayList<>();
        details.add(detail);

        transaction.setTransactionDetails(details);
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction withdrawal(ReqWithdrawal withdrawal) {
        User user = userRepository.findById(withdrawal.getUserId()).orElse(null);
        if (user == null) return null;

        Account account = accountRepository.findById(withdrawal.getUserId()).orElse(null);
        if (account == null) return null;

        BigDecimal balance = account.getBalance().subtract(withdrawal.getAmount());
        if (balance.compareTo(BigDecimal.ZERO) == -1) return null;

        account.setBalance(balance);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTotalAmount(withdrawal.getAmount());
        transaction.setTransaction_mode("DEBIT");
        transaction.setTransaction_type("WITHDRAWAL");
        transaction.setTransactionDate(OffsetDateTime.now());
        transaction.setUser(user);

        TransactionDetail detail = new TransactionDetail();
        detail.setAmount(withdrawal.getAmount());
        detail.setDescription(withdrawal.getDescription());
        detail.setTransaction(transaction);
        detail.setTransactionRef(withdrawal.getTransactionRef());
        List<TransactionDetail> details = new ArrayList<>();
        details.add(detail);

        transaction.setTransactionDetails(details);
        transaction = transactionRepository.save(transaction);
        return transaction;
    }
}
