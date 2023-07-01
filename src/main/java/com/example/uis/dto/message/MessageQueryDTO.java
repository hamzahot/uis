package com.example.uis.dto.message;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageQueryDTO {

    private String messageText;
    private LocalDateTime sentTime;
    private String studentName;
}
