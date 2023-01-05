package com.databaseproject.backend.controller;

import com.databaseproject.backend.repository.interfaces.IManagerRepository;
import com.databaseproject.backend.request.*;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.GenericResponse;
import com.databaseproject.backend.response.LibraryOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@Controller
@CrossOrigin()
@RequestMapping("/api/v1")
public class ManagerController {
    private final IManagerRepository managerRepository;

    private final String test = "";

    @Autowired
    public ManagerController(IManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @PostMapping(test + "/manager/order-confirmation")
    public ResponseEntity<GenericResponse> confirmLibraryOrder(@RequestBody ConfirmLibraryOrderRequest request,
                                                               Authentication auth) {
        boolean isConfirmed = managerRepository.confirmLibraryOrder(request);

        if (isConfirmed)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Order confirmed Successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Order is Rejected!", null));
    }

    @PostMapping(test + "/manager/order")
    public ResponseEntity<GenericResponse> createLibraryOrder(@RequestBody CreateLibraryOrderRequest request,
                                                              Authentication auth) {
        boolean isCreated = managerRepository.createLibraryOrder(request);

        if (isCreated)
            return ResponseEntity.ok(new GenericResponse(true, "Order created successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to create order!", null));
    }

    @GetMapping(test + "/manager/orders/test")
    public ResponseEntity< List<LibraryOrderResponse>> getLibraryOrders(Authentication auth) {
        System.out.println("ana hna le");
        List<LibraryOrderResponse> list =managerRepository.getLibraryOrders();
        return ResponseEntity.ok().body( list) ;
    }

    @PostMapping(test + "/manager/user-promotion")
    public ResponseEntity<GenericResponse> promoteUser(@RequestBody PromoteUserRequest request,
                                                       Authentication auth) {
        System.out.println(request);                                              
        boolean isPromoted = managerRepository.promoteUser(request, auth.getName());

        if (isPromoted)
            return ResponseEntity.ok(new GenericResponse(true, "User promoted successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to promote user!", null));
    }

    @PatchMapping(test + "/manager/book")
    public ResponseEntity<GenericResponse> modifyBook(@RequestBody ModifyBookRequest request,
                                                      Authentication auth) {
        boolean isModified = managerRepository.modifyBook(request);

        if (isModified)
            return ResponseEntity.ok(new GenericResponse(true, "Book modified successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to modify book!", null));
    }

    @PostMapping(test + "/manager/publisher")
    public ResponseEntity<GenericResponse> addPublisher(@RequestBody AddPublisherRequest request,
                                                        Authentication auth) {
        boolean isAdded = managerRepository.addPublisher(request);

        if (isAdded)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Publisher added successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to add publisher!", null));
    }

    @PostMapping(test + "/manager/book")
    public ResponseEntity<GenericResponse> addBook(@RequestBody AddBookRequest request, Authentication auth) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getISBN());        
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getCategory()); 
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getPublisher()); 
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getTitle()); 
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getPrice()); 
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getPubYear()); 
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getStock());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getThreshold());  
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> "+request.getAuthors()); 
        

        boolean isAdded = managerRepository.addBook(request);

        if (isAdded)
            return ResponseEntity.ok(new GenericResponse(true, "Book added successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to add book!", null));
    }

    @PostMapping(test + "/manager/book/get")
    public ResponseEntity<BookInfoResponse> findBook(@RequestBody String bookISBN) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>"+bookISBN);
        BookInfoResponse book = managerRepository.findBookByISBN(bookISBN);

        if(book != null)
            return ResponseEntity.ok(book);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

}
