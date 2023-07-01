package com.example.uis.dto.message;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageCommandDTO {

    private Integer studentId;
    private Integer courseId;
    private String messageText;
    private LocalDateTime sentTime;
}
