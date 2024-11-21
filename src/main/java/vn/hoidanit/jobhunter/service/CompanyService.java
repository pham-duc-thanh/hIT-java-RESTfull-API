package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {

  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  // CREATE
  public Company handleCreateCompany(Company company) {
    return this.companyRepository.save(company);
  }

  // GET ALL
  public ResultPaginationDTO handleGetCompany(Pageable pageable) {
    Page<Company> pageCompany = this.companyRepository.findAll(pageable);
    ResultPaginationDTO rs = new ResultPaginationDTO();
    Meta mt = new Meta();

    mt.setPage(pageCompany.getNumber() + 1);
    mt.setPageSize(pageCompany.getSize());

    mt.setPages(pageCompany.getTotalPages());
    mt.setTotal(pageCompany.getTotalElements());

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
    this.companyRepository.deleteById(id);
  }
}
