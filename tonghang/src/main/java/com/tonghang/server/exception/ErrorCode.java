package com.tonghang.server.exception;

/**
 * <pre>
 * </pre>
 */
public enum ErrorCode {
    //
    /** 没有错误 */
    code0(200, 0, "ok"),
    code2(500, 2, "Error verify code "),
    /** 不支持此接口 */
    code3(500, 3, "unsupported open api"),
    /** 没有权限执行此操作 */
    code4(500, 4, "no permission to do this operation"),
    /** IP未授权 */
    code5(500, 5, "unauthorized client IP address"),
    /** appkey为空或不存在 */
    code6(500, 6, "appkey error"),
    /** appsecret错误 */
    code7(500, 7, "appsecret error"),
    code8(500, 8, "User not exist  "),
    code9(500, 9, "Password error   "),
    /** 服务器错误 */
    code10(500, 10, "server internal error"),
    /** 服务器不支持 */
    code11(500, 11, "not implement"),
    /** 服务不可用 */
    code12(500, 12, "service unavailable"),
    /** 服务器忙，稍候重试 */
    code13(500, 13, "service busy"),
    /** 无可用上传服务器，稍候重试 */
    code14(500, 14, "no available uploadserver"),
    code15(500, 15, "Tel number invalid"),
    /** 输入参数错误 */
    code20(500, 20, "param error"),
    /** 签名错误 */
    code21(500, 21, "signature error"),
    /** 用户请求错误 */
    code22(500, 22, "bad request"),
    /** 请求数超出限额 */
    code23(500, 23, "request exceed limit"),
    /** 禁止访问 */
    code24(500, 24, "access denied"),
    /** 重复请求 */
    code25(500, 25, "repeat request"),
    /** 小影平台登陆令牌不正确或者已经过期 */
    code50(500, 50, "accesstoken invalid or no longer valid"),
    /** 用户未授权 */
    code52(500, 52, "user is not authorized"),
    /** 小影平台上传令牌不正确或者已经过期 */
    code60(500, 60, "uploadtoken invalid or no longer valid"),
    //
    /** 用户唯一标识码不存在 */
    code101(500, 101, "auid invalid"),
    /** 登陆账号不存在 */
    code102(500, 102, "account invalid"),
    /** 密码不正确 */
    code103(500, 103, "password invalid"),
    /** 账号未激活 */
    code104(500, 104, "account nonactivated"),
    /** 账号被冻结 */
    code105(500, 105, "account freeze"),
    /** 账号被禁用 */
    code106(500, 106, "account forbidden"),
    /** 账号已注销 */
    code107(500, 107, "account canceled"),
    /** 第三方SNS网站账号错误或accesstoken失效 */
    code110(500, 110, "3rd snssite account error"),
    /** 第三方SNS网站账号未绑定 */
    code111(500, 111, "3rd snssite account not bind"),
    /** 用户标识码与被查询用户标示码不一致 **/
    code112(500, 112, "auid error"),
    /** 用户昵称已存在 **/
    code113(500, 113, "nike name exist"),
    /** 用户昵称被保留 **/
    code114(500, 114, "nike name exist"),

    /** 可能用户名密码不正确  导致系统找不到该合作方  */
    code115(500,115,"user partner is not exist ,please confirm userid nad password"),
    /** 请求参数不能为null  */
    code116(500,116,"user partner request parameter is null"),

    code150(500, 150, "follow  exist"),
    /** 用户昵称被保留 **/
    code160(500, 160, "not find solarise user"),
    //
    /** 手机imei和mac地址已变化，请重新注册 */
    code201(500, 201, "imei and mac changed"),
    //
    /** 设备唯一标识码不存在 */
    code202(500, 202, "duid invalid"),
    /** 设备被封 */
    code203(500, 203, "duid forbidden"),
    /** 小影工程编号(puid)错误或不存在 */
    code301(500, 301, "puid invalid or not exist"),
    /** 小影工程版本号不存在或错误 */
    code302(500, 302, "version invalid or not exist"),
    /** 小影工程发布失败 */
    code303(500, 303, "project publish error"),
    /** 合成微电影文件未上传或上传失败 */
    code304(500, 304, "video release file invalid"),
    /** 封面文件未上传或上传失败 */
    code305(500, 305, "cover file invalid"),
    /** 海报文件未上传或上传失败 */
    code306(500, 306, "poster file invalid"),
    /** 海报文件未上传或上传失败 */
    code307(500, 307, "该视频已经进入热门视频池！"),
    /** 海报文件未上传或上传失败 */
    code308(500, 308, "该视频入围活动！"),
    /** 小影工程版本已取消或已删除 */
    code310(500, 310, "publish canceled or deleted"),
    /** 喜欢视频失败 */
    code311(500, 311, "like video faile"),
    /** 取消喜欢失败 */
    code312(500, 312, "unlike video faile"),    
    /** 视频未审核 */
    code313(501, 313, "project is not review"),
    /** 视频描述审核失败 */
    code314(500, 314, "publish desc not passed"),
    
    /** 视频不存在     */
    code315(500, 315, "video not exists"),
    //
    /** 文件上传异常，请和服务器同步数据 */
    code601(500, 601, "File upload error,need synchronize data"),
    /** 文件MD5校验不符，上传失败 */
    code602(500, 602, "file md5 mismatch"),
    /** 文件上传获取token失败 */
    code603(500, 603, "get uploadtoken failed"),

    /** 消息不存在或错误 */
    code810(500, 810, "Message invalid or not exist"),
    /** 应用不存在或错误 */
    code830(500, 830, "Recommend App invalid or not exist"),

    /** 模板不存在或错误 */
    code850(500, 850, "Template invalid or not exist"),
    /** 主题专用模板不能单独下载 */
    code851(500, 851, "Theme template, can not be downloaded separately"),
    /** 用户未下载此模板，无法取消 */
    code852(500, 852, "Not download this template"),
    /** 用户未申请下载此模板，确认失败 */
    code853(500, 853, "Not request download this template,Confirm failed"),
    /** 地理位置请求失败 */
    code854(500, 854, "Get the location failed"),

    /** 无权删除评论 **/
    code855(500, 855, "No auth to delete the comment"),
    
    /** ip地址解析失败 **/
    code856(500, 856, "IP parse Failed"),
    /** 获取二级域名失败 **/
    code857(500, 857, "Get domain_pre Failed"),
    
    /** OTHER 未配置*/
    code858(500, 858, "default country OTHER  is not properly configured "),
    
    /** xy_countries 表中缺失countrycode */
    code859(500, 859, "the countrycode  is not exists in system"),

    /** redis中没有General Country  */
    code860(500, 860, "General Country 未被加载到redis中   或者general country 表中缺失数据"),
    
    code861(500, 861, "zone  configs not fixed"),
    
    code862(500, 862, "the country not in the zone "),
    
    /** 含有敏感词  */
    code870(500, 870, "bad word."),

    /** 用户被禁言 */
    code871(500, 871, "user is no talk."),
    /** 设备被禁言 */
    code872(500, 872, "device is no talk."),
    /** 获取环信用户失败*/
    code880(500, 880, "get huanxin user error"),
    
    /** 用户被加入黑名单  */
    code873(500, 873, "You are in black list");
    
    

    private int    httpCode; // 对应的httpcode，参照用
    private int    code;    // 业务code
    private String desc;    // 描述信息

    ErrorCode(int httpCode, int code, String desc){
        this.httpCode = httpCode;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 是否成功判断
     * 
     * @param code
     * @return
     */
    public static boolean isSuccess(int code) {
        if (code == code0.getCode()) {
            return true;
        }
        return false;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static int getHttpCodeByCode(int code) {
        ErrorCode[] codes = ErrorCode.values();
        for (ErrorCode c : codes) {
            if (c.getCode() == code) {
                return c.getHttpCode();
            }
        }
        return ErrorCode.code7.httpCode;
    }
}
