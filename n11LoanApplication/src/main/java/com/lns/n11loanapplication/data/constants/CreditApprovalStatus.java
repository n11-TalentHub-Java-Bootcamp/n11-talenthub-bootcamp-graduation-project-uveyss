package com.lns.n11loanapplication.data.constants;

public enum CreditApprovalStatus {
    APPROVED (1),
    REJECTED(2);
    private byte approvalStatus;
    CreditApprovalStatus(byte approvalStatus) {
        this.approvalStatus=approvalStatus;
    }

    CreditApprovalStatus(int i) {
    }

    public byte getApprovalStatus()
    {
        return approvalStatus;
    }
}
