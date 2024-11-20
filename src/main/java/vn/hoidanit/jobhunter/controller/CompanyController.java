package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.service.CompanyService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CompanyController {

  private final CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @PostMapping("/companies")
  public ResponseEntity<?> createCompany(@Valid @RequestBody Company reqCompany) {

    Company thanhCompany = this.companyService.handleCreateCompany(reqCompany);

    // return ResponseEntity.ok(thanhCompany);
    // return new ResponseEntity<>(thanhCompany, HttpStatus.CREATED);
    return ResponseEntity.status(HttpStatus.CREATED).body(thanhCompany);
  }

  @GetMapping("/companies")
  public ResponseEntity<List<Company>> getAllCompanies() {
    this.companyService.handleGetAllCompanies();
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.companyService.handleGetAllCompanies());
    return ResponseEntity.ok(this.companyService.handleGetAllCompanies());
  }

  @GetMapping("/companies/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
    this.companyService.handleGetCompanyById(id);
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.companyService.handleGetCompanyById(id));
    return ResponseEntity.ok(this.companyService.handleGetCompanyById(id));
  }

  @PutMapping("/companies")
  public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
    this.companyService.handleUpdateCompany(company);
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.userService.handleUpdateCompany(company));
    return ResponseEntity.ok(this.companyService.handleUpdateCompany(company));
  }

  @DeleteMapping("/companies/{id}")
  public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) {

    this.companyService.handleDeleteCompany(id);
    // return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    // return new ResponseEntity<String>("Delete successfully", HttpStatus.OK);
    // return ResponseEntity.ok("Delete successfully");
    return ResponseEntity.ok().build();
  }

}
