package vn.hoidanit.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.service.error.IdInvalidException;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/users")
  public ResponseEntity<User> createNewUser(@RequestBody User postManUser) {

    // User user = new User();
    // user.setEmail("taothichmi2009@gmail.com");
    // user.setName("Thanh");
    // user.setPassword("123456");

    User thanhUser = this.userService.handleCreateUser(postManUser);

    // return ResponseEntity.ok(thanhUser);
    // return new ResponseEntity<>(thanhUser, HttpStatus.CREATED);
    return ResponseEntity.status(HttpStatus.CREATED).body(thanhUser);
  }

  @ExceptionHandler(value = IdInvalidException.class)
  public ResponseEntity<String> hanldeIdExeption(IdInvalidException idException) {
    return ResponseEntity.badRequest().body(idException.getMessage());
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") long id) throws IdInvalidException {

    if (id >= 1500) {
      throw new IdInvalidException("Id không lớn hơn 1501");
    }

    this.userService.handleDeleteUser(id);
    // return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    // return new ResponseEntity<String>("Delete successfully", HttpStatus.OK);
    return ResponseEntity.ok("Delete successfully");
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    this.userService.handleGetUserById(id);
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.userService.handleGetUserById(id));
    return ResponseEntity.ok(this.userService.handleGetUserById(id));
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    this.userService.handleGetAllUsers();
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.userService.handleGetAllUsers());
    return ResponseEntity.ok(this.userService.handleGetAllUsers());
  }

  @PutMapping("/users")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    this.userService.handleUpdateUser(user);
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.userService.handleUpdateUser(user));
    return ResponseEntity.ok(this.userService.handleUpdateUser(user));
  }

}
