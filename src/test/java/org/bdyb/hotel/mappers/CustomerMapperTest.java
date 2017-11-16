package org.bdyb.hotel.mappers;

import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.enums.IdCardType;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.mapper.CustomerMapper;
import org.bdyb.hotel.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Date;

public class CustomerMapperTest {

    private static final Date BIRTHDAY = new Date();

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private IdentityCardMapper identityCardMapper;

    @Spy
    @InjectMocks
    private CustomerMapper customerMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapToDtoOk() {
        Mockito.when(identityCardMapper.mapToDto(Mockito.any(IdentityCard.class))).thenReturn(prepareIdentityCardDto());

        Customer customer = prepareUser();
        CustomerDto customerDto = customerMapper.mapToDto(customer);

        Assert.assertEquals(customer.getId(), customerDto.getId());
        Assert.assertEquals(customer.getBirthday(), customerDto.getBirthday());
        Assert.assertEquals(customer.getName(), customerDto.getName());
        Assert.assertEquals(customer.getSurname(), customerDto.getSurname());
        //id
        Assert.assertEquals(customer.getIdentityCard().getId(), customerDto.getIdentityCard().getId());
        Assert.assertEquals(customer.getIdentityCard().getIdCardType(), customerDto.getIdentityCard().getIdCardType());
        Assert.assertEquals(customer.getIdentityCard().getIdCardNumber(), customerDto.getIdentityCard().getIdCardNumber());
        Assert.assertEquals(customer.getIdentityCard().getMonthExpiring(), customerDto.getIdentityCard().getMonthExpiring());
        Assert.assertEquals(customer.getIdentityCard().getYearExpiring(), customerDto.getIdentityCard().getYearExpiring());
    }

    private IdentityCardDto prepareIdentityCardDto() {
        return IdentityCardDto.builder()
                .id(1L)
                .idCardType(IdCardType.ID_CARD)
                .idCardNumber("number")
                .monthExpiring(6)
                .yearExpiring(2018)
                .build();
    }

    private Customer prepareUser() {
        return Customer.builder()
                .id(1L)
                .birthday(BIRTHDAY)
                .name("name")
                .surname("surname")
                .identityCard(prepareIdentityCard())
                .build();
    }

    private IdentityCard prepareIdentityCard() {
        return IdentityCard.builder()
                .id(1L)
                .idCardType(IdCardType.ID_CARD)
                .idCardNumber("number")
                .monthExpiring(6)
                .yearExpiring(2018)
                .build();
    }

    @Test
    public void testMapToEntityNewOk() {
        CustomerDto customerDto = prepareUserDto();
        Mockito.when(customerRepository.findOne(Mockito.anyLong())).thenReturn(null);

        Customer customer = customerMapper.mapToEntity(customerDto);

        Assert.assertEquals(customerDto.getId(), customer.getId());
        Assert.assertEquals(customerDto.getBirthday(), customer.getBirthday());
        Assert.assertEquals(customerDto.getName(), customer.getName());
        Assert.assertEquals(customerDto.getSurname(), customer.getSurname());
        //id
        Assert.assertNull(customer.getIdentityCard());
    }

    private CustomerDto prepareUserDto() {
        return CustomerDto.builder()
                .id(1L)
                .birthday(BIRTHDAY)
                .name("name")
                .surname("surname")
                .identityCard(prepareIdentityCardDto())
                .build();
    }

    @Test
    public void testMapToEntityExistingOk() {
        CustomerDto customerDto = prepareUserDto();
        Mockito.when(customerRepository.findOne(Mockito.anyLong())).thenReturn(prepareUser());

        Customer customer = customerMapper.mapToEntity(customerDto);

        Assert.assertEquals(customerDto.getId(), customer.getId());
        Assert.assertEquals(customerDto.getBirthday(), customer.getBirthday());
        Assert.assertEquals(customerDto.getName(), customer.getName());
        Assert.assertEquals(customerDto.getSurname(), customer.getSurname());
        //id
        Assert.assertEquals(customerDto.getIdentityCard().getId(), customer.getIdentityCard().getId());
        Assert.assertEquals(customerDto.getIdentityCard().getIdCardType(), customer.getIdentityCard().getIdCardType());
        Assert.assertEquals(customerDto.getIdentityCard().getIdCardNumber(), customer.getIdentityCard().getIdCardNumber());
        Assert.assertEquals(customerDto.getIdentityCard().getMonthExpiring(), customer.getIdentityCard().getMonthExpiring());
        Assert.assertEquals(customerDto.getIdentityCard().getYearExpiring(), customer.getIdentityCard().getYearExpiring());
    }
}
