package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.response.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.CompanyRepository;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class CompanyService {

  private final CompanyRepository companyRepository;
  private final UserRepository userRepository;

  public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
    this.companyRepository = companyRepository;
    this.userRepository = userRepository;
  }

  // CREATE
  public Company handleCreateCompany(Company company) {
    return this.companyRepository.save(company);
  }

  // GET ALL
  public ResultPaginationDTO handleGetCompany(Specification<Company> spec, Pageable pageable) {
    Page<Company> pageCompany = this.companyRepository.findAll(spec, pageable);
    ResultPaginationDTO rs = new ResultPaginationDTO();
    ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

    mt.setPage(pageable.getPageNumber() + 1);// Trang bao nhiêu
    mt.setPageSize(pageable.getPageSize());// Tối đa bao nhiêu phần tử

    mt.setPages(pageCompany.getTotalPages());// Tổng số trang
    mt.setTotal(pageCompany.getTotalElements()); // Tổng số phần tử có trong

    rs.setMeta(mt);
    rs.setResult(pageCompany.getContent());
    return rs;

  }

  // GET BY ID
  public Company handleGetCompanyById(long id) {
    Optional<Company> companyOptional = this.companyRepository.findById(id);
    if (companyOptional.isPresent()) {
      return companyOptional.get();
    }
    return null;
  }

  // UPDATE
  // CÁCH 1
  // public Company handleUpdateCompany(Company updateCompany) {
  // Company existingCompany =
  // this.companyRepository.findById(updateCompany.getId()).orElse(null);
  // if (existingCompany != null) {
  // existingCompany.setName(updateCompany.getName());
  // existingCompany.setDescription(updateCompany.getDescription());
  // existingCompany.setAddress(updateCompany.getAddress());
  // existingCompany.setLogo(updateCompany.getLogo());
  // existingCompany = this.companyRepository.save(existingCompany);
  // }
  // return existingCompany;
  // }

  // UPDATE
  // CÁCH 2
  // public Company handleUpdateCompany(Company updateCompany) {
  // Company existingCompany = this.handleGetCompanyById(updateCompany.getId());
  // if (existingCompany != null) {
  // existingCompany.setName(updateCompany.getName());
  // existingCompany.setDescription(updateCompany.getDescription());
  // existingCompany.setAddress(updateCompany.getAddress());
  // existingCompany.setLogo(updateCompany.getLogo());
  // // update
  // existingCompany = this.companyRepository.save(existingCompany);
  // }
  // return existingCompany;
  // }

  // UPDATE
  // CÁCH 3
  public Company handleUpdateCompany(Company c) {
    Optional<Company> companyOptional = this.companyRepository.findById(c.getId());
    if (companyOptional.isPresent()) {
      Company currentCompany = companyOptional.get();
      currentCompany.setLogo(c.getLogo());
      currentCompany.setName(c.getName());
      currentCompany.setDescription(c.getDescription());
      currentCompany.setAddress(c.getAddress());
      return this.companyRepository.save(currentCompany);
    }
    return null;
  }

  // DELETE
  public void handleDeleteCompany(long id) {
    Optional<Company> comOptional = this.companyRepository.findById(id);
    if (comOptional.isPresent()) {
      Company com = comOptional.get();
      // fetch all user belong to this company
      List<User> users = this.userRepository.findByCompany(com);
      this.userRepository.deleteAll(users);
    }

    this.companyRepository.deleteById(id);
  }

  public Optional<Company> findById(long id) {
    return this.companyRepository.findById(id);
  }

}
