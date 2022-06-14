//package com.springBoot.springSecurityImplementation.TaskJwtTokenImple;
//
//import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import org.springframework.stereotype.Component;
//
//import static java.util.Arrays.asList;
//
//
//public class SimpleCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {
//
//    @Override
//    public void customize(ConcurrentMapCacheManager cacheManager) {
//        cacheManager.setCacheNames(asList("token"));
//    }
//}

//@Component
//@Cacheable(value = "token")
//public class SimpleCacheCustomizer{
//
//}