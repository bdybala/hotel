package org.bdyb.hotel.services;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.RegisterDto;
import org.bdyb.hotel.dto.pagination.SearchFieldDto;
import org.bdyb.hotel.dto.pagination.UserPaginationDto;
import org.bdyb.hotel.enums.RoleNameEnum;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.UserAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoleNotFoundException;
import org.bdyb.hotel.repository.RoleRepository;
import org.bdyb.hotel.repository.UserRepository;
import org.bdyb.hotel.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserIntegrationTest {

    private static final String EMAIL = "test@test.pl";
    private static final String PASSWORD = "123456";
    private static final String FIRST_NAME = "Stefan";
    private static final String LAST_NAME = "Czerczesow";

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test(expected = UserAlreadyExistsConflictException.class)
    public void registerNegativeEmailAlreadyRegistered() throws UserAlreadyExistsConflictException, RoleNotFoundException {
        // given
        userRepository.save(prepareUser(EMAIL));
        // when
        userService.registerUser(prepareRegisterDto(false));

        // then
    }

    @Test(expected = RoleNotFoundException.class)
    public void registerNegativeRoleNotFound() throws UserAlreadyExistsConflictException, RoleNotFoundException {
        // given

        // when
        userService.registerUser(prepareRegisterDto(false));

        // then
    }

    @Test
    public void registerPositive() throws UserAlreadyExistsConflictException, RoleNotFoundException {
        // given

        // when
        User user = userService.registerUser(prepareRegisterDto(true));

        // then
        Assert.assertNotNull(user);
    }

    @Test(expected = SearchFieldNotExistingException.class)
    public void searchUsersNegativeNotExistingSearchField() throws SearchFieldNotExistingException {
        // given

        // when
        List<User> users = userService.searchUsers(prepareSearchPagination(getSearchFieldsNotExisting(), 1));

        // then
        Assert.assertNotNull(users);
    }

    @Test
    public void searchUsersPositive() throws SearchFieldNotExistingException {
        // given
        int usersQuantity = 11;
        userRepository.save(prepareUsers(EMAIL, usersQuantity));

        // when
        List<User> users = userService.searchUsers(prepareSearchPagination(getSearchFieldsOk(), 2));

        // then
        Assert.assertNotNull(users);
        Assert.assertEquals(usersQuantity - 10, users.size());
    }

    private List<User> prepareUsers(String email, int size) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            users.add(prepareUser(i + email));
        }
        return users;
    }

    private User prepareUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setRole(roleRepository.findByName(RoleNameEnum.ROLE_ADMINISTRATOR).get());
        return user;
    }

    private RegisterDto prepareRegisterDto(boolean withRole) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail(EMAIL);
        registerDto.setFirstName(FIRST_NAME);
        registerDto.setLastName(LAST_NAME);
        registerDto.setPassword(PASSWORD);
        registerDto.setRoleNameEnum(withRole ? RoleNameEnum.ROLE_ADMINISTRATOR : null);
        return registerDto;
    }

    private UserPaginationDto prepareSearchPagination(List<SearchFieldDto> searchFields, int page) {
        UserPaginationDto paginationDto = new UserPaginationDto();
        paginationDto.setCurrentPage(page);
        paginationDto.setSearchFields(searchFields);
        return paginationDto;
    }

    private List<SearchFieldDto> getSearchFieldsNotExisting() {
        List<SearchFieldDto> searchFields = new ArrayList<>();
        searchFields.add(new SearchFieldDto("username", "login"));
        return searchFields;
    }

    private List<SearchFieldDto> getSearchFieldsOk() {
        List<SearchFieldDto> searchFields = new ArrayList<>();
        searchFields.add(new SearchFieldDto("email", EMAIL.substring(1, EMAIL.length() - 1)));
        return searchFields;
    }
}
