package com.ravi.model;

public class SwiftMsg {

    private String swiftMessage;
    private String refNum;
    private String hashValue;
    private String source;
    private String reconStatus;

    /**
     * @return the swiftMessage
     */
    public String getSwiftMessage() {
        return swiftMessage;
    }

    /**
     * @return the reconStatus
     */
    public String getReconStatus() {
        return reconStatus;
    }

    /**
     * @param reconStatus the reconStatus to set
     */
    public void setReconStatus(String reconStatus) {
        this.reconStatus = reconStatus;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the hashValue
     */
    public String getHashValue() {
        return hashValue;
    }

    /**
     * @param hashValue the hashValue to set
     */
    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    /**
     * @return the refNum
     */
    public String getRefNum() {
        return refNum;
    }

    /**
     * @param refNum the refNum to set
     */
    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    /**
     * @param swiftMessage the swiftMessage to set
     */
    public void setSwiftMessage(String swiftMessage) {
        this.swiftMessage = swiftMessage;
    }

    
}