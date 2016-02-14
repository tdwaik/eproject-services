package com.thaer.jj.controller;

import com.thaer.jj.entities.User;
import com.thaer.jj.model.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

/**
 * Created by Thaer AlDwaik on February 10, 2016.
 */

@Path("user")
public class UserController extends AbstractController {

    @GET @Path("/getUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("userId") int id) {
        User user = new User();
        try {
            UserModel userModel = new UserModel();
            user = userModel.getUserById(id);
            return toJson(false, user);
        } catch (Exception e) {
            e.printStackTrace();
            return toJson(true);
        }

    }

    @POST @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser() {
        try {
            UserModel userModel = new UserModel();
            userModel.addUser("tdwaik", "t_dwaik@hotmail.com", "Thaer", "AlDwaik", "+962791305948");
            return toJson(false);
        } catch (Exception e) {
            e.printStackTrace();
            return toJson(true);
        }

    }
}
