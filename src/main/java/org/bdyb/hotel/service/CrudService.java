package org.bdyb.hotel.service;

import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface CrudService <Entity, Dto> {

    Dto findOne(Long id) throws EntityNotFoundException;

    List<Dto> findAll();

    Dto addOne(Dto dtoToAdd) throws ConflictException;

    Dto editOne(Dto dtoToEdit) throws EntityNotFoundException, ConflictException;

    Dto delete(Long id) throws EntityNotFoundException;
}
