package com.hust13.wishbottle.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust13.wishbottle.entity.Tag;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.mapper.TagUserMapper;
import com.hust13.wishbottle.mapper.UserMapper;
import com.hust13.wishbottle.model.OpenIdJson;
import com.hust13.wishbottle.model.vo.UserVO;
import com.hust13.wishbottle.service.UserService;
import com.hust13.wishbottle.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * UserService实现类
 * update by wzy on 2020/2/20
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final String appID = "wxa4b82aa3f2b0b4b4"; //小程序id
    private final String appSecret = "d08c0b6f6a429c58efa00c0117f32d76"; //小程序密钥

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TagUserMapper tagUserMapper;

    @Autowired
    private RedisTemplate redisTemplate; //redis缓存操作工具

    /**
     * 登录授权
     * @param js_code
     * @return
     * @throws Exception
     */
    @Override
    public Object userLogin(String js_code) throws Exception{
        String result = "";
        String key = "";
        result = HttpUtil.doGet(
                "https://api.weixin.qq.com/sns/jscode2session?appid="
                        + this.appID + "&secret="
                        + this.appSecret + "&js_code="
                        + js_code
                        + "&grant_type=authorization_code", null);
        //读取json数据
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result,OpenIdJson.class);
        //如果用户登录状态已存在 则直接返回用户信息
        if(redisTemplate.hasKey(openIdJson.getOpenid())){
            String openid = openIdJson.getOpenid();
            Integer userId = userMapper.findUserIdByOpenId((String) redisTemplate.opsForValue().get(openid));
            return userMapper.selectByPrimaryKey(userId);
        }
        //生成身份标识
        key = UUID.randomUUID().toString();
        //以k-v形式存入redis缓存，key为身份标识，value为openIdJson，同时设置缓存过期时间为35分钟
        redisTemplate.opsForValue().set(key, openIdJson, 35*60, TimeUnit.SECONDS);
        //保存信息到数据库
        String openid = openIdJson.getOpenid();
        String session_key = openIdJson.getSession_key();
        Map<String, Object> map = new HashMap<>();
        //查看session表中是否已经存在对应条目
        Integer userid = userMapper.findUserIdByOpenId(openid);
        //已经存在，则更新session_key
        if(userid != null){
            map.put("session_key", session_key);
            map.put("userid", userid);
            Integer ret = userMapper.updateSessionSelective(map);
        }
        else{
            //若不存在则插入条目
            map.put("openid", openid);
            map.put("session_key", session_key);
            Integer ret = userMapper.insertOpenId(map);
        }
        //返回身份标识给前端
        return key;
    }

    /**
     * 保存用户信息
     * @param userInfo
     * @param openid
     * @return
     */
    @Override
    public User saveUserInfo(User userInfo, String openid){
        //查找userid
        int userid = userMapper.findUserIdByOpenId(openid);
        userInfo.setId(userid);
        int ret = 0;
        //查询是否存在用户条目
        Integer count = userMapper.ifExist(userid);
        //存在则更新用户信息
        if(count > 0)
            ret = userMapper.updateByPrimaryKeySelective(userInfo);
        else //否则插入条目
            ret = userMapper.insertSelective(userInfo);

        if(ret > 0)
            return userMapper.selectByPrimaryKey(userid); //返回User给前端
        else
            return null;
    }

    /**
     * 通过openid获取用户id
     * @param openid
     * @return
     */
    @Override
    public Integer getUserIdByOpenId(String openid) {
        return userMapper.findUserIdByOpenId(openid);
    }

    /**
     * 获取指定用户信息
     * @param userId
     * @return
     */
    @Override
    public UserVO getUserInfo(Integer userId) {
        User user = userMapper.getUserInfo(userId);
        List<Tag> tags = tagUserMapper.getTagsByUserId(userId);
        //设置返回数据
        UserVO userVo = new UserVO();
        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setAvatar(user.getAvatar());
        userVo.setGender(user.getGender());
        userVo.setAge(user.getAge());
        userVo.setSignature(user.getSignature());
        userVo.setCity(user.getCity());
        userVo.setMyPic(user.getMyPic());
        userVo.setTags(tags);
        return userVo;
    }

    /**
     * 更新用户信息
     * @param record
     * @return
     */
    @Override
    public UserVO updateUserInfo(UserVO record) {
        //更新user表
        User user = record;
        Integer ret1 = userMapper.updateByPrimaryKeySelective(record);
        if(record.getTags() != null) {
            //先清除用户所有的标签
            Integer ret2 = tagUserMapper.deleteAllByUserId(record.getId());
            //循环向tag_user表中插入记录
            for (Tag tag : record.getTags()) {
                Map<String, Integer> map = new HashMap<>();
                map.put("userId", record.getId());
                map.put("tagId", tag.getId());
                Integer ret3 = tagUserMapper.insertRecord(map);
            }
        }
        return record;
    }

    /**
     * 管理获取所有
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    /**
     * 管理删除
     * @param id
     * @return
     */
    @Override
    public String deleteById(Integer id) {
        Integer ret = userMapper.deleteByPrimaryKey(id);
        if(ret > 0)
            return "删除成功";
        else
            throw new RuntimeException("删除失败");
    }
}
