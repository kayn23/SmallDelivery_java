
package com.kafpin.smallDelivery.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.kafpin.smallDelivery.dto.user.UserDetailsDto;
import com.kafpin.smallDelivery.dto.user.UserParamsDto;
import com.kafpin.smallDelivery.repositoryes.UserRepository;
import com.kafpin.smallDelivery.models.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Пользователи")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
  private UserRepository repository;
  private ModelMapper mapper;

  @GetMapping("")
  public List<UserDetailsDto> getAll(@RequestParam(name = "page", required = false) String pageQuery,
      @RequestParam(name = "per_page", required = false) String perPageQuery) {
    var p = pageQuery != null ? Integer.parseInt(pageQuery) : 0;
    var page = p > 0 ? p - 1 : p;
    var per_page = perPageQuery == null ? pageQuery == null ? 10000 : 100 : Integer.parseInt(perPageQuery);
    var pag = PageRequest.of(page, per_page);
    return repository.findAll(pag)
        .stream()
        .map(element -> mapper.map(element, UserDetailsDto.class))
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public UserDetailsDto show(@PathVariable String id) {
    var optionUser = repository.findById(Long.parseLong(id));
    if (optionUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return mapper.map(optionUser.get(), UserDetailsDto.class);
  }

  @PostMapping("")
  public Long create(@RequestBody UserParamsDto request) {
    var user = User.builder()
        .name(request.getName())
        .surname(request.getSurname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .role(request.getRole())
        .build();
    repository.save(user);
    return user.getId();
  }

  @PatchMapping("/{id}")
  public UserDetailsDto update(@PathVariable String id,
      @RequestBody UserParamsDto request) {
    var optionUser = repository.findById(Long.parseLong(id));
    if (optionUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    var user = optionUser.get();
    user.setName(request.getName());
    user.setSurname(request.getSurname());
    user.setLastname(request.getLastname());
    user.setRole(request.getRole());
    user.setEmail(request.getEmail());
    repository.save(user);
    return mapper.map(user, UserDetailsDto.class);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    var optionUser = repository.findById(Long.parseLong(id));
    if (optionUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    repository.delete(optionUser.get());
  }
}
