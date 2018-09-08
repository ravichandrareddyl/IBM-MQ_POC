package com.ravi.dao;

import com.ravi.model.SwiftMsg;
import com.ravi.model.SwiftMsgRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import static com.ravi.constants.AppConstants.INSERT_MSG;
import static com.ravi.constants.AppConstants.UPDTE_MSG;

import java.util.List;

import static com.ravi.constants.AppConstants.INSERT_UNRECONCILED;
import static com.ravi.constants.AppConstants.COMPLETE_STATUS;
import static com.ravi.constants.AppConstants.GET_ALL_MESSAGES;
import static com.ravi.constants.AppConstants.GET_UNRECONCILED_MSGS;


@Service
public class DBRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SwiftMsg> getMessages() {
        return jdbcTemplate.query(GET_ALL_MESSAGES, new SwiftMsgRowMapper());
    }

    public List<SwiftMsg> getUnreconciledMsgs() {
        return jdbcTemplate.query(GET_UNRECONCILED_MSGS, new SwiftMsgRowMapper());
    }

    public void saveMsg(SwiftMsg msg) {
        jdbcTemplate.update(INSERT_MSG, new Object[] { msg.getRefNum(), 
            msg.getSwiftMessage(), msg.getHashValue() });
    }

    public int reconcileMsg(SwiftMsg msg) {
        return jdbcTemplate.update(UPDTE_MSG, new Object[] { COMPLETE_STATUS, 
            msg.getRefNum(), msg.getHashValue() });
    }

    public void saveUnreconciledMsg (SwiftMsg msg) {
        jdbcTemplate.update(INSERT_UNRECONCILED, 
        new Object[] {msg.getRefNum(), msg.getSwiftMessage(), 
            msg.getHashValue(), msg.getSource()});
    }
}