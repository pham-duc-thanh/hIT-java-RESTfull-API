package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/create")
  public String createNewUser() {

    User user = new User();
    user.setEmail("taothichmi2009@gmail.com");
    user.setName("Thanh");
    user.setPassword("123456");

    this.userService.handleCreateUser(user);

    return "tạo mới Người dùng";
  }

}
