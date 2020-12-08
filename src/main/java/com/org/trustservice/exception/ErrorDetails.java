package com.org.trustservice.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorDetails {

  private String message;
  private LocalDateTime timeStamp;
  private String info;
}
