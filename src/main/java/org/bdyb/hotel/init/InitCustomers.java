package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.service.CustomerService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitCustomers {

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
                        .firstName(name)
                        .lastName(surname)
                        .birthday(birthday.plusYears(i).toDate())
                        .identityCard(IdentityCardDto.builder()
                                .number(idCardNumber + i)
                                .expiringDate(new DateTime().plusYears(1).toDate())
                                .build())
                        .build());
                log.info("INIT Customer " + name + " " + surname + " created");
            } catch (ConflictException e) {
                log.info("INIT Customer " + name + " " + surname + " exists");
            }
        }
    }
}
