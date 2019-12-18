package com.example.demo.common.sign;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
public enum  PermissionEnum {
    LOGIN("login"),
    CREATE_ADMIN("ceateAdmin"),
    DASHBOARD_VIEW("dashboard_view"),
    USER_DATA_VIEW("userDataView"),;

    private String permission;


    PermissionEnum(String permission){
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
