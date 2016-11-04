package com.spc.jpa.domain.user;

import org.joda.time.DateTime;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author lKJ
 */
@Projection(name = "basic", types = { User.class })
public interface BasicUserProjection {

  String getId();
  
  String getName();
  
  String getPassword();
  
  String getCreatedBy();
  
  DateTime getCreatedTime();
  
  String getModifiedBy();
  
  DateTime getModifiedTime();

}
