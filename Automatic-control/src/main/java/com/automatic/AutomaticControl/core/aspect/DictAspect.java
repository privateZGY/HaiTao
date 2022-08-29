package com.automatic.AutomaticControl.core.aspect;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.automatic.AutomaticControl.core.annotation.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.automatic.AutomaticControl.core.utils.ConvertUtils;
import com.automatic.AutomaticControl.administration.service.IAdministrationDictionaryDetailsService;
import com.automatic.AutomaticControl.core.result.ResponseResult;
import com.automatic.AutomaticControl.core.statics.GlobalStaticVariable;
import com.automatic.AutomaticControl.core.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @version v1.0
 * @ProjectName: micro-questions-answers
 * @ClassName: DictAspect
 * @Description: 数据字典转换类
 * 这里是AOP切入的一个类
 * @Author: xiefan
 * @Date: 12/9/2021 8:39 AM
 */
@Aspect
@Component
@Slf4j
public class DictAspect {

    @Autowired
    // 注入字典明细表的service
    private  IAdministrationDictionaryDetailsService iAdministrationDictionaryDetailsService;

    /**
     * 配置切入点
     * 切入点在controller
     */
    @Pointcut("execution(public * com.automatic.AutomaticControl.*.controller.*.*(..))")
    public void excudeService() {}

    /*
    * 执行切入点
    * @Around的作用：既可以在目标方法之前织入增强动作，也可以在执行目标方法之后织入增强动作；
      可以决定目标方法在什么时候执行，如何执行，甚至可以完全阻止目标目标方法的执行；
      可以改变执行目标方法的参数值，也可以改变执行目标方法之后的返回值； 当需要改变目标方法的返回值时，只能使用Around方法；
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time1 = System.currentTimeMillis();
        // 获得返回结果
        Object result = pjp.proceed();
        long time2 = System.currentTimeMillis();
        log.debug("获取JSON数据 耗时：" + (time2 - time1) + "ms");
        long start = System.currentTimeMillis();
        // 解析返回结果
        this.parseDictText(result);
        long end = System.currentTimeMillis();
        log.debug("解析注入JSON数据  耗时" + (end - start) + "ms");
        return result;
    }

    private void parseDictText(Object result) {
        // 判断返回结果是否为ResponseResult的数据类型
        // instanceof  用来判断一个对象实例的类型是否是某构造函数, 这里的作用就是判断result是否为ResponseResult这个数据类型
        if (result instanceof ResponseResult) {
            // 获取ResponseResult的data属性， ((ResponseResult) result)这里是个强制转换，前提是result必须等于ResponseResult这个数据类型我们才能强制转换
            Object data = ((ResponseResult) result).getData();
            // 判断这个data的属性是否等于IPage
            if (data instanceof IPage) {
                // 如果是IPage的话我们就执行这一个方法
                parseJsonForIPage(data);
                // 判断这个data的属性是否等于List
            } else if (data instanceof List) {
                // 如果是List的话我们就执行这一个方法
                parseJsonForList(result);
                // 如果他不等于null还有基本数据类型，就判断他为javaBean的一个类
            } else if (isBaseDataType(data)) {
                // 如果是javaBean的话我们就执行这一个方法
                parseJsonForBean(result);
            }
        }
    }

    /**
     * 解析Ipage返回值
     * @param data
     * 如果是IPage的话我们就执行这一个方法
     */
    private void parseJsonForIPage (Object data) {
        // 定义返回结果集, 这里定义的是List里的一个json集合， 因为要改变他原来的所以定义一个新的list来接收
        List<JSONObject> items = new ArrayList<>();
        /*
        * ((IPage) data)将data值强制转换为IPage数据类型
        * .getRecords(获取到的是对应的JavaBean)
        * */
        for (Object record : ((IPage) data).getRecords()) {
            // 通过parseObject这个反射获取javaBean里面的所有字段，然后遍历所有字段，他有没有包含@Dict这个注解，
            // parseObject这个方法执行完后，将得到的item
            JSONObject item = parseObject(record);
            items.add(item);
        }
        // 重新设置进Records里面，改变了Records的值
        ((IPage) data).setRecords(items);
    }

    /**
     * 解析List集合
     * @param result
     * 如果是List的话我们就执行这一个方法
     */
    private void parseJsonForList (Object result) {
        // 获得data值
        Object data = ((ResponseResult) result).getData();
        List<JSONObject> items = new ArrayList<>();
        for (Object record : ((List) data)) {
            JSONObject item = parseObject(record);
            items.add(item);
        }
        ((ResponseResult) result).setData(items);
    }

    /**
     * 解析javaBean集合
     * @param result
     * 如果是javaBean的话我们就执行这一个方法
     */
    private void parseJsonForBean(Object result) {
        Object data = ((ResponseResult) result).getData();
        JSONObject item = parseObject(data);
        ((ResponseResult) result).setData(item);
    }

    /**
     * 将code值转换为value
     * @param object 就是我们得到的 javabean
     * @return
     */
    private JSONObject parseObject (Object object) {
        // 通过ObjectMapper返回原来的值
        ObjectMapper mapper = new ObjectMapper();
        // 将object转换为一个json格式， 因为返回给前端的是一个json的数据类型
        String json="{}";
        try {
            // 解决LocalDateTime转换失败问题
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(sdf);
            mapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            mapper.registerModule(new JavaTimeModule());
            mapper.setLocale(Locale.getDefault());
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            //这一行是转换为json格式的代码 解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json解析失败" + e.getMessage(), e);
        }
        // 将JSON字段转换为JSON对象
        JSONObject item = JSONUtil.parseObj(json);
        // 遍历JavaBean的所有属性
        // ConvertUtils.getAllFields(object)获取JavaBean所有属性
        for (Field field : ConvertUtils.getAllFields(object)) {
            // 遍历javaBean判断哪个字段包含这个Dict这个注解
            if (field.getAnnotation(Dict.class) != null) {
                // 获取注解的dicCode值也就是user_type这一类似的值
                String code = field.getAnnotation(Dict.class).dicCode();
                // 获取属性的具体值，值为0或1或2
                String key = String.valueOf(item.get(field.getName()));
                // 根据dicCode值和属性值查询value
                String textValue = translateDictValue(code, key);
                // 往item里面添加值, 通过field.getName()获取原来的一个属性名+DICT_TEXT_SUFFIX这个后缀
                item.putOpt(field.getName() + GlobalStaticVariable.DICT_TEXT_SUFFIX, textValue);
            }
        }
        return item;
    }

    private String translateDictValue (String code, String key) {
        if(StrUtil.isBlank(key)) {
            return null;
        }
        // 查询value的值
        String tmpValue = iAdministrationDictionaryDetailsService.queryDictTextByKey(code, Integer.parseInt(key.trim()));
        return tmpValue;
    }

    // 判断他是否为JavaBean
    private boolean isBaseDataType (Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof String) {
            return false;
        } else if (object instanceof Integer) {
            return false;
        } else if (object instanceof Long) {
            return false;
        } else if (object instanceof Character) {
            return false;
        } else if (object instanceof Double) {
            return false;
        } else if (object instanceof Float) {
            return false;
        } else if (object instanceof Boolean) {
            return false;
        } else if (object instanceof Byte) {
            return false;
        } else if (object instanceof Short) {
            return false;
        } else {
            return true;
        }
    }

    public class JavaTimeModule extends SimpleModule {
        public JavaTimeModule() {
            super(PackageVersion.VERSION);
            this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
            this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
            this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
            this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        }
    }

}

