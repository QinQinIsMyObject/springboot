package com.zpark.circle_shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author Celery
 * springboot的配置类都需要添加一个注解： @Configuration
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

        //创建一个泛型为String,Object的RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置key的序列化机制
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置value的序列化机制
        redisTemplate.setValueSerializer(getJsonSerializer());
        redisTemplate.setHashValueSerializer(getJsonSerializer());

        //将redisTemplate设置给redisConnectionFactory
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

    /**
     * 配置Spring-Cache的缓存序列化机制
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        //获取redis-cache的配置对象
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        return configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getJsonSerializer()))
                .entryTtl(Duration.ofHours(1));
    }

    /**
     * 生成json的序列化机制
     *
     * @return
     */
    private Jackson2JsonRedisSerializer<Object> getJsonSerializer() {
        return new Jackson2JsonRedisSerializer<Object>(Object.class);
    }

}
