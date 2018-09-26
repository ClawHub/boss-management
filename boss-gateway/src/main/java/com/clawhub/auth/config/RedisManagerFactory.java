package com.clawhub.auth.config;

import com.clawhub.redis.client.IRedisClient;
import com.clawhub.redis.client.RedisClient;
import com.clawhub.redis.client.RedisClusterClient;
import com.clawhub.redis.core.RedisTemplate;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <Description> redis管理工厂<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/3 <br>
 */
public class RedisManagerFactory {
    /**
     * The Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(RedisManagerFactory.class);

    /**
     * getRedisManager
     *
     * @param redisTemplate redisTemplate
     * @return RedisManager
     */
    public static IRedisManager getRedisManager(RedisTemplate redisTemplate) {
        IRedisClient redisClient = redisTemplate.getRedisClient();
        if (redisClient instanceof RedisClient) {
            logger.info("init redis client");
            RedisManager redisManager = new RedisManager();
            redisManager.setJedisPool(((RedisClient) redisClient).getJedisPool());
            return redisManager;
        } else {
            logger.info("init redis cluster client");
            RedisClusterManager redisManager = new RedisClusterManager();
            redisManager.setJedisCluster(((RedisClusterClient) redisClient).getJedisCluster());
            return redisManager;
        }
    }
}
