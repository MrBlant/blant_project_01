package org.example.springboot.internation;

/**
 * 枚举国际化示例
 * 演示如何为枚举字段绑定国际化 key，以便在前端根据语言环境展示不同语言
 */
public enum DemoEnum {

    /* 1.现有枚举值添加对应中文字段的I18nKey */
    DEMO_1("姓名1", "测试1", "java.DemoEnum.DEMO_1.xing_ming_1", "java.DemoEnum.DEMO_1.ce_shi_1"),
    DEMO_2("姓名2", "测试2", "java.DemoEnum.DEMO_2.xing_ming_2", "java.DemoEnum.DEMO_2.ce_shi_2");

    private final String name;
    private final String desc;

    /* 2.定义字段 */
    private final String nameI18nKey;
    private final String descI18nKey;

    DemoEnum(String name, String desc, String nameI18nKey, String descI18nKey) {
        this.name = name;
        this.desc = desc;
        /* 3. 构造函数添加对应中文字段的I18nKey */
        this.nameI18nKey = nameI18nKey;
        this.descI18nKey = descI18nKey;
    }
}
