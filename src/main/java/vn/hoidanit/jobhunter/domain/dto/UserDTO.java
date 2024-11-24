package vn.hoidanit.jobhunter.domain.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import vn.hoidanit.jobhunter.util.SecurityUtil;
import vn.hoidanit.jobhunter.util.constant.GenderEnum;

@Getter
@Setter
public class UserDTO {

  private long id;

  private String name;
  private String email;
  // private String password;
  private int age;

  @Enumerated(EnumType.STRING)
  private GenderEnum gender;

  private String address;
  // private String refreshToken;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+9")
  private Instant createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+9")
  private Instant updatedAt;

  private String createdBy;

  private String updatedBy;

  @PrePersist
  public void handleBeforeCreate() {
    this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() == true ? SecurityUtil.getCurrentUserLogin().get()
        : "";

    this.createdAt = Instant.now();
  }

  @PreUpdate
  public void handleBeforeUpdate() {
    this.updatedBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
        ? SecurityUtil.getCurrentUserLogin().get()
        : "";

    this.updatedAt = Instant.now();
  }

}
