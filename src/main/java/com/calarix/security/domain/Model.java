package com.calarix.security.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Model {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;
}
