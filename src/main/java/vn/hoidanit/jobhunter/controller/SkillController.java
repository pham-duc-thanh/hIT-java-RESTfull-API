package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Skill;
import vn.hoidanit.jobhunter.domain.response.ResultPaginationDTO;
import vn.hoidanit.jobhunter.service.SkillService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
public class SkillController {

  private final SkillService skillService;

  public SkillController(SkillService skillService) {
    this.skillService = skillService;
  }

  @GetMapping("/skills")
  @ApiMessage("fetch all skills")
  public ResponseEntity<ResultPaginationDTO> getAllSkills(@Filter Specification<Skill> spec, Pageable pageable) {

    return ResponseEntity.ok().body(this.skillService.handleGetSkill(spec, pageable));
  }

  @PostMapping("/skills")
  @ApiMessage("Create a skill")

  public ResponseEntity<Skill> createSkill(@Valid @RequestBody Skill reqSkill) throws IdInvalidException {

    boolean isNameExist = this.skillService.isNameExist(reqSkill.getName());
    if (isNameExist) {
      throw new IdInvalidException(
          "Skill name = " + reqSkill.getName() + " đã tồn tại");
    }

    Skill thanhSkill = this.skillService.handleCreateSkill(reqSkill);

    return ResponseEntity.status(HttpStatus.CREATED).body(thanhSkill);
  }

  @PutMapping("/skills")
  @ApiMessage("Update a skill")

  public ResponseEntity<Skill> updateSkill(@Valid @RequestBody Skill skill) throws IdInvalidException {
    // Kiểm tra xem tên kỹ năng mới đã tồn tại chưa
    boolean existingSkill = this.skillService.isNameExist(skill.getName());

    if (existingSkill) {
      throw new IdInvalidException("Skill name = " + skill.getName() + " đã tồn tại");
    }

    Skill updatedSkill = this.skillService.handleUpdateSkill(skill);

    return ResponseEntity.status(HttpStatus.OK).body(updatedSkill);
  }

  @DeleteMapping("/skills/{id}")
  @ApiMessage("Delete a skill")
  public ResponseEntity<Void> deleteSkill(@PathVariable("id") long id) throws IdInvalidException {

    Skill currentSkill = this.skillService.fetchSkillById(id);
    if (currentSkill == null) {
      throw new IdInvalidException("Skill với id = " + id + " không tồn tại");
    }

    this.skillService.handleDeleteSkill(id);

    return ResponseEntity.ok().build();
  }

}
