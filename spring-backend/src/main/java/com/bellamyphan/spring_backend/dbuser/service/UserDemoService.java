package com.bellamyphan.spring_backend.dbuser.service;

import com.bellamyphan.spring_backend.dbmoney.data.SampleBankData;
import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import com.bellamyphan.spring_backend.dbmoney.service.BankService;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDemoService {

    private final SampleBankData sampleBankData;
    private final BankService bankService;

    @Transactional
    public void generateDemoData(User user) {
        // Generate sample banks for the demo user
        List<Bank> sampleBanks = sampleBankData.generateSampleBanks(3, user.getId());
        bankService.saveAllByUserDemoService(sampleBanks);

    }
}
