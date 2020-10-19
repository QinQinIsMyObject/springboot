package com.zpark.springboot03.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Celery
 * springboot的配置类都需要添加一个注解：@Configuration
 */
@Configuration
public class RedisConfig {
    /**
     * 只要给springboot组件中的方法加入@Bean注解，该方法的返回值类型就可以成为别的组件@Autowired的内容
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //创建一个泛型为String，Object的RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        //创建一种json的序列化机制
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        //设置key的序列化机制
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //设置value的序列化机制
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);

        //将redisTemplate设置给redisConnectionFactory
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

}
