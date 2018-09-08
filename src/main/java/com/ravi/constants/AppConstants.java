package com.ravi.constants;

public interface AppConstants {
    
    //being considered as target system queues
    public static final String DESTINATION_QUEUE_1 = "DEV.QUEUE.1";
    public static final String DESTINATION_QUEUE_2 = "DEV.QUEUE.2";

    //source data topic
    public static final String SOURCE_TOPIC  = "dev/";

    public static final String COMPLETE_STATUS = "COMPLETED";

    public static final String SUBSCRIPTION_ID = "DEV_TOPIC_SUBSCRIBER";

    public static final String SHA256 = "SHA-256";

    public static final String INSERT_MSG = "INSERT INTO RECON_TABLE"
                                + " (ID, REF_NUM, SWIFT_MSG, HASH_VALUE, CREATED_DATE, MODIFIED_DATE)"
                                + " VALUES (MSG_SEQ.NEXTVAL ,? ,? ,?, SYSDATE, SYSDATE) ";

    public static final String UPDTE_MSG = "UPDATE RECON_TABLE"
                                + " SET RECON_STATUS = ?,"
                                + " MODIFIED_DATE = SYSDATE"
                                + " WHERE RECON_STATUS IS NULL AND REF_NUM = ? AND HASH_VALUE = ?";

    public static final String INSERT_UNRECONCILED = "INSERT INTO UNRECONCILED_MSGS"
                                + " (ID, REF_NUM, SWIFT_MSG, HASH_VALUE, SOURCE, CREATED_DATE, MODIFIED_DATE)"
                                + " VALUES (UN_RECON_MSG_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, SYSDATE)";


    public static final String GET_ALL_MESSAGES = "SELECT * FROM RECON_TABLE";

    public static final String GET_UNRECONCILED_MSGS = "SELECT * FROM UNRECONCILED_MSGS";


    //table column names
    public static final String REF_NUM = "REF_NUM";
    public static final String SWIFT_MSG = "SWIFT_MSG";
    public static final String HASH_VALUE = "HASH_VALUE";
    public static final String SOURCE = "SOURCE";
    public static final String RECON_STATUS = "RECON_STATUS";
}