package com.dareen.spu.User;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
// import org.springframework.hateoas.IanaLinkRelations;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import com.project.demo.UserUserNotFoundException;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private final UserRepository repository;
    private final UserModelAssembler assembler;

    UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
   
// @CrossOrigin
    @GetMapping("/User/{id}")
    EntityModel<User> findById(@PathVariable Long id) {
        User User = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(User);
    }

    @PutMapping("/User/{id}")
    User updateUser(@Valid@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(User -> {
                    User.setUsername(newUser.getUsername());
                    User.setEmail(newUser.getEmail());
                    User.setPassword(newUser.getPassword());
                    return repository.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/User/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
