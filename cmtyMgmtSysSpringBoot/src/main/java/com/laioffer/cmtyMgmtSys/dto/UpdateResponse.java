package com.laioffer.cmtyMgmtSys.dto;

public class UpdateResponse {
    Boolean success;
    String reason;

    public UpdateResponse(Boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
