package org.bdyb.hotel.mappers;

import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.enums.IdCardType;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.mapper.UserMapper;
import org.bdyb.hotel.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class UserMapperTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private IdentityCardMapper identityCardMapper;

    @Spy
    @InjectMocks
    private UserMapper userMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapToDtoOk() {
        Mockito.when(identityCardMapper.mapToDto(Mockito.any(IdentityCard.class))).thenReturn(prepareIdentityCardDto());

        User user = prepareUser();
        UserDto userDto = userMapper.mapToDto(user);

        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getEmail(), userDto.getEmail());
        Assert.assertEquals(user.getPassword(), userDto.getPassword());
        Assert.assertEquals(user.getName(), userDto.getName());
        //id
        Assert.assertEquals(user.getIdentityCard().getId(), userDto.getIdentityCard().getId());
        Assert.assertEquals(user.getIdentityCard().getIdCardType(), userDto.getIdentityCard().getIdCardType());
        Assert.assertEquals(user.getIdentityCard().getIdCardNumber(), userDto.getIdentityCard().getIdCardNumber());
        Assert.assertEquals(user.getIdentityCard().getMonthExpiring(), userDto.getIdentityCard().getMonthExpiring());
        Assert.assertEquals(user.getIdentityCard().getYearExpiring(), userDto.getIdentityCard().getYearExpiring());
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

    private User prepareUser() {
        return User.builder()
                .id(1L)
                .email("email")
                .password("password")
                .name("name")
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
        UserDto userDto = prepareUserDto();
        Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(null);

        User user = userMapper.mapToEntity(userDto);

        Assert.assertEquals(userDto.getId(), user.getId());
        Assert.assertEquals(userDto.getEmail(), user.getEmail());
        Assert.assertEquals(userDto.getPassword(), user.getPassword());
        Assert.assertEquals(userDto.getName(), user.getName());
        //id
        Assert.assertNull(user.getIdentityCard());
    }

    private UserDto prepareUserDto() {
        return UserDto.builder()
                .id(1L)
                .email("email")
                .password("password")
                .name("name")
                .identityCard(prepareIdentityCardDto())
                .build();
    }

    @Test
    public void testMapToEntityExistingOk() {
        UserDto userDto = prepareUserDto();
        Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(prepareUser());

        User user = userMapper.mapToEntity(userDto);

        Assert.assertEquals(userDto.getId(), user.getId());
        Assert.assertEquals(userDto.getEmail(), user.getEmail());
        Assert.assertEquals(userDto.getPassword(), user.getPassword());
        Assert.assertEquals(userDto.getName(), user.getName());
        //id
        Assert.assertEquals(userDto.getIdentityCard().getId(), user.getIdentityCard().getId());
        Assert.assertEquals(userDto.getIdentityCard().getIdCardType(), user.getIdentityCard().getIdCardType());
        Assert.assertEquals(userDto.getIdentityCard().getIdCardNumber(), user.getIdentityCard().getIdCardNumber());
        Assert.assertEquals(userDto.getIdentityCard().getMonthExpiring(), user.getIdentityCard().getMonthExpiring());
        Assert.assertEquals(userDto.getIdentityCard().getYearExpiring(), user.getIdentityCard().getYearExpiring());
    }
}
