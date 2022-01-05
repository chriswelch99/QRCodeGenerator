package org.weewelchie.qr.boot.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weewelchie.qr.persistence.Scout;
import org.weewelchie.qr.persistence.ScoutService;

import javax.persistence.EntityNotFoundException;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "Scout Endpoint")
@RequestMapping("/scout")
public class ScoutController {

    @Autowired
    ScoutService service;

    @GetMapping("/add")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Returns a newly generated Scout entity.") })
    public Scout add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Date dob, @RequestParam String troop)
    {
            return service.create(new Scout(firstName,lastName,dob,troop));
    }

    @GetMapping("/search")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Find Scout by First Name/Last Name") })
    public List<Scout> find(@RequestParam String firstName, @RequestParam String lastName)
    {
        return service.findByName(firstName,lastName);
    }

    @GetMapping("/all")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Find all Scouts in the DB") })
    public List<Scout> findAll()
    {
        return service.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete Scout from the DB") ,
                            @ApiResponse(responseCode = "400", description = "Error deleting Scout from DB")})
    public ResponseEntity<String> delete(@PathVariable Long id)
    {

        Scout sc = null;
        String text = "";

        try {
            sc = service.findByID(id);
            text = " Scout: " + sc.getFirstName() + " " + sc.getLastName() + " deleted";
            service.delete(sc);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(text, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Returns a newly updated Scout entity.") })
    public Scout updateScout(@RequestBody Scout scout , @PathVariable Long id)
    {
        service.update(scout);

        return service.findByID(id);
    }



}
