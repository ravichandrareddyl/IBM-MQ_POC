package com.ravi.controller;

import java.util.List;

import com.ravi.dao.DBRepo;
import com.ravi.model.SwiftMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReconController {

    @Autowired
    private DBRepo dao;

    /**
     * 
     * @return JSON array on messages
     */
    @RequestMapping("/messages")
    public List<SwiftMsg> getMessages() {
        return dao.getMessages();
    }

    /**
     * 
     * @return JSON array of unreconciled messages
     */
    @PostMapping("/unreconciled")
    public List<SwiftMsg> getUnreconciledMsgs() {
        return dao.getUnreconciledMsgs();
    }

}