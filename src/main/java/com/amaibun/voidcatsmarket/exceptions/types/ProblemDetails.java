package com.amaibun.voidcatsmarket.exceptions.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProblemDetails {
  private int status;
  private String error;
  private String message;
  private String path;
}
