package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageAdminController {

    @Autowired
    MessageService messageService;

}
