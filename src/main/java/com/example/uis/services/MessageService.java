package com.example.uis.services;


import com.example.uis.dto.message.MessageCommandDTO;
import com.example.uis.dto.message.MessageQueryDTO;
import com.example.uis.entities.Course;
import com.example.uis.entities.Message;
import com.example.uis.entities.Student;
import com.example.uis.mappers.MessageMapper;
import com.example.uis.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private MessageMapper messageMapper;


    public void save(MessageCommandDTO messageCommandDTO)
    {
        Student student = studentService.findById(messageCommandDTO.getStudentId());
        Course course = courseService.findById(messageCommandDTO.getCourseId());

        Message message = messageMapper.commandDtoToEntity(messageCommandDTO);
        message.setStudent(student);
        message.setCourse(course);

        messageRepository.save(message);
    }

    public List<MessageQueryDTO> findAllByCourseId(Integer id)
    {
        return messageRepository.findAllByCourseId(id)
            .stream().map(messageMapper :: entityToQueryDto).toList();
    }
}
