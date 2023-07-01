package com.example.uis.controllers;


import com.example.uis.dto.message.MessageCommandDTO;
import com.example.uis.dto.message.MessageQueryDTO;
import com.example.uis.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping
    public ResponseEntity<Void> addMessage(@RequestBody MessageCommandDTO messageCommandDTO)
    {
        messageService.save(messageCommandDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MessageQueryDTO>> findAllByCourseId(@PathVariable("id") Integer id)
    {
        List<MessageQueryDTO> messages = messageService.findAllByCourseId(id);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
