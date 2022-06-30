package com.gd.manage.controller;

import com.gd.manage.common.result.Result;
import com.gd.manage.entity.dto.UserUpdateDTO;
import com.gd.manage.entity.po.UserPO;
import com.gd.manage.service.UserService;
import com.gd.manage.shiro.session.SessionManage;
import com.gd.manage.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author gq
 * @date 2022/6/27 0027 14:06
 */
@RestController
@RequestMapping("/api/v1/index")
public class LoginApiController {

    private static final Logger logger = LoggerFactory.getLogger(LoginApiController.class);

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody UserPO userBean,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        return doLogin(userBean.getUserName(), userBean.getPassword(), request, response);
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public Map<String, Object> login(@RequestParam String userName, @RequestParam String password,
//                                     HttpServletRequest request,
//                                     HttpServletResponse response) {
//        return doLogin(userName, password, request, response);
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
        return doLogout(request, response);
    }

    @RequestMapping(value = "/notLogin")
    public Result notLogin() {
        return Result.fail("未登录", null, null);
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST)
    public Map<String, Object> getCurrentUser() {
        Map<String, Object> result = new HashMap<>();
        UserPO user;
        Subject subject = SecurityUtils.getSubject();

        // 如果已经登录，跳过登录步骤，返回登录成功
        if (subject.isAuthenticated()) {

            user = SessionManage.getCurrentUser();
            combineResult(result, user);  // 组装返回结构

            result.put("success", true);
            result.put("code", "200");
            result.put("message", "登录成功");
            return result;
        } else {
            result.put("success", false);
            result.put("code", "500");
            result.put("message", "未登录");
            return result;
        }
    }

//    @RequestMapping(value = "/getCurrentUserMenuList", method = RequestMethod.GET)
//    @ApiOperation(value = "获取当前用户菜单", notes = "获取当前用户菜单")
//    public Map<String, Object> getCurrentUserMenuList(@ApiParam(required = true, value = "应用编码") @RequestParam String appCode) {
//        Map<String, Object> result = new HashMap<>();
//        UserBean user = SessionManage.getCurrentUser();
//
//        result.put("menuList", menuService.queryUserAppMenu(user.getId(), appCode));   //当前用户菜单
//        result.put("success", true);
//        result.put("code", "200");
//        result.put("message", "查询成功");
//        return result;
//    }

//    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
//    public Result resetPwd(@RequestBody Map<String, Object> params, HttpServletRequest request) {
//        String originalPwd = (String) params.get("originalPassword"); // 原始密码
//        String newPwd = (String) params.get("newPassWord");  // 修改密码
//
//        UserPO userBean = userService.get(SessionManage.getCurrentUser().getId());
//        Assert.notNull(userBean, "账号不存在");
//
//        //原始密码校验
//        Assert.isTrue(userBean.getPassword().equals(MD5Encrypt.encode(originalPwd)), "原密码不正确");
//
//        //修改密码
//        userService.resetPwd(userVO.getUserName(), newPwd);
//
////        logRecord(request, Constants.OperateType.MODIFY, "修改密码", "/api/v1/resetPwd", ".resetPwd");
//
//        return Result.ok("修改密码成功");
//    }

//
//    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
//    public JSONResult modifyPwd(@ApiParam(required = true, value = "新密码和确认密码以及验证码") @RequestBody Map<String, Object> params, HttpServletRequest request) {
//        String userName = (String) params.get("userName");
//        String confirmPassword = (String) params.get("confirmPassword"); // 原始密码
//        String newPwd = (String) params.get("newPassWord");              // 修改密码
//        String validCode = (String) params.get("validCode");             // 验证码
//
//        Assert.hasText(userName, "缺少参数userName");
//        //验证两次输入密码是否一致
//        Assert.isTrue(newPwd.equals(confirmPassword), "新密码和确认密码不同");
//
//        //验证账号是否存在
//        UserBean user = userService.getUserByUserName(userName);
//        Assert.notNull(user, "用户名不存在");
//
//        UserVO userVO = userRemoteApi.getByUserName(user.getUserName()).getData();
//        Assert.notNull(userVO, "未在认证中心查询到账号");
//
//
//        //验证验证码的正确性
//        UserValidCodePO userValidCodePO = userValidCodeMapper.selectByUserId(user.getId());
//        Assert.notNull(userValidCodePO, "请重新发送验证码");
//
//        Assert.isTrue(validCode.equals(userValidCodePO.getValidCode()), "验证码错误");
//
//        //修改密码
//        userService.resetPwd(user.getUserName(), newPwd);
//        //验证码记录清除
//        userValidCodeMapper.deleteByUserId(user.getId());
//
//        //记录登录日志
//        logRecord(request, Constants.OperateType.MODIFY, "修改密码", "/api/v1/modifyPwd", ".modifyPwd");
//
//        return new JSONResult(true, String.valueOf(HttpStatus.OK), "修改密码成功");
//    }


//    @ApiOperation(value = "发送验证码")
//    @RequestMapping(value = "/validCode/send", method = RequestMethod.GET)
//    public Result sendValidCode(@ApiParam(required = true, value = "手机号") @RequestParam String cellphone) {
//        Assert.notNull(cellphone, "手机号不能为空");
//
////        String pattern = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
////        if(cellphone.length() != 11){
////            throw new BusinessException("400", "账号错误");
////        }else{
////            Pattern p = Pattern.compile(pattern);
////            Matcher m = p.matcher(cellphone);
////            boolean isMatch = m.matches();
////            if(!isMatch) {
////                throw new BusinessException("400", "不是手机号，请填写正确手机号");
////            }
////        }
//        if (cellphone.length() != 11) {
//            throw new BusinessException("400", "账号错误");
//        }
//
//        UserBean user = userService.getUserByUserName(cellphone);
//        Assert.notNull(user, "手机号未注册");
//
//        //根据用户id查询验证码是否存在
//        UserValidCodePO userValidCodePO = userValidCodeMapper.selectByUserId(user.getId());
//        //记录不存在
//        if (userValidCodePO == null) {
//            insertCode(user.getId(), cellphone);//生成并发送验证码
//        } else {
//            //记录存在且验证码时间没过期
//            if (userValidCodePO.getExpireTime().getTime() - System.currentTimeMillis() >= 0) {
//
//                //发送验证码时间没超过60s则可能多人同时操作，不在生成验证码
//                if ((userValidCodePO.getExpireTime().getTime() - System.currentTimeMillis()) > 4 * 60000) {
//                    throw new BusinessException("400", "正在修改密码中，请稍后再试");
//                } else {//没过期前多次点击发送验证码则更新验证码
//                    userValidCodePO.setValidCode(RandomStringUtils.randomNumeric(4));
//                    long expireTime = new Date().getTime() + 5 * 60000;
//                    userValidCodePO.setExpireTime(new Date(expireTime));
//                    userValidCodeMapper.updateByPrimaryKeySelective(userValidCodePO);
//                    smsService.sendValidCode(cellphone, userValidCodePO.getValidCode());
//                }
//            }
//
//            //记录存在但验证码过期则删除原来记录重新生成
//            if (userValidCodePO.getExpireTime().getTime() - System.currentTimeMillis() < 0) {
//                userValidCodeMapper.deleteByUserId(user.getId());
//                insertCode(user.getId(), cellphone);//生成验证码
//            }
//        }
//        return new Result(true, HttpStatus.OK.toString(), "发送成功");
//    }
//
//    @ApiOperation(value = "获取验证码")
//    @RequestMapping(value = "/validCode/get", method = RequestMethod.GET)
//    public Result getValidCode(@ApiParam(required = true, value = "手机号") @RequestParam String cellphone) throws Exception {
//        Assert.notNull(cellphone, "手机号不能为空");
//
//        String regx = "^[1][0-9]{10}$";
//        Assert.isTrue(Pattern.compile(regx).matcher(cellphone).matches(), "非手机号！");
//
//        UserVO userVO = userRemoteApi.getByUserName(cellphone).getData();
//        Assert.notNull(userVO, "未在认证中心注册此账号");
//
//        UserBean user = userService.getUserByUserName(cellphone);
//        Assert.notNull(user, "项目应用未开通此账号");
//
//        String validCode;
//        if (enabled) {
//            validCode = RandomStringUtils.randomNumeric(4);
//            Map<String, String> params = Maps.newHashMap();
//            params.put("code", validCode);
//
//            // 发送验证码
//            SmsSendDTO sendDTO = new SmsSendDTO();
//            sendDTO.setCellphone(cellphone);
//            sendDTO.setSignName(signature);
//            sendDTO.setTemplateCode(templateCode);
//            sendDTO.setTemplateParams(params);
//            smsRemoteApi.send(sendDTO);
//        } else {
//            validCode = DateFormatUtils.format(new Date(), "HHmm");
//        }
//
//        validCodeService.save(cellphone, validCode);
//        return new Result(true, HttpStatus.OK.toString(), "发送成功");
//    }
//
//    @RequestMapping(value = "/validCodeLogin", method = RequestMethod.GET)
//    @ApiOperation(value = "验证码登录", notes = "验证码登录")
//    public Map<String, Object> validCodeLogin(@ApiParam(required = true, value = "手机号") @RequestParam String cellphone,
//                                              @ApiParam(required = true, value = "验证码") @RequestParam String code,
//                                              HttpServletRequest request, HttpServletResponse response) {
//        // 验证码校验
//        ValidCodePO validCodePO = validCodeService.getByCellphone(cellphone);
//        Assert.isTrue(validCodePO != null && validCodePO.getCode().equals(code), "验证码无效");
//
//        long validCodeTime = validCodePO.getUpdateTime() == null ? validCodePO.getCreateTime().getTime() : validCodePO.getUpdateTime().getTime();
//        Assert.isTrue(System.currentTimeMillis() - validCodeTime < validCodePO.getExpires() * 1000, "验证码过期！");
//
//        // 用户登录
//        UserVO userVO = userRemoteApi.getByUserName(cellphone).getData();
//        Map<String, Object> result = doLogin(userVO.getUserName(), userVO.getPassword(), webappCode, request, response);
//
//        // 清除验证码
//        validCodeService.delete(validCodePO.getId());
//        return result;
//    }

    /**
     * 执行登录
     *
     * @param userName 用户名
     * @param password 密码
     * @param request  请求对象
     * @param appCode  app代码
     * @return 组装数据
     */
    /**
     * 执行登录
     *
     * @param userName 用户名
     * @param password 密码
     * @param request  请求对象
     * @return 组装数据
     */
    private Map<String, Object> doLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
        boolean success;
        String msg;
        String code;
        String err = "";

        Map<String, Object> result = new HashMap<>();

        //账号登录验证
        UserPO user = userService.getByUserName(userName);
        Assert.notNull(user, "用户名或密码错误");
        Assert.isTrue(user.getForbidden().equals("1"), "账号已被禁用，请联系管理员");  //1启用 2停用/锁定


        Subject subject = SecurityUtils.getSubject();
        // 如果已经登录，跳过登录步骤，返回登录成功
        if (subject.isAuthenticated() && subject.getPrincipal().toString().equals(userName)) {
            logger.info("{} have logined. sessionid : {}", subject.getPrincipal(), subject.getSession().getId());

            user = SessionManage.getCurrentUser();
            combineResult(result, user);  // 组装返回结构

            result.put("success", true);
            result.put("code", "200");
            result.put("message", "登录成功");
            return result;
        }

        try {

            // 创建令牌执行登录
            userName = userName.trim();
            password = password.trim();

            UsernamePasswordToken token = new UsernamePasswordToken(userName, ShiroUtils.md5(password, user.getSalt()));
            subject.login(token);

            logger.info("{} login success. sessionid : {}", subject.getPrincipal(), subject.getSession().getId());
            updateUser(user);
            // 保存Session
//            setUnit(user);                        // 设置用户单位
            SessionManage.setCurrentUser(user);   // 设置当前用户信息
            //组装返回结构
            combineResult(result, user);

            success = true;
            code = "200";
            msg = "登录成功";

//            //记录登录日志
//            logRecord(request, Constants.OperateType.LOGIN, "登录系统", "/api/v1/login", ".login");
//
//            // 记录登录日志
//            LoginLogBean loginLogBean = new LoginLogBean();
//            loginLogBean.setLoginType(Constants.OperateType.LOGIN);
//            loginLogBean.setClientType(NetworkUtil.getClientType(request));
//            loginLogBean.setClientAgent(request.getHeader("User-Agent"));
//            loginLogBean.setIp(NetworkUtil.getIpAddress(request));
//            loginLogService.insert(loginLogBean);

        } catch (IncorrectCredentialsException e) {
            success = false;
            code = "400";
            msg = "用户名或密码错误";
        } catch (UnknownAccountException e) {
            success = false;
            code = "400";
            msg = "用户名或密码错误";
        } catch (AuthenticationException e) {
            success = false;
            code = "400";
            msg = "用户名或密码错误";
        } catch (Exception e) {
            success = false;
            code = "500";
            msg = "网络发生异常，登录失败";
//            LOGGER.debug("{} login failure!!!", userName);
        }

        result.put("success", success);
        result.put("code", code);
        result.put("message", msg);
        result.put("error", err);
        return result;
    }

    /**
     * 更新人员信息
     * @param userPO
     */
    private void updateUser(UserPO userPO){
        userPO.setLoginTime(new Date());
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        BeanUtils.copyProperties(userPO,userUpdateDTO);
        userService.update(userUpdateDTO);
    }


    /**
     * 执行注销登出
     *
     * @param request
     * @return
     */
    private Map<String, Object> doLogout(HttpServletRequest request, HttpServletResponse response) {
        boolean success;
        String msg;
        String code;
        String err = "";
        Map<String, Object> result = new HashMap();

        //退出业务
        try {
            Session session = SessionManage.getSession();
            if (null == session) {
                result.put("success", true);
                result.put("code", HttpStatus.OK.toString());
                result.put("messgage", "注销成功");
                return result;
            }

            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                String principal = (String) subject.getPrincipal();

                //注销登录
                subject.logout();
                logger.info("{} logout success!!!", principal);
            } else {
                logger.info("logout success");
            }
            msg = "注销成功";
            success = true;
            code = HttpStatus.OK.toString();
        } catch (IllegalStateException e) {
            logger.info("session invalidated");
            success = false;
            code = HttpStatus.BAD_REQUEST.toString();
            msg = "Session无效";
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            code = HttpStatus.BAD_REQUEST.toString();
            msg = "注销发生异常";
            err = e.getMessage();
        }
        result.put("success", success);
        result.put("code", code);
        result.put("messgage", msg);
        result.put("error", err);
        return result;
    }

//    /**
//     * 验证服务授权有效
//     *
//     * @param orgId 机构ID
//     * @return true 有效 false无效
//     */
//    private boolean licenseValid(String orgId) {
//
//        boolean result = true;
//
//        // 获取用户机构服务授权
//        Map<String, Object> params = new HashMap<>();
//        params.put("orgId", orgId);
//        params.put("sortName", "created_time");
//        params.put("sortOrder", "desc");
//
//        PageInfo pageInfo = licenseService.query(params);
//        List<LicenseBean> licenseList = pageInfo.getList();
//        if (!licenseList.isEmpty()) {
//            LicenseBean license = licenseList.get(0);
//
//            if (DateUtils.truncatedCompareTo(license.getEndDate(), new Date(), Calendar.DAY_OF_MONTH) < 0) {
//                result = false;
//            }
//        }
//        return result;
//    }

    /**
     * 获取单位
     *
     * @param type 单位类型
     * @return 单位
     */
    private String getUnit(String type) {
        switch (type) {
            case "1":
                return "指挥部";
            case "2":
                return "总监办";
            case "3":
                return "项目部";
            default:
                return "";
        }
    }

    /**
     * 组装返回结构
     *
     * @param result
     * @param user
     * @throws Exception
     */
    private void combineResult(Map<String, Object> result, UserPO user) {
        //将用户角色列表放入到返回对象中
//        List<RolePO> userRoles = roleService.queryRoleByUserId(user.getId());
//        result.put("userId", user.getId());
//        result.put("userName", user.getFullName());
//        result.put("username", user.getUserName());
//        result.put("cellphone", user.getCellphone());
//        result.put("orgId", user.getOrgId()); // 机构Id
//
//        result.put("userRoles", userRoles);
//        result.put("permissionList", permissionService.queryByUserId(user.getId()));   //当前用户权限
//        result.put("menuList", menuService.queryUserAppMenu(user.getId(), appCode));   //当前用户菜单
//        result.put("userManageRangeList", userManageRangeService.getByUserId(user.getId())); // 当前账号管理范围
//        result.put("currentSystemTime", DateTimeUtil.dateToString(new Date(), "yyyy年MM月dd日"));  // 当前系统时间
//        result.put("encryptLoginName", StringUtils.isNotEmpty(user.getUserName()) ? AESEncryptDecrypt.aesEncrypt(user.getUserName(), "sgms8!8@8#8$8%8^8&8*") : ""); // 加密后登录名
//        result.put("systemCode", ParamConfigCache.getInstance().getParamValue("system_code")); // 系统代码
//        result.put("isjjj", ParamConfigCache.getInstance().getParamValue("is.jjj")); //是否是交建局项目
//        result.put("isChecked", ParamConfigCache.getInstance().getParamValue("is_checked")); // 系统工人分层代码
//        result.put("isNewCheck", ParamConfigCache.getInstance().getParamValue("is_new_check")); // 是否为新版巡查
//        result.put("isNewCheckClassify", ParamConfigCache.getInstance().getParamValue("is_new_check_classify")); // 是否为新版巡查
//
//        OrgBean orgBean = orgService.getOrg(user.getOrgId());
//        result.put("orgBean", orgBean); // 标段名称
//        result.put("unit", orgBean == null ? "" : getUnit(orgBean.getType())); // 单位 1指挥部 2总监办 3项目部
//        result.put("postName", dictService.getDictNameByTypeNameAndValue("岗位", user.getPostCode())); // 岗位名称
//        result.put("appEnv", ConfigUtil.getInstance().getAppEnv()); // App使用环境
//        //获取 cas 用户密码
//        UserVO userVO = userRemoteApi.getByUserName(user.getUserName()).getData();
//        result.put("isOriginPwd", "8ddcff3a80f4189ca1c9d4d902c3c909".equals(userVO.getPassword()));
//        result.put("originPwdModifyOnOff", ParamConfigCache.getInstance().getParamValue("origin_pwd_modify_on_off")); // 原始密码修改提示开关
    }

//    /**
//     * 设置用户单位（指挥部，监理，项目部）
//     *
//     * @param user 用户对象
//     * @throws Exception 异常
//     */
//    public void setUnit(UserBean user) {
//        if (user.getOrgId() == null) {
//            return;
//        }
//        OrgBean orgBean = orgService.getOrg(user.getOrgId());
//        user.setUnit(orgBean == null ? "" : getUnit(orgBean.getType()));
//        user.setUnitType(orgBean == null ? "" : orgBean.getType());
//    }

//    private void insertCode(String userId, String cellPhone) {
//        Assert.notNull(userId, "用户ID不为空");
//        UserValidCodePO po = new UserValidCodePO();
//        po.setId(UuidUtils.getUuid32());
//        po.setUserId(userId);
//        po.setValidCode(RandomStringUtils.randomNumeric(4));
//        //验证码过期时间五分钟
//        long expireTime = new Date().getTime() + 5 * 60000;
//        po.setExpireTime(new Date(expireTime));
//        userValidCodeMapper.insert(po);
//        //发送验证码
//        smsService.sendValidCode(cellPhone, po.getValidCode());
//
//    }
//
//    private void logRecord(HttpServletRequest request, String operateType, String content, String Url, String methodName) {
//        //记录登录日志
//        OperateLogBean operateLogBean = new OperateLogBean();
//        operateLogBean.setOperateType(operateType);
//        operateLogBean.setContent(content);
//        operateLogBean.setUrl(Url);
//        operateLogBean.setMethodName(this.getClass().getName() + methodName);
//        operateLogBean.setClientType(NetworkUtil.getClientType(request));
//        operateLogBean.setClientAgent(request.getHeader("User-Agent"));
//        operateLogBean.setIp(NetworkUtil.getIpAddress(request));
//        operateLogService.insert(operateLogBean);
//    }

}
