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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @GetMapping(test + "/manager/orders")
    public List<LibraryOrderResponse> getLibraryOrders(Authentication auth) {
        return managerRepository.getLibraryOrders();
    }

    @PostMapping(test + "/manager/user-promotion")
    public ResponseEntity<GenericResponse> promoteUser(@RequestBody PromoteUserRequest request,
                                                       Authentication auth) {
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
        boolean isAdded = managerRepository.addBook(request);

        if (isAdded)
            return ResponseEntity.ok(new GenericResponse(true, "Book added successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to add book!", null));
    }

    @GetMapping(test + "/manager/book")
    public ResponseEntity<BookInfoResponse> findBook(@RequestParam String bookISBN) {
        BookInfoResponse book = managerRepository.findBookByISBN(bookISBN);

        if(book != null)
            return ResponseEntity.ok(book);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

}
