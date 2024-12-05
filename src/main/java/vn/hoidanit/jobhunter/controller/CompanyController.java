package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.response.ResultPaginationDTO;
import vn.hoidanit.jobhunter.service.CompanyService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
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
  @ApiMessage("fetch companies")
  public ResponseEntity<ResultPaginationDTO> getCompany(
      @Filter Specification<Company> spec, Pageable pageable) {

    return ResponseEntity.ok(this.companyService.handleGetCompany(spec, pageable));
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.companyService.handleGetCompany());

  }

  @GetMapping("/companies/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
    this.companyService.handleGetCompanyById(id);
    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.companyService.handleGetCompanyById(id));
    return ResponseEntity.ok(this.companyService.handleGetCompanyById(id));
  }

  @PutMapping("/companies")
  public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company reqCompany) {
    Company updatedCompany = this.companyService.handleUpdateCompany(reqCompany);
    return ResponseEntity.ok(updatedCompany);

    // return
    // ResponseEntity.status(HttpStatus.OK).body(this.userService.handleUpdateCompany(reqCompany));

  }

  @DeleteMapping("/companies/{id}")
  public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) {

    this.companyService.handleDeleteCompany(id);
    return ResponseEntity.ok().build();

    // return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    // return new ResponseEntity<String>("Delete successfully", HttpStatus.OK);
    // return ResponseEntity.ok("Delete successfully");
  }

}
