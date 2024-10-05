package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // CREATE
  public User handleCreateUser(User user) {
    return this.userRepository.save(user);
  }

  // DELETE
  public void handleDeleteUser(long id) {
    this.userRepository.deleteById(id);
  }

  // GET BY ID
  public User handleGetUserById(long id) {
    Optional<User> userOptional = this.userRepository.findById(id);
    if (userOptional.isPresent()) {
      return userOptional.get();
    }
    return null;
  }

  // GET ALL
  public List<User> handleGetAllUsers() {
    return this.userRepository.findAll();
  }

  // UPDATE
  // CÁCH 1
  // public User handleUpdateUser(User updatedUser) {
  // User existingUser =
  // this.userRepository.findById(updatedUser.getId()).orElse(null);
  // if (existingUser != null) {
  // existingUser.setName(updatedUser.getName());
  // existingUser.setEmail(updatedUser.getEmail());
  // existingUser.setPassword(updatedUser.getPassword());

  // existingUser = this.userRepository.save(existingUser);
  // }
  // return existingUser;
  // }

  // CÁCH 2
  public User handleUpdateUser(User updatedUser) {
    User existingUser = this.handleGetUserById(updatedUser.getId());
    if (existingUser != null) {
      existingUser.setName(updatedUser.getName());
      existingUser.setEmail(updatedUser.getEmail());
      existingUser.setPassword(updatedUser.getPassword());

      // update
      existingUser = this.userRepository.save(existingUser);
    }
    return existingUser;
  }
}
