package org.bdyb.hotel.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.dto.RoleDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.RoleMapper;
import org.bdyb.hotel.repository.RoleRepository;
import org.bdyb.hotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto findOne(Long id) throws EntityNotFoundException {
        return roleMapper.mapToDto(Optional.ofNullable(roleRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Role with that id not found!")));
    }

    @Override
    public List<RoleDto> findAll() {
        return roleMapper.mapToDto(roleRepository.findAll());
    }

    @Override
    public RoleDto addOne(RoleDto dtoToAdd) throws ConflictException {
        if (roleRepository.existsByName(dtoToAdd.getName()))
            throw new ConflictException("Role wth that name already exists in db!");
        return roleMapper.mapToDto(roleRepository.save(roleMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public RoleDto editOne(RoleDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Role role = Optional.ofNullable(roleRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Role to edit not found! : " + dtoToEdit));
        return roleMapper.mapToDto(roleRepository.save(updateRole(role, dtoToEdit)));
    }

    private Role updateRole(Role role, RoleDto dtoToEdit) {
        if (dtoToEdit.getName() != null) {
            role.setName(dtoToEdit.getName());
        }
        if (dtoToEdit.getDescription() != null) {
            role.setDescription(dtoToEdit.getDescription());
        }
        return role;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!roleRepository.exists(id));
        roleRepository.delete(id);
    }
}
