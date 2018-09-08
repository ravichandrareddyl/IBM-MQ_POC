package com.ravi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import static com.ravi.constants.AppConstants.HASH_VALUE;
import static com.ravi.constants.AppConstants.SOURCE;
import static com.ravi.constants.AppConstants.SWIFT_MSG;
import static com.ravi.constants.AppConstants.REF_NUM;
import static com.ravi.constants.AppConstants.RECON_STATUS;


public class SwiftMsgRowMapper implements RowMapper {

    @Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SwiftMsg msg = new SwiftMsg();
        msg.setHashValue(rs.getString(HASH_VALUE));
        msg.setRefNum(rs.getString(REF_NUM));
        msg.setSource(rs.getString(SOURCE));
        msg.setSwiftMessage(rs.getString(SWIFT_MSG));
        msg.setReconStatus(rs.getString(RECON_STATUS));
		return msg;
	}

}