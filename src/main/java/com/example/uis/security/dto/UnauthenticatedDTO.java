package com.example.uis.security.dto;

import lombok.Data;

@Data // @RequiredArgsConstructor | @Getter | @Setter | @NoArgsConstructor | @ToString
public class UnauthenticatedDTO {

    private final String message;
    private final String exceptionMessage;

}
