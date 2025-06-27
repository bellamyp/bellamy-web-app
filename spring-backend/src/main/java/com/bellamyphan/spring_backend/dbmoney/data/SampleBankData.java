package com.bellamyphan.spring_backend.dbmoney.data;

import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import com.bellamyphan.spring_backend.dbmoney.entity.BankType;
import com.bellamyphan.spring_backend.dbmoney.entity.BankTypeEnum;
import com.bellamyphan.spring_backend.dbmoney.service.BankTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SampleBankData {

    private final List<String> bankNames = List.of(
            "Chase", "Bank of America", "Wells Fargo", "Capital One", "Ally", "Citi",
            "Discover", "PNC", "TD Bank", "US Bank", "BB&T", "Regions", "SunTrust",
            "HSBC", "Synchrony", "American Express", "Fifth Third", "KeyBank", "Charles Schwab",
            "Navy Federal", "Axos", "SoFi", "Barclays", "Comenity", "Huntington", "Santander"
    );

    private final List<String> suffixes = List.of("360", "Premier", "Advantage", "Rewards", "Gold",
            "Plus", "Essential");

    private final Random random = new Random();

    private final BankTypeService bankTypeService;

    public List<Bank> generateSampleBanks(int count, Long userId, BankTypeEnum bankTypeEnum) {
        List<Bank> generatedBanks = new ArrayList<>();
        BankType bankType = bankTypeService.findByNameIgnoreCase(bankTypeEnum.getType());
        for (int i = 0; i < count; i++) {
            // Generate random bank name
            String name = generateBankName() + " " + bankTypeEnum.getDisplayName();
            // Generate random open date
            LocalDate openDate = generateRandomDateBetween(
                    LocalDate.of(2010, 1, 1),
                    LocalDate.of(2025, 6, 1));
            // Create a new Bank object and add to the list
            Bank bank = new Bank(name, openDate, null, bankType, userId); // ID auto generated
            generatedBanks.add(bank);
        }
        return generatedBanks;
    }

    private String generateBankName() {
        String bank = bankNames.get(random.nextInt(bankNames.size()));
        String suffix = suffixes.get(random.nextInt(suffixes.size()));
        return bank + " " + suffix;
    }

    private BankTypeEnum getRandomBankTypeEnum() {
        BankTypeEnum[] values = BankTypeEnum.values();
        return values[random.nextInt(values.length)];
    }

    private LocalDate generateRandomDateBetween(LocalDate start, LocalDate end) {
        int days = (int) (end.toEpochDay() - start.toEpochDay());
        long randomDay = start.toEpochDay() + random.nextInt(days + 1);
        return LocalDate.ofEpochDay(randomDay);
    }
}
