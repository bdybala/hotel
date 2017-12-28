package org.bdyb.hotel.controller;

import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public abstract class AbstractCrudController<Entity, Dto> {

    public AbstractCrudController(CrudService<Entity, Dto> service) {
        this.service = service;
    }

    protected CrudService<Entity, Dto> service;

    @RequestMapping(method = RequestMethod.GET)
    List<Dto> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Dto findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Dto addOne(@RequestBody Dto dtoToAdd) throws ConflictException {
        return service.addOne(dtoToAdd);
    }

    @RequestMapping(method = RequestMethod.PUT)
    Dto editOne(@RequestBody Dto dtoToEdit) throws ConflictException, EntityNotFoundException {
        return service.editOne(dtoToEdit);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void remove(@PathVariable("id") Long id) throws EntityNotFoundException {
        service.delete(id);
    }
}
