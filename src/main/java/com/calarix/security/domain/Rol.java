package com.calarix.security.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Rol extends Model{
   public final static String ADMIN = "ADMIN";
   public final static String INVITADO = "INVITADO";

   public String name;
}
