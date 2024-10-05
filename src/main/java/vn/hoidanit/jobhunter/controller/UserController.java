package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public User createNewUser(@RequestBody User postManUser) {

    // User user = new User();
    // user.setEmail("taothichmi2009@gmail.com");
    // user.setName("Thanh");
    // user.setPassword("123456");

    User thanhUser = this.userService.handleCreateUser(postManUser);

    return thanhUser;
  }

  @DeleteMapping("/user/{id}")
  public String deleteUser(@PathVariable("id") long id) {

    this.userService.handleDeleteUser(id);
    return "delete thanh";
  }

}
