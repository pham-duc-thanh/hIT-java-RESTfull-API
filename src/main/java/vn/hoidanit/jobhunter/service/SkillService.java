package vn.hoidanit.jobhunter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Skill;
import vn.hoidanit.jobhunter.domain.response.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.SkillRepository;

@Service
public class SkillService {

  private final SkillRepository skillRepository;

  public SkillService(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  public boolean isNameExist(String name) {
    return this.skillRepository.existsByName(name);
  }

  // GET ALL
  public ResultPaginationDTO handleGetSkill(Specification<Skill> spec, Pageable pageable) {
    Page<Skill> pageSkill = this.skillRepository.findAll(spec, pageable);
    ResultPaginationDTO rs = new ResultPaginationDTO();
    ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

    mt.setPage(pageable.getPageNumber() + 1);// Trang bao nhiêu
    mt.setPageSize(pageable.getPageSize());// Tối đa bao nhiêu phần tử

    mt.setPages(pageSkill.getTotalPages());// Tổng số trang
    mt.setTotal(pageSkill.getTotalElements()); // Tổng số phần tử có trong

    rs.setMeta(mt);
    rs.setResult(pageSkill.getContent());
    return rs;
  }

  // CREATE
  public Skill handleCreateSkill(Skill skill) {
    return this.skillRepository.save(skill);
  }

  // UPDATE
  public Skill handleUpdateSkill(Skill skill) {
    Optional<Skill> skillOptional = this.skillRepository.findById(skill.getId());
    if (skillOptional.isPresent()) {
      Skill currentSkill = skillOptional.get();
      currentSkill.setName(skill.getName());
      return this.skillRepository.save(currentSkill);
    }
    return null;
  }

  // DELETE
  public void handleDeleteSkill(long id) {
    this.skillRepository.deleteById(id);
  }

  public Skill fetchSkillById(long id) {
    Optional<Skill> skillOptional = this.skillRepository.findById(id);
    if (skillOptional.isPresent()) {
      return skillOptional.get();
    }
    return null;
  }
}
