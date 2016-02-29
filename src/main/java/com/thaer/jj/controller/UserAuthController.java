package com.thaer.jj.controller;

import com.thaer.jj.core.JWTAuth;
import com.thaer.jj.exceptions.UnAuthorizedException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Thaer AlDwaik <thaer_aldwaik@hotmail.com>
 * @since February 11, 2016.
 */

@Path("userAuth")
public class UserAuthController extends MainController {

    @POST @Path("/login")
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {

        try {

            JWTAuth jwtAuth = new JWTAuth();

            String jwtAuthorization = jwtAuth.generateUserAuth(email, password, request.getRemoteAddr());

            return Response.accepted().header("Authorization", jwtAuthorization).build();

        }catch (UnAuthorizedException e) {
            return Response.status(401).build();

        }catch (IOException | SQLException | ClassNotFoundException e) {
            return Response.status(500).build();
        }

    }

    @GET @Path("/isLogin")
    public Response isLogin() {

        if(isAuthUser) {
            return Response.status(401).build();
        }else {
            return Response.ok().build();
        }

    }

}
