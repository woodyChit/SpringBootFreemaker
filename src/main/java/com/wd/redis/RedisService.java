package com.wd.redis;

import com.wd.entity.RedisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by woody on 2017/7/6.
 */
@Service
public class RedisService {
    //only valueOps
    //
    private final String SCORE_RANK = "RedisScoreRankZSet";
    private final int MAX_RANK=500;
    private final Random rd = new Random();
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    RedisUserRepository redisUserRepository;


    public void setValue(String key,Object o){
        ValueOperations<String, Object > ops = redisTemplate.opsForValue();
        ops.set(key,o,30, TimeUnit.MINUTES);
    }

    public void setValue(String key,Object o,long expire,TimeUnit unit){
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        ops.set(key,o,expire, unit);
    }

    public <T>  T getValue(String key,Class<T> clazz){
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        Object value = ops.get(key);
        return clazz.cast(value);
    }


    public RankTurple genRandomScore(String username){
        double r = rd.nextDouble();
        int i = rd.nextInt(100);
        r = Math.round(r*10000)/10.0;
        String u = username+i;
        Long rank = addNewScore(u,r);
        return new RankTurple(u,r,rank);
    }

    public class  RankTurple{
        private String s;
        private Double v;
        private Long rank;
        public RankTurple(String name,Double value,Long r){
            s=name;
            v=value;
            rank = r;
        }

        public String getS() {
            return s;
        }

        public Double getV() {
            return v;
        }

        public Long getRank() {
            return rank;
        }
    }
    /**
     * 设置初始分
     * @param username
     * @param value
     * @return
     */
    public Long addNewScore(String username,double value){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        zso.add(SCORE_RANK,username,value);
        return zso.reverseRank(SCORE_RANK,username)+1;
    }
    /**
     * 加分
     * @param username
     * @param value
     * @return
     */
    public Double increScore(String username,double value){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        return zso.incrementScore(SCORE_RANK,username,value);
    }

    /**
     * 得到排名
     * @param start
     * @param end
     * @return
     */
    public Map<String,Double> getRank(long start,long end){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<Object>> rank = zso.reverseRangeWithScores(SCORE_RANK,start,end);
        Map<String,Double> rankMap = new LinkedHashMap<>();
        for(ZSetOperations.TypedTuple<Object> it:rank){
            rankMap.put((String)it.getValue(),it.getScore());
        }
        return rankMap;
    }

    /**
     * 得到用户的排名
     * @param userName
     * @return
     */
    public Long getUserRank(String userName){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        return zso.rank(SCORE_RANK,userName);
    }

    /**
     * 得到总数
     * @return
     */
    public Long getLastRank(){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        return zso.size(SCORE_RANK);
    }
    public Boolean removeUser(String userName){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        Long removedNumber = zso.remove(SCORE_RANK,userName);
        return (removedNumber!=0);
    }


    public Long leftPush(String listName,Object value){
        ListOperations<String,Object> lop = redisTemplate.opsForList();
        return lop.leftPush(listName,value);
    }
    public <E> Long leftPush(String listName, Collection<E> collection){
        ListOperations<String,Object> lop = redisTemplate.opsForList();
        return lop.leftPushAll(listName,collection);
    }
    public <T> T rightPop(String listName, Class<T> type){
        ListOperations<String,Object> lop = redisTemplate.opsForList();
        Object value = lop.rightPop(listName);
        if(value==null){
            return null;
        }else{
            //same  return type.cast(value);
            return (T) value;
        }

    }

    public RedisUser setRedisUser(RedisUser user){
        return redisUserRepository.save(user);
    }

    public void delRedisUser(RedisUser user){
        redisUserRepository.delete(user);
    }

    public void delRedisUser(Integer id){
        redisUserRepository.delete(id);
        //redisUserRepository.delete(new RedisUser(12,"2","fwe"));
        // 按类型删除，实际上依据id。
    }
    public RedisUser getRedisUser(Integer id){
        return redisUserRepository.findOne(id);
    }

}
