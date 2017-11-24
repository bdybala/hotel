package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.enums.IdCardType;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.service.CustomerService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitCustomer {

    @Autowired
    private CustomerService customerService;

    public void init() {
        String name = "Jan";
        String surname = "Kowalski";
        String idCardNumber = "AWK70707";
        DateTime birthday = new DateTime().minusYears(21);
        for (int i = 0; i < 2; i++) {
            try {
                customerService.addOne(CustomerDto.builder()
                        .name(name)
                        .surname(surname )
                        .birthday(birthday.plusYears(i).toDate())
                        .identityCard(IdentityCardDto.builder()
                                .idCardType(IdCardType.ID_CARD)
                                .idCardNumber(idCardNumber + i)
                                .monthExpiring(6)
                                .yearExpiring(2018)
                                .build())
                        .build());
                log.info("INIT Customer " + name + " " + surname + " created");
            } catch (ConflictException e) {
                log.info("INIT Customer " + name + " " + surname + " exists");
            }
        }
    }
}
