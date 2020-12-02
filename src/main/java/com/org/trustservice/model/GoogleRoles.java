package com.org.trustservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google.role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleRoles {

  private String owner;
  private String reader;
  private String commenter;
  private String writer;
  private String unknown;
}
