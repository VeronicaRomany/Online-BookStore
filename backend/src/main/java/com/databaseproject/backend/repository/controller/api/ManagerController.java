package com.databaseproject.backend.repository.controller.api;

import com.databaseproject.backend.repository.controller.response.ManagerResponse;
import com.databaseproject.backend.repository.interfaces.IManagerRepository;
import com.databaseproject.backend.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/manager")
public class ManagerController {
    @Autowired
    IManagerRepository managerService;


    @PostMapping("/confirmOrder")
    public ResponseEntity<ManagerResponse> confirmLibraryOrder(@RequestBody ConfirmLibraryOrderRequest request, Authentication auth) {
        boolean success = managerService.confirmLibraryOrder(request);
        if(success)
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ManagerResponse(true, "Order Confirmed Successfully!"));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ManagerResponse(false, "Order is Rejected!!"));
    }

    @PostMapping("/createOrder")
    public ResponseEntity<ManagerResponse> createLibraryOrder(@RequestBody CreateLibraryOrderRequest request, Authentication auth) {
        boolean success = managerService.createLibraryOrder(request);
        if(success)
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ManagerResponse(true, "Order Created Successfully!"));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ManagerResponse(false, "Order is NOT Created!!"));
    }

    @PostMapping("/promoteUser")
    public ResponseEntity<ManagerResponse> promoteUser(@RequestBody PromoteUserRequest request, Authentication auth) {
        boolean success = managerService.promoteUser(request, auth.getName());
        if(success)
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ManagerResponse(true, "User Promoted Successfully!"));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ManagerResponse(false, "User CANNOT be Promoted!!"));
    }

    @PostMapping("/modifyBook")
    public ResponseEntity<ManagerResponse> modifyBook(@RequestBody ModifyBookRequest request, Authentication auth) {
        boolean success = managerService.modifyBook(request);
        if(success)
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ManagerResponse(true, "Book Modified Successfully!"));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ManagerResponse(false, "Book CANNOT be Modified!!"));
    }

    @PostMapping("/addPublisher")
    public ResponseEntity<ManagerResponse> addPublisher(@RequestBody AddPublisherRequest request, Authentication auth) {
        boolean success = managerService.addPublisher(request);
        if(success)
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body(new ManagerResponse(true, "Publisher is Added Successfully!"));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ManagerResponse(false, "Publisher CANNOT be Added!!"));
    }

    @PostMapping("/addBook")
    public ResponseEntity<ManagerResponse> addBook(@RequestBody AddBookRequest request, Authentication auth) {
        boolean success = managerService.addBook(request);
        if(success)
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ManagerResponse(true, "Book added Successfully!"));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ManagerResponse(false, "Could not add book!!"));
    }

}
