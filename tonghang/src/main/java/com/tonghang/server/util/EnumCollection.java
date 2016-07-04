package com.tonghang.server.util;

public class EnumCollection {

    public enum ResponseCode {
        SUCCESS("000001", "录入档案成功"), ERROR("000002", "档案录入失败"), DELETE_RECORD_SUCCESS(
                "000003", "删除档案成功"), DELETE_RECORD_ERROR("000004", "删除档案失败"), UPDATE_RECORD_SUCCESS(
                "000005", "修改档案成功"), UPDATE_RECORD_ERROR("000006", "修改档案失败"), ADD_REMARK_SUCCESS(
                "000007", "添加备注成功"), ADD_REMARK_ERROR("000008", "添加备注失败"), ADD_HEALTH_PLAN_SUCCESS(
                "000009", "添加健康计划成功"), ADD_HEALTH_PLAN_ERROR("000010",
                "添加健康计划失败"), UPDATE_HEALTH_PLAN_SUCCESS("000011", "更新病人健康计划成功"), UPDATE_HEALTH_PLAN_ERROR(
                "000012", "更新病人健康计划失败"), DELETE_HEALTH_PLAN_SUCCESS("000013",
                "删除病人健康计划成功"), DELETE_HEALTH_PLAN_ERROR("000014", "删除病人健康计划失败"), LOGIN_SUCCESS(
                "000015", "登入成功"), LOGIN_PASSWORD_ERROR("000016", "登入密码错误"), LOGIN_USER_NOT_EXIST(
                "000017", "登入用户不存在"), LOGIN_ERROR("000018", "登入错误"), LOGIN_ROLE_FAILED(
                "000019", "登入权限受限"), REGIST_SUCCESS("000020", "注册用户成功"), REGIST_ERROR(
                "000021", "注册用户失败"), REGIST_EXIST("000022","注册用户已存在");

        // 成员变量
        private String code;
        private String msg;

        // 构造方法
        private ResponseCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.code + "_" + this.msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

    public static void main(String[] args) {
    }
}
