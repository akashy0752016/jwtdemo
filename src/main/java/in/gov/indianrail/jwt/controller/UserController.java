package in.gov.indianrail.jwt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import in.gov.indianrail.jwt.model.User;
import in.gov.indianrail.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rest/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ResponseEntity<String> getUsers() {
        List<User> users = userService.getAllUser();
        Gson gson = new GsonBuilder().serializeNulls().create();
        String userJson = gson.toJson(users).toString();
        return new ResponseEntity<>(userJson, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<String> getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user != null ) {
            Gson gson = new GsonBuilder().serializeNulls().create();
            String userJson = gson.toJson(user).toString();
            return new ResponseEntity<>(userJson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user){
        User u = null;
        if(userService.getUserById(user.getId()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(userService.createUser(user)) {
            u = userService.getUserById(user.getId());
        }
        Gson gson = new GsonBuilder().serializeNulls().create();
        String userJson = gson.toJson(u).toString();
        return new ResponseEntity<>(userJson, u != null ? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody User user){
        User u = null;
        user.setId(id);
        if(userService.updateUser(user)) {
            u = userService.getUserById(user.getId());
        }
        Gson gson = new GsonBuilder().serializeNulls().create();
        String userJson = gson.toJson(u).toString();
        return new ResponseEntity<>(userJson, u != null ? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        if(userService.getUserById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
