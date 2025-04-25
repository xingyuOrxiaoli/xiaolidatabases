package com.xiaoli.constants;

/**
 * @program: itoken
 * @classname: HttpStatusConstants
 * @description: Http状态常量
 **/
public enum DeleteStatusEnum {

    /**
     * 已删除
     */
    DELETED(1, "已删除"),
    /**
     * 未删除
     */
    NO_DELETED(0, "未删除");

    private final int status;
    private final String note;

    DeleteStatusEnum(int status, String note) {
        this.status = status;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public int getStatus() {
        return status;
    }
}
