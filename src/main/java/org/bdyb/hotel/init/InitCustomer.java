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
        String name = "FirstName";
        String surname = "LastName";
        try {
            customerService.addOne(CustomerDto.builder()
                    .name(name)
                    .surname(surname)
                    .birthday(new DateTime().minusYears(21).toDate())
                    .identityCard(IdentityCardDto.builder()
                            .idCardType(IdCardType.ID_CARD)
                            .idCardNumber("AWK707070")
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
