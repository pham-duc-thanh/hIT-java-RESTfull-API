package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Company;
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
  public List<Company> handleGetAllCompanies() {
    return this.companyRepository.findAll();
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

  // CÁCH 2
  public Company handleUpdateCompany(Company updateCompany) {
    Company existingCompany = this.handleGetCompanyById(updateCompany.getId());
    if (existingCompany != null) {
      existingCompany.setName(updateCompany.getName());
      existingCompany.setDescription(updateCompany.getDescription());
      existingCompany.setAddress(updateCompany.getAddress());
      existingCompany.setLogo(updateCompany.getLogo());

      // update
      existingCompany = this.companyRepository.save(existingCompany);
    }
    return existingCompany;
  }

  // DELETE
  public void handleDeleteCompany(long id) {
    this.companyRepository.deleteById(id);
  }
}
