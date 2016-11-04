package com.spc.jpa.domain.user;

import org.springframework.data.rest.core.config.Projection;

/**
 * @author lKJ
 */
@Projection(name = "withPages", types = { User.class })
public interface BasicUserWithPagesProjection {

  String getId();
  
  String getName();
  
  String getPassword();
  
}
