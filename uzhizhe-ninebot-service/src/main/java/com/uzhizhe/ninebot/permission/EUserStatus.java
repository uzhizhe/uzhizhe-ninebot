package com.uzhizhe.ninebot.permission;

/**
 * @Desc user status
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-14
 */
public enum EUserStatus {
    Normal(1, "正常"),
    Frozen(2, "冻结");

    private int id;
    private String desc;

    EUserStatus(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
