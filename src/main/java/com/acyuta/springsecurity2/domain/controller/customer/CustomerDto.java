package com.acyuta.springsecurity2.domain.controller.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Integer id;

    private String email;

    private String pwd;

    private Set<String> authorities;
}
