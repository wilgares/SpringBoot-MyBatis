package com.controller;

import com.entity.Userboot;
import com.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/users")
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Userboot> getPerson(@PathVariable("id") int userId) {
            Userboot user = userMapper.findById(userId);
            return new ResponseEntity<Userboot>(user, HttpStatus.OK);
    }
    
    @GET
    //@Path("/otro/{id}")
    @Produces("application/json")
    @RequestMapping("/otro/{id}")
    public Response getPersonb(@PathVariable("id") int userId) {

        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();

        try {
        	Userboot user = userMapper.findById(userId);

            if (user == null) {
                response.put("User", Collections.emptyMap());
            } else {
                response.put("user", user);
            }
            return Response.status(Response.Status.OK).entity(user).build();
        } catch (Exception ex) {

            response.put("user", "Not Found");
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }
    
    @GET
    @Produces("application/json")
    @RequestMapping("/test")
    public @ResponseBody Response getTest() {
    	return Response.status(Response.Status.OK).entity("ok").build();
    }
    
    @GET
    @Produces("application/json")
    @RequestMapping("/todos")
    public @ResponseBody Response getUsers() {
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        try {
            List<Userboot> listUsers = userMapper.getUsers();
            if (listUsers == null) {
                response.put("users", Collections.emptyMap());
            } else {
                response.put("total", listUsers.size());
                response.put("users", listUsers);
            }
            return Response.status(Response.Status.OK).entity(listUsers).build();
        } catch (Exception ex) {
            response.put("user", "Not Found");
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping("/")
    public Response create(@RequestBody Userboot user) {
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();
        logger.info("Starting to create a user");

        try {
            Set<ConstraintViolation<Userboot>> validateErrors = validator.validate(user);
            if (validateErrors.isEmpty()) {
                if (userMapper.findByEmail(user.getEmail()) != null) {
                    serviceResponse.put("duplicate_Email", "user already exist");
                } else {
                    Integer createPerson = userMapper.insertUser(user);

                    if (createPerson != 1) {
                        serviceResponse.put("created", "unable to create user");
                    } else {
                        logger.info("Successfully created User.");
                        serviceResponse.put("created_msg", "Successfully created User");
                    }
                }
                return Response.status(Response.Status.CREATED).entity(serviceResponse).build();
            } else {
                logger.info("Failed to create a user due to field validation errors.");
                logger.debug("Unable to create a user due to validation errors using {}", user);
                //JSONObject jsonObj = new JSONObject(validateErrors.toString());
                serviceResponse.put("error", validateErrors.toString());
                return Response.status(400).entity(serviceResponse).build();


            }
        } catch (Exception e) {
            logger.debug("<< create()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
        }
    }




    @PUT
    //@Path("/{id}")
    @RequestMapping("/update/{id}")
    @Produces("application/json")
    public @ResponseBody Response updateUser(@PathVariable("id") Integer id, Userboot user) {
        //String personJson = gson.toJson(user);
        //logger.debug(">> create({})", personJson);
        //LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();
        logger.info("Starting to create a person");

        try {
            Set<ConstraintViolation<Userboot>> validateErrors = validator.validate(user);
            if (validateErrors.isEmpty()) {
                if (userMapper.findById(id) == null) {
                    serviceResponse.put("msg", "User Not Found");
                } else {
                    if (userMapper.findByEmailNotUser(user.getEmail(), user.getId()) != null) {
                        serviceResponse.put("duplicate_Email", "user already exist");
                    } else {
                        int updateUser = userMapper.updateUser(user);
                        if (updateUser == 0) {
                            serviceResponse.put("created", "unable to update User");
                        } else {
                            logger.info("Successfully update user.");
                            serviceResponse.put("update", user);
                        }
                    }
                }
                return Response.status(Response.Status.OK).entity(user).build();
            } else {
                logger.info("Failed to update a user due to field validation errors.");
                // logger.debug("Unable to update a user due to validation errors using {}", personJson);
                serviceResponse.put("error", validateErrors.toString());

                return Response.status(400).entity(serviceResponse).build();
            }
        } catch (Exception e) {
            logger.debug("<< create()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
        }
    }



    @DELETE
    //@Path("/{id}")
    @RequestMapping("/delete/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(@PathVariable("id") int userId) {

        //LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();
        logger.info("Starting to delete a user");

        try {
            int deletePerson = userMapper.deleteById(userId);
            if (deletePerson == 0) {
                serviceResponse.put("delete", "unable delete user");
            } else {
                logger.info("Successfully delete user.");
                serviceResponse.put("delete", "Successfully delete user.");
            }
            return Response.status(Response.Status.OK).entity(serviceResponse).build();

        } catch (Exception e) {

            logger.debug("<< create()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
        }
    }

}