package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.UserRepository;
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
  public ResultPaginationDTO handleGetAllUsers(Specification<User> spec, Pageable pageable) {
    Page<User> pageUser = this.userRepository.findAll(spec, pageable);
    ResultPaginationDTO rs = new ResultPaginationDTO();
    Meta mt = new Meta();

    mt.setPage(pageUser.getNumber() + 1); // Trang bao nhiêu
    mt.setPageSize(pageUser.getSize()); // Tối đa bao nhiêu phần tử

    mt.setPages(pageUser.getTotalPages()); // Tổng số trang
    mt.setTotal(pageUser.getTotalElements()); // Tổng số phần tử có trong Database

    rs.setMeta(mt);
    rs.setResult(pageUser.getContent());

    return rs;
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

  public User handleGetUserByUsername(String username) {
    return this.userRepository.findByEmail(username);
  }
}
