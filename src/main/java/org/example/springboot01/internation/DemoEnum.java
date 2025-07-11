package org.example.springboot01.internation;

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
