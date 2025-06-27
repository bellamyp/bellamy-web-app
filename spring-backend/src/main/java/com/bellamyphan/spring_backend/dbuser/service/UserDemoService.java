package com.bellamyphan.spring_backend.dbuser.service;

import com.bellamyphan.spring_backend.dbmoney.data.SampleBankData;
import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import com.bellamyphan.spring_backend.dbmoney.entity.BankTypeEnum;
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
        // Generate sample checking banks for the demo user
        List<Bank> sampleCheckingBanks = sampleBankData.generateSampleBanks(
                2, user.getId(), BankTypeEnum.CHECKING);
        bankService.saveAllByUserDemoService(sampleCheckingBanks);
        // Generate sample savings banks for the demo user
        List<Bank> sampleSavingsBanks = sampleBankData.generateSampleBanks(
                2, user.getId(), BankTypeEnum.SAVINGS);
        bankService.saveAllByUserDemoService(sampleSavingsBanks);
        // Generate sample credit card banks for the demo user
        List<Bank> sampleCreditCardBanks = sampleBankData.generateSampleBanks(
                4, user.getId(), BankTypeEnum.CREDIT);
        bankService.saveAllByUserDemoService(sampleCreditCardBanks);
    }
}
